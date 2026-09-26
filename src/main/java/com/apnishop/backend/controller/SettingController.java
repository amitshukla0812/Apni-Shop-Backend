package com.apnishop.backend.controller;

import com.apnishop.backend.entity.Setting;
import com.apnishop.backend.repository.SettingRepository;
import com.apnishop.backend.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingRepository repository;

    @GetMapping
    public List<Setting> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setting> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Setting create(@RequestBody Setting setting) {
        // If no id was sent (normal frontend create), generate one.
        // If an id WAS sent (e.g. migrating old db.json data), keep it as-is.
        if (setting.getId() == null || setting.getId().isBlank()) {
            setting.setId(IdGenerator.generate());
        }
        return repository.save(setting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setting> update(@PathVariable String id, @RequestBody Setting updated) {
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
