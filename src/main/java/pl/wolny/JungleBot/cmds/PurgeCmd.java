package pl.wolny.JungleBot.cmds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.wolny.JungleBot.errorsgen.genErrorEmbled;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PurgeCmd extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageString = event.getMessage().getContentRaw().split(" ");
        if (messageString[0].toString().equals("?purge")) {
            Member user = event.getGuild().getMemberById(event.getAuthor().getId());
            genErrorEmbled errorEmbled = new genErrorEmbled();
            TextChannel channel = event.getChannel();
            if (user.hasPermission(Permission.MESSAGE_MANAGE)) {
                if (messageString.length == 2) {
                    if (isNumeric(messageString[1].toString())) {
                        if(Integer.parseInt(messageString[1]) > 1){
                            event.getMessage().delete().queue();
                            List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(messageString[1])).complete();
                            channel.deleteMessages(messages).queue();
                            channel.deleteMessages(messages).complete();
                            EmbedBuilder sukcess = new EmbedBuilder();
                            sukcess.setTitle("Sukces");
                            sukcess.setColor(Color.green);
                            sukcess.addField("Usuniętę wiadomości:", messageString[1], false);
                            channel.sendMessage(sukcess.build()).queueAfter(1, TimeUnit.SECONDS);
                        } else {
                            errorEmbled.genError(channel, 4);
                        }
                    }else {
                        errorEmbled.genError(channel, 5);
                    }
                }else {
                    errorEmbled.genError(channel, 2);
                }
            }else {
                errorEmbled.genError(channel, 3);
            }
        }
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
