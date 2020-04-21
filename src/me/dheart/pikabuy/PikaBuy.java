package me.dheart.pikabuy;

import javax.security.auth.login.LoginException;

import me.dheart.pikabuy.commands.Vender;
import me.dheart.pikabuy.events.MessageReactionAdd;
import me.dheart.pikabuy.events.PrivateMessageReactionAdd;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Activity.ActivityType;

public class PikaBuy {
	public static JDA jda;
	public static String prefix = "*";
	
	public static void main(String[] args) throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken("NzAxNjE4NTcwNzE4NjA5NDA5.Xp0IBg.TGhcMZ8RLWVIgutz6G7q4NS8eZA").build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setActivity(Activity.playing("PokeMMO"));
		jda.addEventListener(new Commands());
		jda.addEventListener(new Vender());
		jda.addEventListener(new MessageReactionAdd());
		jda.addEventListener(new PrivateMessageReactionAdd());
	}
}
