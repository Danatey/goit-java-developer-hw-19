package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteRequestDto {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @Size(max = 2000)
    private String content;
}
