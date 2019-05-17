package com.learning.heroesservice.dto;

import com.learning.heroesservice.model.Hero;

public class WebSocketDto {

    private String command;

    private Hero hero;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
