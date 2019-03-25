package com.codecool.seriesminiproject.repository;

import com.codecool.seriesminiproject.entity.Episode;
import com.codecool.seriesminiproject.entity.Season;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
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
public class EpisodeRepositoryTest {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveOneEpisode() {
        Episode episode = Episode.builder()
                .title("some title")
                .releaseDate(LocalDate.of(2013, 10, 10))
                .build();

        episodeRepository.save(episode);

        List<Episode> episodes = episodeRepository.findAll();

        assertThat(episodes).hasSize(1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveUniqueFieldTwice() {
        Episode episode = Episode.builder()
                .title("title")
                .build();

        episodeRepository.save(episode);

        Episode episode1 = Episode.builder()
                .title("title")
                .build();

        episodeRepository.saveAndFlush(episode1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void titleShouldNotBeNull() {

        Episode episode = Episode.builder()
                .releaseDate(LocalDate.of(2013, 10, 10))
                .build();

        episodeRepository.save(episode);
    }

    @Test
    public void releaseDateIsTransient() {

        Episode episode = Episode.builder()
                .title("some title")
                .releaseDate(LocalDate.of(2013, 10, 10))
                .build();

        episodeRepository.save(episode);

        entityManager.clear();

        List<Episode> all = episodeRepository.findAll();

        assertThat(all).allMatch(episode1 -> episode1.getReleaseDate() == null);
    }
}