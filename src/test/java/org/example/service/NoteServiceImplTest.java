package org.example.service;

import org.example.dto.NoteRequestDto;
import org.example.dto.NoteResponseDto;
import org.example.exceptions.NoteNotFoundException;
import org.example.model.Note;
import org.example.repository.NoteRepository;
import org.example.services.NoteCrudService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    private NoteRepository repository;

    @InjectMocks
    private NoteCrudService service;

    @Test
    void getAll_shouldReturnNotes() {

        Note note = new Note();
        note.setId(1L);
        note.setTitle("Test");
        note.setContent("Content");

        when(repository.findAll())
                .thenReturn(List.of(note));

        List<NoteResponseDto> result =
                service.getAll();

        assertEquals(1, result.size());
        assertEquals("Test",
                result.get(0).getTitle());
    }

    @Test
    void getById_shouldReturnNote() {

        Note note = new Note();
        note.setId(1L);
        note.setTitle("Title");

        when(repository.findById(1L))
                .thenReturn(Optional.of(note));

        NoteResponseDto result =
                service.getById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void getById_shouldThrowException() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                NoteNotFoundException.class,
                () -> service.getById(1L)
        );
    }

    @Test
    void create_shouldSaveNote() {

        NoteRequestDto dto =
                new NoteRequestDto();

        dto.setTitle("New");
        dto.setContent("Content");

        Note saved = new Note();
        saved.setId(1L);
        saved.setTitle("New");
        saved.setContent("Content");

        when(repository.save(any(Note.class)))
                .thenReturn(saved);

        NoteResponseDto result =
                service.create(dto);

        assertNotNull(result.getId());
        assertEquals("New",
                result.getTitle());
    }

    @Test
    void delete_shouldCallRepository() {

        Note note = new Note();
        note.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(note));

        service.delete(1L);

        verify(repository)
                .delete(note);
    }
}
