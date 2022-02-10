import events.MessageEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    public static JDA jda;

    public static void main(String[] args) throws LoginException {

        JDABuilder builder = JDABuilder.createDefault(APIKeys.DISCORD_BOT_TOKEN);

        builder.setActivity(Activity.playing("Music \uD83C\uDFB6"));

        builder.addEventListeners(new MessageEvent());

        jda = builder.build();
    }
}