package com.codecool.seriesminiproject.repository;

import com.codecool.seriesminiproject.entity.Episode;
import com.codecool.seriesminiproject.entity.Season;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SeasonRepositoryTest {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test //TODO: is that right?
    public void episodeIsPersistedWithSeason() {
        Episode episode = Episode.builder()
                .title("some title")
                .releaseDate(LocalDate.of(2013, 10, 10))
                .build();

        Season season = Season.builder()
                .title("Season2")
                .episode(episode)
                .build();

        seasonRepository.save(season);

        List<Season> seasons = seasonRepository.findAll();

        assertThat(seasons)
                .hasSize(1)
                .allMatch(season1 -> season1.getId() > 0L);
    }

    @Test
    public void episodesArePersistedWithNewSeason() {

        Set<Episode> episodes = IntStream.range(1, 10)
                .boxed()
                .map(integer -> Episode.builder().title("title" + integer)
                        .build())
                .collect(Collectors.toSet());

        Season season = Season.builder()
                .title("Desperate Housewives")
                .episodes(episodes)
                .build();

        seasonRepository.save(season);

        assertThat(episodeRepository.findAll())
                .hasSize(9)
                .anyMatch(episode -> episode.getTitle().equals("title9"));
    }
}