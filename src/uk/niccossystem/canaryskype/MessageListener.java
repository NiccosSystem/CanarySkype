package uk.niccossystem.canaryskype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.Skype;
import com.skype.SkypeException;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class MessageListener implements CommandListener, ChatMessageListener {
	
	private CanarySkype plugin;	
	private Chat chat = null;
	private List<ChatMessage> receivedMessages = new ArrayList<ChatMessage>();
	

	public MessageListener(CanarySkype instance) {
		plugin = instance;		
		try {
			Skype.addChatMessageListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SkypeListener activated");
	}

	@Override
	public void chatMessageReceived(ChatMessage arg0) throws SkypeException {
		
		if (receivedMessages.contains(arg0)) return;
		receivedMessages.add(arg0);
		
		Canary.getServer().broadcastMessage(Colors.LIGHT_GREEN + "[Skype] " + Colors.LIGHT_GRAY + arg0.getSenderDisplayName() + ": " + Colors.WHITE + arg0.getContent());
	}

	@Override
	public void chatMessageSent(ChatMessage arg0) throws SkypeException {
		if (chat == null) {
			chat = arg0.getChat();
			plugin.getLogman().logInfo("CanarySkype: Registered Skype chat!");
		}
		
		Canary.getServer().broadcastMessage(Colors.LIGHT_GREEN + "[Skype] " + Colors.LIGHT_GRAY + arg0.getSenderDisplayName() + ": " + Colors.WHITE + arg0.getContent());
		
	}
	
	@Command(aliases = { "skype" },
			description = "Send a Skype message.",
			permissions = { "canaryskype.send" },
			toolTip = "/skype <message>",
			min = 2)
	public void skypeCommand(MessageReceiver caller, String[] parameters) {
		if (chat != null) {			
			try {				
				String[] params = Arrays.copyOfRange(parameters, 1, parameters.length);
				String message = null;
				for (String s : params) {
					if (message != null) {
						message = message + s + " ";								
					} else {
						message = s + " ";
					}
				}
				if (message != null) {
					if (message.startsWith("/skype ")) message.replaceFirst("/skype ", "");
					chat.send("[Canary] (" + 
					(caller instanceof Player ? ((Player) caller).getDisplayName() : "Console")
					+ ") " + message);
				}
			} catch (SkypeException e) {
				e.printStackTrace();
			}
		}
		
	}
}