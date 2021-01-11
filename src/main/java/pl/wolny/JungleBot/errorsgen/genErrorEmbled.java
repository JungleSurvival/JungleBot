package pl.wolny.JungleBot.errorsgen;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class genErrorEmbled {
    public void genError(TextChannel channel, int id){
        EmbedBuilder error = new EmbedBuilder();
        errorTypes errTypeMenager = new errorTypes();
        error.setTitle("Błąd");
        error.setColor(Color.red);
        System.out.println(errTypeMenager.getErrornames().toString());
        if(!(errTypeMenager.getErrornames().get(id) == null)){
            error.addField("Kod błędu:", errTypeMenager.getErrornames().get(id), false);

        }
        channel.sendMessage(error.build()).queue();
    }
}
