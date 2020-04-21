package me.dheart.pikabuy.commands;

import java.awt.Color;

import me.dheart.pikabuy.PikaBuy;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Vender extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event){
		String[] args = event.getMessage().getContentRaw().split(" ");
		if(args[0].equalsIgnoreCase(PikaBuy.prefix+"vender")){
			
			if(args.length<6){
				EmbedBuilder vender = new EmbedBuilder();
				vender.setTitle("PikaBuy ERROR");
				vender.setDescription("⛔ ERROR!");
				vender.addField("Uso Correcto:", "*vender <pokemon> <nivel> <precio> <genero> <tipo>", false);
				vender.setColor(Color.RED);
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(vender.build()).queue();
				vender.clear();
			}else{
				String gen = "";
				EmbedBuilder venta = new EmbedBuilder();
				venta.setTitle("💥 PikaBuy venta");
				venta.setDescription("Se ha ofertado un nuevo pokemon!");
				venta.setImage("https://img.pokemondb.net/sprites/black-white/anim/normal/"+args[1]+".gif");
				venta.addField("Pokemon:",args[1],false);
				venta.addField("Nivel:","💎 "+args[2],false);
				venta.addField("Precio:","💵 "+args[3],false);
				if(args[4].equalsIgnoreCase("hembra")){
					gen = gen + "🟣 Hembra";
				}
				if(args[4].equalsIgnoreCase("macho")){
					gen = gen + "🔵 Macho";
				}
				venta.addField("Género:",gen,false);
				venta.addField("Tipo:",args[5],false);
				venta.setFooter(event.getAuthor().getName(),event.getMember().getUser().getAvatarUrl());
				venta.setColor(0x14e329);
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(venta.build()).queue(message -> message.addReaction("💰").queue());
				venta.clear();
		
			}
			event.getMessage().delete().queue();
		}
	}
}
