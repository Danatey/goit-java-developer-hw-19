package org.example.services;

import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;

import java.util.List;

public interface NoteService {

    List<NoteResponseDto> getAll();

    NoteResponseDto getById(Long id);

    NoteResponseDto create(NoteRequestDto dto);

    NoteResponseDto update(Long id,
                           NoteRequestDto dto);

    void delete(Long id);
}
