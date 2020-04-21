package me.dheart.pikabuy.events;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReactionAdd extends ListenerAdapter{
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event){
		if(event.getReactionEmote().getName().equals("💰") && !event.getMember().getUser().equals(event.getJDA().getSelfUser())){
			EmbedBuilder compra = new EmbedBuilder();
			String msg_id = event.getMessageId();
			MessageHistory history = event.getChannel().getHistory();
			List<Message> ret = history.retrievePast(100).complete();
			for(Message m : ret){
				String m_id = m.getId();
				if(m_id.equals(msg_id)){
					//event.getChannel().sendMessage(m).queue();
					List<MessageEmbed> embeds = m.getEmbeds();
					for(MessageEmbed me : embeds){
						Footer footer = me.getFooter();
						List<User> users = event.getJDA().getUsersByName(footer.getText(), true);
						//event.getChannel().sendMessage(footer.getText()).queue();
						for(User u : users){
							compra.setTitle("💰 Alguien quiere comprar tu pokemon!");
							compra.setDescription("Vende tu pokemon ya!");
							compra.setColor(Color.CYAN);
							compra.addField("Pokemon:",me.getFields().get(0).getValue(),false);
							compra.addField("Precio:",me.getFields().get(2).getValue(),false);
							compra.addField("Posible comprador:",event.getUser().getName(),false);
							compra.addField("ID de oferta:",msg_id,false);
							compra.addField("ID Channel:",event.getChannel().getId(),false);
							compra.setImage("https://img.pokemondb.net/sprites/black-white/anim/normal/"+me.getFields().get(0).getValue()+".gif");
							u.openPrivateChannel().queue( (channel) -> channel.sendMessage(compra.build()).queue(message -> message.addReaction("✅").queue(message1 -> message.addReaction("❌").queue())));
							
						}
					}
				}
			}
		}
		
		if(event.getReactionEmote().getName().equals("✅")){
			event.getChannel().sendMessage("HOLA").queue();
		}
	}
	
}
