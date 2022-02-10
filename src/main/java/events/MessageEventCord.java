package events;

import audio.LavaplayerAudioSource;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageEventCord implements MessageCreateListener {

    public MessageEventCord() { }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        DiscordApi api = event.getApi();

        String message = event.getMessage().getReadableContent();

        if (event.getMessage().getReadableContent().equals("music")) {
            testingHelper(api, event);
        }

        if (event.getMessageAuthor().getIdAsString().equals("226849548469796865")) {
            event.getChannel().sendMessage("\"" + message + "\" :nerd:");
        }

        if (message.equals("busy")) {
            event.getChannel().sendMessage("\"" + message + "\" :nerd:");
        }
    }

    private void testingHelper(DiscordApi api, MessageCreateEvent event) {
        ServerVoiceChannel channel = event.getMessageAuthor().getConnectedVoiceChannel().get();
        channel.connect().thenAccept(audioConnection -> {

            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            playerManager.registerSourceManager(new YoutubeAudioSourceManager());
            AudioPlayer player = playerManager.createPlayer();

            AudioSource source = new LavaplayerAudioSource(api, player);
            audioConnection.setAudioSource(source);

            playerManager.loadItem("https://www.youtube.com/watch?v=l9PxOanFjxQ&ab_channel=FallOutBoyVEVO", new AudioLoadResultHandler() {
                @Override
                public void trackLoaded(AudioTrack track) {
                    player.playTrack(track);
                }

                @Override
                public void playlistLoaded(AudioPlaylist playlist) {
                    for (AudioTrack track : playlist.getTracks()) {
                        player.playTrack(track);
                    }
                }

                @Override
                public void noMatches() {
                    System.out.println("Nothing found");
                }

                @Override
                public void loadFailed(FriendlyException throwable) {
                    System.out.println("Something exploded");
                }
            });

        }).exceptionally(e -> {
            // Failed to connect to voice channel (no permissions?)
            e.printStackTrace();
            return null;
        });
    }
}
