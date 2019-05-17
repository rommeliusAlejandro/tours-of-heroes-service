package com.learning.heroesservice.command.businesslogic;

import com.learning.heroesservice.command.CreateHeroCommand;
import com.learning.heroesservice.command.SendHeroWSCommand;
import com.learning.heroesservice.framework.businesslogic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class CreateHeroBusinessLogic implements BusinessLogic {

    @Autowired
    private SendHeroWSCommand sendHeroWSCommand;

    private CreateHeroCommand createHeroCommand;

    public void setCreateHeroCommand(CreateHeroCommand createHeroCommand) {
        this.createHeroCommand = createHeroCommand;
    }

    private SendHeroWSCommand getSendHeroWSCommand() {
        return sendHeroWSCommand;
    }

    private CreateHeroCommand getCreateHeroCommand() {
        return createHeroCommand;
    }

    @Override
    public void doWork() {
        getCreateHeroCommand().execute();

        String command = "CREATE";
        if(null != getCreateHeroCommand().getHero().getId()) {
            command = "UPDATE";
        }

        getSendHeroWSCommand().setHero(getCreateHeroCommand().getHero());
        getSendHeroWSCommand().setCommand(command);
        getSendHeroWSCommand().execute();
    }
}
