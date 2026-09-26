package com.apnishop.backend.controller;

import com.apnishop.backend.entity.Feature;
import com.apnishop.backend.repository.FeatureRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/feature")
public class FeatureController {

    @Autowired
    private FeatureRepository repository;

    @GetMapping
    public List<Feature> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feature> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Feature create(@RequestBody Feature feature) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (feature.getId() == null || feature.getId().isBlank()) {
            feature.setId(IdGenerator.generate());
        }
        return repository.save(feature);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feature> update(@PathVariable String id, @RequestBody Feature updated) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updated.setId(id);
        return ResponseEntity.ok(repository.save(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {{ put("id", id); }});
    }
}
