package me.invacto;

import me.invacto.commands.SlashCommandManager;
import me.invacto.events.MessageEventCord;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

public class Bot {

    public static DiscordApi api;

    public static void main(String[] args) {

        api = new DiscordApiBuilder().setToken(APIKeys.DISCORD_BOT_TOKEN)

                .addListener(new MessageEventCord())

                .login().join();

        api.updateActivity(ActivityType.PLAYING, "Music \uD83C\uDFB6");

        new SlashCommandManager(api);
    }

}