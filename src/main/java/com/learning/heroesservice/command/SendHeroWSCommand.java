package com.learning.heroesservice.command;

import com.learning.heroesservice.dto.WebSocketDto;
import com.learning.heroesservice.framework.command.Command;
import com.learning.heroesservice.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SendHeroWSCommand implements Command {

    private Hero hero;

    private String command;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute() {

        WebSocketDto dto =  new WebSocketDto();
        dto.setCommand(this.command);
        dto.setHero(this.hero);

        simpMessagingTemplate.convertAndSend("/heroSocket", dto);
    }
}
