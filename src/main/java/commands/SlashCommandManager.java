package commands;

import org.javacord.api.DiscordApi;

public class SlashCommandManager {

    private DiscordApi api;

    public SlashCommandManager(DiscordApi api) {
        this.api = api;

        init();
    }

    private void init() {
        new PlayCommand(api);
    }

}
