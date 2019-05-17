package com.learning.heroesservice.rest;

import com.learning.heroesservice.command.CreateHeroCommand;
import com.learning.heroesservice.command.DeleteHeroCommand;
import com.learning.heroesservice.command.businesslogic.CreateHeroBusinessLogic;
import com.learning.heroesservice.command.businesslogic.DeleteHeroBusinessLogic;
import com.learning.heroesservice.model.Hero;
import com.learning.heroesservice.repositories.HeroRepository;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HeroController {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private CreateHeroBusinessLogic createHeroBusinessLogic;

    @Autowired
    private CreateHeroCommand createHeroCommand;

    @Autowired
    private DeleteHeroBusinessLogic deleteHeroBusinessLogic;

    @Autowired
    private DeleteHeroCommand deleteHeroCommand;

    @RequestMapping(
            value = "/heroes",
            method = RequestMethod.GET
    )
    public List<Hero> getHeroes() {
        List<Hero> heroes = (List<Hero>) heroRepository.findAll();

        heroes = heroes.stream().sorted(Comparator.comparing(Hero::getId).reversed())
        .collect(Collectors.toList());

        return heroes;
    }

    @RequestMapping(
            value = "/heroes/{id}",
            method = RequestMethod.GET
    )
    public Hero getHero(@PathVariable Integer id) {
        return heroRepository.findHeroById(id);
    }

    @RequestMapping(
            value = "/heroes",
            method = RequestMethod.POST
    )
    public void addHero(@RequestBody Hero hero) {
        createHeroCommand.setHero(hero);
        createHeroBusinessLogic.setCreateHeroCommand(createHeroCommand);
        createHeroBusinessLogic.doWork();
    }

    @RequestMapping(
            value = "/heroes/{id}",
            method = RequestMethod.POST
    )
    public void deleteHero(@PathVariable Integer id) {
        deleteHeroCommand.setHeroId(id);
        deleteHeroBusinessLogic.setDeleteHeroCommand(deleteHeroCommand);
        deleteHeroBusinessLogic.doWork();
    }

}
