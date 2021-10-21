package com.devsuperior.bds04.repositories;

import com.devsuperior.bds04.entities.City;
import org.apache.juli.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
