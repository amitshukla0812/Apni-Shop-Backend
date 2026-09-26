package com.apnishop.backend.controller;

import com.apnishop.backend.entity.Checkout;
import com.apnishop.backend.repository.CheckoutRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutRepository repository;

    @GetMapping
    public List<Checkout> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkout> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Checkout create(@RequestBody Checkout checkout) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (checkout.getId() == null || checkout.getId().isBlank()) {
            checkout.setId(IdGenerator.generate());
        }
        return repository.save(checkout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checkout> update(@PathVariable String id, @RequestBody Checkout updated) {
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
