package com.codecool.seriesminiproject;

import com.codecool.seriesminiproject.entity.Episode;
import com.codecool.seriesminiproject.entity.Season;
import com.codecool.seriesminiproject.entity.Series;
import com.codecool.seriesminiproject.repository.EpisodeRepository;
import com.codecool.seriesminiproject.repository.SeasonRepository;
import com.codecool.seriesminiproject.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SeriesMiniProjectApplication {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    public static void main(String[] args) {
        SpringApplication.run(SeriesMiniProjectApplication.class, args);
    }

    @Bean
    @Profile("production") // h ne zavarjon be a teszt futások során
    public CommandLineRunner init() { // létrehozunk vele entitánsokat
        return args -> {

            Episode e1s1 = Episode.builder()
                    .title("Pilot")
                    .star("Teri Hatcher")
                    .star("Felicity Huffman")
                    .star("Marcia Cross")
                    .star("Eva Longoria")
                    .build();

            Episode e2s1 = Episode.builder()
                    .title("Ah, But Underneath")
                    .star("Ricardo Chavira")
                    .star("James Denton")
                    .star("Doug Savant")
                    .build();

            Episode e3s1 = Episode.builder()
                    .title("Pretty Little Picture")
                    .build();

            Season season = Season.builder()
                    .title("Season1")
                    .episode(e1s1)
                    .episode(e2s1)
                    .episode(e3s1)
                    .build();

            Episode e1s2 = Episode.builder()
                    .title("Next")
                    .build();

            Episode e2s2 = Episode.builder()
                    .title("You Could Drive a Person Crazy")
                    .build();

            Episode e3s2 = Episode.builder()
                    .title("You'll Never Get Away from Me")
                    .build();

            Season season2 = Season.builder()
                    .title("Season2")
                    .episode(e1s2)
                    .episode(e2s2)
                    .episode(e3s2)
                    .build();

            Series series = Series.builder()
                    .name("Desperate Housewives")
                    .season(season)
                    .season(season2)
                    .build();

            e1s1.setSeason(season);
            e2s1.setSeason(season);
            e3s1.setSeason(season);
            e1s1.setSeries(series);
            e2s1.setSeries(series);
            e3s1.setSeries(series);

            e1s2.setSeason(season2);
            e2s2.setSeason(season2);
            e3s2.setSeason(season2);
            e1s2.setSeries(series);
            e2s2.setSeries(series);
            e3s2.setSeries(series);

            season.setSeries(series);
            season2.setSeries(series);

            seriesRepository.save(series);
        };
    }
}
