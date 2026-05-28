package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;
import org.example.services.NoteService;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    @GetMapping
    public List<NoteResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NoteResponseDto get(
            @PathVariable Long id
    ) {
        return service.getById(id);
    }

    @PostMapping
    public NoteResponseDto create(
            @Valid
            @RequestBody NoteRequestDto dto
    ) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public NoteResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        service.delete(id);
    }
}
