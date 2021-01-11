package pl.wolny.JungleBot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import pl.wolny.JungleBot.TimeSystem.GetTime;
import pl.wolny.JungleBot.TimeSystem.GetTimeunit;
import pl.wolny.JungleBot.TimeSystem.IsValid;
import pl.wolny.JungleBot.cmds.mute.MuteCmd;
import pl.wolny.JungleBot.cmds.ticket.CreateTicket;
import pl.wolny.JungleBot.cmds.warns.WarnCmd;
import pl.wolny.JungleBot.cmds.warns.WarnsCmd;
import pl.wolny.JungleBot.events.*;
import pl.wolny.JungleBot.errorsgen.*;

import javax.security.auth.login.LoginException;

import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {
        Class c = main.class;
        String className = c.getName();
        System.out.println("The fully-qualified name of the class is: " + className);
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        errorTypes errTypeMenager = new errorTypes();
        errTypeMenager.generateAllErrors();
        try {
            DiscordBot();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private static void DiscordBot() throws LoginException {
        JDA jda = JDABuilder.createDefault(System.getenv("TOKEN"))
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS)
                .enableIntents(GatewayIntent.GUILD_PRESENCES)
                .build();
        jda.addEventListener(new MessageReciveEvent());
        jda.addEventListener(new pl.wolny.JungleBot.cmds.AvatarCmd());
        jda.addEventListener(new pl.wolny.JungleBot.cmds.PurgeCmd());
        jda.addEventListener(new WarnsCmd());
        jda.addEventListener(new WarnCmd());
        jda.addEventListener(new CreateTicket());
        jda.addEventListener(new pl.wolny.JungleBot.cmds.ticket.ReactEvent());
        jda.addEventListener(new pl.wolny.JungleBot.cmds.ticket.AddUserToTicket());
        jda.addEventListener(new pl.wolny.JungleBot.events.GuildJoinEvent());
        jda.addEventListener(new MuteCmd());
    }
}