package com.learning.heroesservice.repositories;

import com.learning.heroesservice.model.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

    Hero findHeroById(Integer id);
}
