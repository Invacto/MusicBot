package events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageEvent extends ListenerAdapter {

    public MessageEvent() { }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor();
        String message = event.getMessage().getContentDisplay();

        if (user.getId().equals("226849548469796865")) {
            event.getChannel().sendMessage("\"" + message + "\" :nerd:").queue();
        }

        if (message.equalsIgnoreCase("busy")) {
            event.getChannel().sendMessage("\"" + message + "\" :nerd:").queue();
        }
    }
}
