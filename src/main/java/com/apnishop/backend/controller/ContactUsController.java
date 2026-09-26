package com.apnishop.backend.controller;

import com.apnishop.backend.entity.ContactUs;
import com.apnishop.backend.repository.ContactUsRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/contactus")
public class ContactUsController {

    @Autowired
    private ContactUsRepository repository;

    @GetMapping
    public List<ContactUs> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactUs> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ContactUs create(@RequestBody ContactUs contactUs) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (contactUs.getId() == null || contactUs.getId().isBlank()) {
            contactUs.setId(IdGenerator.generate());
        }
        return repository.save(contactUs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactUs> update(@PathVariable String id, @RequestBody ContactUs updated) {
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
