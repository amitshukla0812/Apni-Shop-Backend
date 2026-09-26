package com.apnishop.backend.controller;

import com.apnishop.backend.entity.Faq;
import com.apnishop.backend.repository.FaqRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqRepository repository;

    @GetMapping
    public List<Faq> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faq> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Faq create(@RequestBody Faq faq) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (faq.getId() == null || faq.getId().isBlank()) {
            faq.setId(IdGenerator.generate());
        }
        return repository.save(faq);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faq> update(@PathVariable String id, @RequestBody Faq updated) {
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
