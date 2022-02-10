package commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.interaction.SlashCommand;

public record PlayCommand(DiscordApi api) {

    public PlayCommand(DiscordApi api) {
        this.api = api;

        SlashCommand command = SlashCommand.with("ping", "Checks the functionality of this command")
                .createGlobal(api)
                .join();
    }


}
