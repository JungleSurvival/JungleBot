package pl.wolny.JungleBot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GuildJoinEvent extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        event.getMember().getUser().openPrivateChannel().queue((channel) ->
        {
            EmbedBuilder unmute = new EmbedBuilder();
            unmute.setTitle("Koniec kary");
            channel.sendMessage("**Witaj**, na serwerze **" + event.getGuild().getName() + "**" + "\n" + "Aby grać na serwerze napisz `?whitelista NICK` na " + Objects.requireNonNull(event.getGuild().getTextChannelById("785474109126606868")).getAsMention() + " gdzie `NICK` to twój nick w minecraft").queue();
        });
    }
}
