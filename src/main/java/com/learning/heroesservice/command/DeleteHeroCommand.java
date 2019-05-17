package com.learning.heroesservice.command;

import com.learning.heroesservice.framework.command.Command;
import com.learning.heroesservice.model.Hero;
import com.learning.heroesservice.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class DeleteHeroCommand implements Command {

    private Integer heroId;

    private Hero hero;

    @Autowired
    private HeroRepository heroRepository;

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public Hero getHero() {
        return hero;
    }

    private void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void execute() {
        setHero(heroRepository.findHeroById(getHeroId()));
        heroRepository.deleteById(getHeroId());
    }
}
