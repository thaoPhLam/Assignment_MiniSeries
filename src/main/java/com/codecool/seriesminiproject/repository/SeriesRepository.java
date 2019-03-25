package com.codecool.seriesminiproject.repository;

import com.codecool.seriesminiproject.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
