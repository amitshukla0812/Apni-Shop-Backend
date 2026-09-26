package com.apnishop.backend.controller;

import com.apnishop.backend.entity.SubCategory;
import com.apnishop.backend.repository.SubCategoryRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryRepository repository;

    @GetMapping
    public List<SubCategory> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SubCategory create(@RequestBody SubCategory subCategory) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (subCategory.getId() == null || subCategory.getId().isBlank()) {
            subCategory.setId(IdGenerator.generate());
        }
        return repository.save(subCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> update(@PathVariable String id, @RequestBody SubCategory updated) {
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
