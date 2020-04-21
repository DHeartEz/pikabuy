package me.dheart.pikabuy.events;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PrivateMessageReactionAdd extends ListenerAdapter{
	
	public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event){
		if(event.getReactionEmote().getName().equals("✅") && !event.getUser().equals(event.getJDA().getSelfUser())){
			//event.getChannel().sendMessage("HOLA").queue();
			EmbedBuilder rpta = new EmbedBuilder();
			
			String msg_id = event.getMessageId();
			MessageHistory history = event.getChannel().getHistory();
			List<Message> ret = history.retrievePast(100).complete();
			for(Message m : ret){
				String m_id = m.getId();
				if(m_id.equals(msg_id)){
					//event.getChannel().sendMessage(m).queue();
					List<MessageEmbed> embeds = m.getEmbeds();
					for(MessageEmbed me : embeds){
							rpta.setTitle("💰 Has aceptado la oferta!");
							rpta.setDescription("Pokemon vendido");
							rpta.setColor(Color.MAGENTA);
							rpta.addField("Pokemon:",me.getFields().get(0).getValue(),false);
							rpta.addField("Precio:",me.getFields().get(1).getValue(),false);
							rpta.addField("Comprador:",me.getFields().get(2).getValue(),false);
							rpta.setImage("https://img.pokemondb.net/sprites/black-white/anim/normal/"+me.getFields().get(0).getValue()+".gif");
							event.getChannel().sendMessage(rpta.build()).queue();
							
							//Guild guild = event.getUser().getJDA().getGuildById(me.getFields().get(4).getValue());
							//GuildChannel ch = event.getUser().getJDA().getGuildChannelById(me.getFields().get(5).getValue());
							//TextChannel tcha = guild.getTextChannelById(me.getFields().get(5).getValue());
							TextChannel text_ch = event.getUser().getJDA().getTextChannelById(me.getFields().get(4).getValue());
							//event.getChannel().sendMessage(text_ch.getName()).queue();
							MessageHistory hist = text_ch.getHistory();
							List<Message> ret2 = hist.retrievePast(100).complete();
							for(Message msg : ret2){
								String msg_id2 = msg.getId();
								if(me.getFields().get(3).getValue().equals(msg_id2)){
									//msg.delete();
									text_ch.deleteMessageById(msg_id2).queue();
								}
							}
							
							List<Member> members = text_ch.getGuild().getMembersByName(me.getFields().get(2).getValue(), false);
							
							for(Member member : members){
								EmbedBuilder felic = new EmbedBuilder();
								felic.setTitle("‼ Felicidades!");
								felic.setDescription("Has comprado con éxito un pokemon!");
								felic.setColor(Color.ORANGE);
								felic.addField("Pokemon:",me.getFields().get(0).getValue(),false);
								felic.addField("Precio:",me.getFields().get(1).getValue(),false);
								felic.addField("Vendedor:",event.getUser().getName(),false);
								felic.setImage("https://img.pokemondb.net/sprites/black-white/anim/normal/"+me.getFields().get(0).getValue()+".gif");
								
								member.getUser().openPrivateChannel().queue( (channel) -> channel.sendMessage(felic.build()).queue());
							}
					}
				}
			}
		}
	}
}
