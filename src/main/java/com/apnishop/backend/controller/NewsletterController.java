package com.apnishop.backend.controller;

import com.apnishop.backend.entity.Newsletter;
import com.apnishop.backend.repository.NewsletterRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/newsletter")
public class NewsletterController {

    @Autowired
    private NewsletterRepository repository;

    @GetMapping
    public List<Newsletter> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Newsletter> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Newsletter create(@RequestBody Newsletter newsletter) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (newsletter.getId() == null || newsletter.getId().isBlank()) {
            newsletter.setId(IdGenerator.generate());
        }
        return repository.save(newsletter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Newsletter> update(@PathVariable String id, @RequestBody Newsletter updated) {
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
