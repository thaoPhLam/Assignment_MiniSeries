package com.codecool.seriesminiproject.repository;

import com.codecool.seriesminiproject.entity.Episode;
import com.codecool.seriesminiproject.entity.Season;
import com.codecool.seriesminiproject.entity.Series;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SeriesRepositoryTest {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private TestEntityManager entityManager;

    //TODO: why these tests failed?
    //TODO: sql-es fv-k megírása s tesztelése
    @Test
    public void seasonsArePersistedWithNewSeries() {

        Set<Season> seasons = IntStream.range(1, 3)
                .boxed()
                .map(integer -> Season.builder().title("Season" + integer)
                        .build())
                .collect(Collectors.toSet());

        Series series = Series.builder()
                .name("Desperate Housewives")
                .seasons(seasons)
                .build();

        seriesRepository.save(series);

        assertThat(seasonRepository.findAll())
                .hasSize(2)
                .anyMatch(season1 -> season1.getTitle().equals("Season2"));
    }

    @Test
    public void episodesArePersistedWithNewSeries() {
        Set<Episode> episodes = IntStream.range(1, 3)
                .boxed()
                .map(integer -> Episode.builder().title("title" + integer)
                        .build())
                .collect(Collectors.toSet());


        Set<Season> seasons = IntStream.range(1, 3)
                .boxed()
                .map(integer -> Season.builder().title("Season" + integer)
                        .episodes(episodes)
                        .build())
                .collect(Collectors.toSet());

        Series series = Series.builder()
                .name("Desperate Housewives")
                .seasons(seasons)
                .build();

        seriesRepository.save(series);

        assertThat(episodeRepository.findAll())
                .hasSize(2)
                .anyMatch(episode -> episode.getTitle().equals("title2"));
    }

}