package com.apnishop.backend.controller;

import com.apnishop.backend.entity.MainCategory;
import com.apnishop.backend.repository.MainCategoryRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/maincategory")
public class MainCategoryController {

    @Autowired
    private MainCategoryRepository repository;

    @GetMapping
    public List<MainCategory> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainCategory> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MainCategory create(@RequestBody MainCategory mainCategory) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (mainCategory.getId() == null || mainCategory.getId().isBlank()) {
            mainCategory.setId(IdGenerator.generate());
        }
        return repository.save(mainCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainCategory> update(@PathVariable String id, @RequestBody MainCategory updated) {
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
