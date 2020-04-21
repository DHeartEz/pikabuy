package me.dheart.pikabuy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event){
		String[] args = event.getMessage().getContentRaw().split(" ");
		if(args[0].equalsIgnoreCase(PikaBuy.prefix+"info")){
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("PikaBuy BOT info");
			info.setDescription("Información importante acerca de PikaBuy BOT");
			info.setColor(0xf5e042);
			info.setFooter("Creado por DHeartEz",event.getMember().getUser().getAvatarUrl());
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
		}
	}
}
