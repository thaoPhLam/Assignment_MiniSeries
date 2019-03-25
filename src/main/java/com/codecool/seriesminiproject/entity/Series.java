package com.codecool.seriesminiproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Series {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ElementCollection
    @Singular
    @OneToMany(mappedBy = "series", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private Set<Season> seasons;

    @ElementCollection
    @Singular
    @OneToMany(mappedBy = "series", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private Set<Episode> episodes;

}
