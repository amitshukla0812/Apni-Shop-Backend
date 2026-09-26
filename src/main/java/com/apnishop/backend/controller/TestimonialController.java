package com.apnishop.backend.controller;

import com.apnishop.backend.entity.Testimonial;
import com.apnishop.backend.repository.TestimonialRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/testimonial")
public class TestimonialController {

    @Autowired
    private TestimonialRepository repository;

    @GetMapping
    public List<Testimonial> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Testimonial> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Testimonial create(@RequestBody Testimonial testimonial) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (testimonial.getId() == null || testimonial.getId().isBlank()) {
            testimonial.setId(IdGenerator.generate());
        }
        return repository.save(testimonial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Testimonial> update(@PathVariable String id, @RequestBody Testimonial updated) {
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
