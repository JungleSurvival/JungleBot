package pl.wolny.JungleBot.cmds.warns;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.wolny.JungleBot.errorsgen.genErrorEmbled;

import java.awt.*;
import java.sql.*;
import java.util.EnumSet;

public class WarnCmd extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] msg = event.getMessage().getContentRaw().split(" ");
        if (msg[0].equalsIgnoreCase("?warn")) {
            genErrorEmbled errorEmbled = new genErrorEmbled();
            TextChannel channel = event.getChannel();
            Member user = event.getGuild().getMemberById(event.getAuthor().getId());
                assert user != null;
                if(user.hasPermission(Permission.MESSAGE_MANAGE)){
                    if(msg.length > 2){
                        if(isNumeric(msg[1])){
                            Member warn_new = event.getGuild().getMemberById(msg[1]);
                            if(warn_new != null){
                                StringBuilder sb = new StringBuilder();
                                for (int i = 2; i < msg.length; i++) {
                                    sb.append(msg[i]).append(" ");
                                }
                                String reason = sb.toString().trim();
                                System.out.print(reason);
                                try {
                                    Connection myConn = DriverManager.getConnection(System.getenv("JDBC"), System.getenv("mysql_usr"), System.getenv("mysql_pass"));
                                    String sql = "INSERT INTO `warns` (`idwarna`, `idwarnowanego`, `nickwarnownego`, `idadmina`, `nickadmina`, `powod`) VALUES (NULL, ?, ?, ?, ?, ?);";
                                    PreparedStatement myStmt = myConn.prepareStatement(sql);
                                    myStmt.setString(1, msg[1]);
                                    myStmt.setString(2, warn_new.getUser().getName());
                                    myStmt.setString(3, event.getAuthor().getId());
                                    myStmt.setString(4, event.getAuthor().getName());
                                    myStmt.setString(5, reason);
                                    myStmt.executeUpdate();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                    errorEmbled.genError(channel, -1);
                                    return;
                                }
                                EmbedBuilder suk = new EmbedBuilder();
                                suk.setTitle("Pomyślnie dodano ostrzeżenie");
                                suk.setColor(Color.green);
                                suk.addField("Osoba ostrzeżona:", warn_new.getUser().getName(), false);
                                suk.addField("Admin:", event.getAuthor().getName(), false);
                                suk.addField("Powód:", reason, false);
                                event.getChannel().sendMessage(suk.build()).queue();
                            }else {
                                errorEmbled.genError(channel, 1);
                            }

                        }else {
                            if(event.getMessage().getMentionedMembers().size() > 0){
                                Member warn_new = event.getGuild().getMemberById(event.getMessage().getMentionedMembers().get(0).getId());
                                if(warn_new != null){
                                    Connection myConn = null;
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 2; i < msg.length; i++) {
                                        sb.append(msg[i]).append(" ");
                                    }
                                    String reason = sb.toString().trim();
                                    System.out.print(reason);
                                    try {
                                        Connection myConn2 = DriverManager.getConnection(System.getenv("JDBC"), System.getenv("mysql_usr"), System.getenv("mysql_pass"));
                                        String sql = "INSERT INTO `warns` (`idwarna`, `idwarnowanego`, `nickwarnownego`, `idadmina`, `nickadmina`, `powod`) VALUES (NULL, ?, ?, ?, ?, ?);";
                                        PreparedStatement myStmt = myConn2.prepareStatement(sql);
                                        myStmt.setString(1, warn_new.getId());
                                        myStmt.setString(2, warn_new.getUser().getName());
                                        myStmt.setString(3, event.getAuthor().getId());
                                        myStmt.setString(4, event.getAuthor().getName());
                                        myStmt.setString(5, reason);
                                        myStmt.executeUpdate();
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                        errorEmbled.genError(channel, 0);
                                        return;
                                    }
                                    EmbedBuilder suk = new EmbedBuilder();
                                    suk.setTitle("Pomyślnie dodano ostrzeżenie");
                                    suk.setColor(Color.green);
                                    suk.addField("Osoba ostrzeżona:", warn_new.getUser().getName(), false);
                                    suk.addField("Admin:", event.getAuthor().getName(), false);
                                    suk.addField("Powód:", reason, false);
                                    event.getChannel().sendMessage(suk.build()).queue();
                                }else {
                                    errorEmbled.genError(channel, 1);
                                }
                            }else {
                                errorEmbled.genError(channel, 1);
                            }
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
