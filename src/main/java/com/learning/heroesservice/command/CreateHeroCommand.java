package com.learning.heroesservice.command;

import com.learning.heroesservice.framework.command.Command;
import com.learning.heroesservice.model.Hero;
import com.learning.heroesservice.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class CreateHeroCommand implements Command {

    private Hero hero;

    @Autowired
    private HeroRepository heroRepository;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void execute() {
        this.hero = heroRepository.save(hero);
    }
}
