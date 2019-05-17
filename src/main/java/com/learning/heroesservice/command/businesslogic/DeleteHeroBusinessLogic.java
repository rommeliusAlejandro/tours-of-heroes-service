package com.learning.heroesservice.command.businesslogic;

import com.learning.heroesservice.command.CreateHeroCommand;
import com.learning.heroesservice.command.DeleteHeroCommand;
import com.learning.heroesservice.command.SendHeroWSCommand;
import com.learning.heroesservice.framework.businesslogic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class DeleteHeroBusinessLogic implements BusinessLogic {

    @Autowired
    private SendHeroWSCommand sendHeroWSCommand;

    private DeleteHeroCommand deleteHeroCommand;

    private SendHeroWSCommand getSendHeroWSCommand() {
        return sendHeroWSCommand;
    }

    private DeleteHeroCommand getDeleteHeroCommand() {
        return deleteHeroCommand;
    }

    public void setDeleteHeroCommand(DeleteHeroCommand deleteHeroCommand) {
        this.deleteHeroCommand = deleteHeroCommand;
    }

    @Override
    public void doWork() {
        getDeleteHeroCommand().execute();

        getSendHeroWSCommand().setHero(getDeleteHeroCommand().getHero());
        getSendHeroWSCommand().setCommand("DELETE");
        getSendHeroWSCommand().execute();
    }
}
