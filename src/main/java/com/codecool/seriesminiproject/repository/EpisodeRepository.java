package com.codecool.seriesminiproject.repository;

import com.codecool.seriesminiproject.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
