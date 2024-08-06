package com.example.demo.patron.Controller;

import com.example.demo.patron.Model.patronModel;
import com.example.demo.patron.Repository.patronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class patronController {
    @Autowired
    private patronRepository patronRepository;

    @GetMapping
    public ResponseEntity<List<patronModel>> getAllPatrons() {
        return ResponseEntity.ok(patronRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<patronModel> getPatronById(@PathVariable Long id) {
        return patronRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<patronModel> addPatron(@Valid @RequestBody patronModel patron) {
        patronModel savedPatron = patronRepository.save(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<patronModel> updatePatron(@PathVariable Long id, @Valid @RequestBody patronModel patron) {
        return patronRepository.findById(id)
                .map(existingPatron -> {
                    patron.setId(id);
                    return ResponseEntity.ok(patronRepository.save(patron));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        return patronRepository.findById(id)
                .map(patron -> {
                    patronRepository.delete(patron);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}