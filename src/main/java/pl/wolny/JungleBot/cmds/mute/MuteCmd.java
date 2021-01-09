package pl.wolny.JungleBot.cmds.mute;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.wolny.JungleBot.TimeSystem.GetTime;
import pl.wolny.JungleBot.TimeSystem.GetTimeunit;
import pl.wolny.JungleBot.TimeSystem.IsValid;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MuteCmd extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] msg = event.getMessage().getContentRaw().split(" ");
        if (msg[0].equalsIgnoreCase("?mute")) {
            if (Objects.requireNonNull(event.getGuild().getMemberById(event.getAuthor().getId())).hasPermission(Permission.MESSAGE_MANAGE)) {
                if (msg.length > 3) {
                    GetTime time = new GetTime();
                    IsValid valid = new IsValid();
                    GetTimeunit TimeUnit = new GetTimeunit();
                    if (valid.ValidateTime(msg[2])) {
                        if (TimeUnit.GetTimeunit_method(msg[2]).equals(java.util.concurrent.TimeUnit.MILLISECONDS)) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 3; i < msg.length; i++) {
                                sb.append(msg[i]).append(" ");
                            }
                            String reason = sb.toString();
                            if (event.getMessage().getMentionedMembers().size() > 0) {
                                User punished = event.getMessage().getMentionedMembers().get(0).getUser();
                                EmbedBuilder sukces = new EmbedBuilder();
                                sukces.setTitle("Pomyślnie wyciszono");
                                sukces.setColor(Color.green);
                                sukces.addField("Admin:", event.getAuthor().getName(), false);
                                sukces.addField("Ukarany:", punished.getName(), false);
                                sukces.addField("Czas:", msg[2], false);
                                sukces.addField("Powód:", reason, false);
                                event.getChannel().sendMessage(sukces.build()).queue();
                                EmbedBuilder sukces_pv = new EmbedBuilder();
                                sukces_pv.setTitle("Zostałeś wyciszony");
                                sukces_pv.setColor(Color.green);
                                sukces_pv.addField("Admin:", event.getAuthor().getName(), false);
                                sukces_pv.addField("Czas:", msg[2], false);
                                sukces_pv.addField("Powód:", reason, false);
                                final Role role = event.getGuild().getRoleById("762568841087156244");
                                assert role != null;
                                event.getGuild().addRoleToMember(event.getMessage().getMentionedMembers().get(0), role).reason("Muted by " + event.getAuthor().getName()).queue();
                                event.getAuthor().openPrivateChannel().queue((channel) ->
                                {
                                    EmbedBuilder unmute = new EmbedBuilder();
                                    unmute.setTitle("Mute dobiegł końca");
                                    unmute.setColor(Color.GREEN);
                                    channel.sendMessage(unmute.build()).queueAfter(time.GetTime_Method(msg[2]), TimeUnit.GetTimeunit_method(msg[2]));
                                });
                                event.getGuild().removeRoleFromMember(event.getMessage().getMentionedMembers().get(0), role).reason("Auto unmute").queueAfter(time.GetTime_Method(msg[2]), TimeUnit.GetTimeunit_method(msg[2]));
                            } else {
                                if(isNumeric(msg[1])){
                                    Member user = event.getGuild().getMemberById(msg[1]);
                                    if (user != null) {
                                        User punished = user.getUser();
                                        EmbedBuilder sukces = new EmbedBuilder();
                                        sukces.setTitle("Pomyślnie wyciszono");
                                        sukces.setColor(Color.green);
                                        sukces.addField("Admin:", event.getAuthor().getName(), false);
                                        sukces.addField("Ukarany:", punished.getName(), false);
                                        sukces.addField("Czas:", msg[2], false);
                                        sukces.addField("Powód:", reason, false);
                                        event.getChannel().sendMessage(sukces.build()).queue();
                                        EmbedBuilder sukces_pv = new EmbedBuilder();
                                        sukces_pv.setTitle("Zostałeś wyciszony");
                                        sukces_pv.setColor(Color.green);
                                        sukces_pv.addField("Admin:", event.getAuthor().getName(), false);
                                        sukces_pv.addField("Czas:", msg[2], false);
                                        sukces_pv.addField("Powód:", reason, false);
                                        final Role role = event.getGuild().getRoleById("762568841087156244");
                                        assert role != null;
                                        event.getGuild().addRoleToMember(event.getMessage().getMentionedMembers().get(0), role).reason("Muted by " + event.getAuthor().getName()).queue();
                                        event.getAuthor().openPrivateChannel().queue((channel) ->
                                        {
                                            EmbedBuilder unmute = new EmbedBuilder();
                                            unmute.setTitle("Mute dobiegł końca");
                                            unmute.setColor(Color.GREEN);
                                            channel.sendMessage(unmute.build()).queueAfter(time.GetTime_Method(msg[2]), TimeUnit.GetTimeunit_method(msg[2]));
                                        });
                                        event.getGuild().removeRoleFromMember(event.getMessage().getMentionedMembers().get(0), role).reason("Auto unmute").queueAfter(time.GetTime_Method(msg[2]), TimeUnit.GetTimeunit_method(msg[2]));
                                    }else {
                                        EmbedBuilder perms = new EmbedBuilder();
                                        perms.setTitle("Błąd");
                                        perms.setColor(Color.red);
                                        perms.addField("Kod błędu:", "Taki użytkownik nie istnieje", false);
                                        event.getChannel().sendMessage(perms.build()).queue();
                                    }
                                }else {
                                    EmbedBuilder perms = new EmbedBuilder();
                                    perms.setTitle("Błąd");
                                    perms.setColor(Color.red);
                                    perms.addField("Kod błędu:", "Taki użytkownik nie istnieje", false);
                                    event.getChannel().sendMessage(perms.build()).queue();
                                }
                            }
                        } else {
                            EmbedBuilder usg = new EmbedBuilder();
                            usg.setTitle("Błąd");
                            usg.setColor(Color.red);
                            usg.addField("Kod błędu:", "Podano zły czas!", false);
                            event.getChannel().sendMessage(usg.build()).queue();
                        }

                    } else {
                        EmbedBuilder usg = new EmbedBuilder();
                        usg.setTitle("Błąd");
                        usg.setColor(Color.red);
                        usg.addField("Kod błędu:", "Podano zły czas!", false);
                        event.getChannel().sendMessage(usg.build()).queue();
                    }
                }else {
                    EmbedBuilder usg = new EmbedBuilder();
                    usg.setTitle("Błąd");
                    usg.setColor(Color.red);
                    usg.addField("Kod błędu:", "Złe użycie!", false);
                    event.getChannel().sendMessage(usg.build()).queue();
                }
            }else {
                    EmbedBuilder perms = new EmbedBuilder();
                    perms.setTitle("Błąd");
                    perms.setColor(Color.red);
                    perms.addField("Kod błędu:", "Brak permisji", false);
                    event.getChannel().sendMessage(perms.build()).queue();
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
