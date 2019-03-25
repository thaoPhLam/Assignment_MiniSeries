package com.codecool.seriesminiproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Episode {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Transient
    private LocalDate releaseDate;

    //TODO: enumerated LANGUAGE

    @ManyToOne
    private Season season;

    @ManyToOne
    private Series series;

    @ElementCollection
    @Singular
    private List<String> stars;

}
