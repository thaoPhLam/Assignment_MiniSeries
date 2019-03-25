package com.codecool.seriesminiproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Season {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ElementCollection
    @Singular
    @OneToMany(mappedBy = "season", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private Set<Episode> episodes;

    @ManyToOne
    private Series series;

}
