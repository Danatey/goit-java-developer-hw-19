package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "note")
@Getter
@Setter
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;
}
