package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;
import org.example.exceptions.NoteNotFoundException;
import org.example.model.Note;
import org.example.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteCrudService implements NoteService {

    private final NoteRepository repository;

    @Override
    public List<NoteResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public NoteResponseDto getById(Long id) {
        return toDto(find(id));
    }

    @Override
    public NoteResponseDto create(
            NoteRequestDto dto
    ) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        return toDto(repository.save(note));
    }

    @Override
    public NoteResponseDto update(
            Long id,
            NoteRequestDto dto
    ) {
        Note note = find(id);

        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        return toDto(repository.save(note));
    }

    @Override
    public void delete(Long id) {
        repository.delete(find(id));
    }

    private Note find(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new NoteNotFoundException(id)
                );
    }

    private NoteResponseDto toDto(
            Note note
    ) {
        return new NoteResponseDto(
                note.getId(),
                note.getTitle(),
                note.getContent()
        );
    }
}