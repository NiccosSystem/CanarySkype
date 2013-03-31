package uk.niccossystem.canaryskype;

import com.skype.*;

import net.canarymod.Canary;
import net.canarymod.chat.Colors;
import net.canarymod.hook.Hook;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.ChatHook;
import net.canarymod.plugin.PluginListener;

public class MessageListener implements PluginListener, ChatMessageListener {
	
	private CanarySkype plugin;	
	private final String chatId = "#jerry199610/$tyler-yarbrough;c2d10522f2574475";
	private Chat chat;
	
	

	public MessageListener(CanarySkype instance) {
		plugin = instance;		
		try {
			Skype.addChatMessageListener(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SkypeListener activated");
		chat = ChatWrapper.getChat(chatId);
	}

	@Override
	public void chatMessageReceived(ChatMessage arg0) throws SkypeException {
		// TODO Auto-generated method stub
		Canary.getServer().broadcastMessage(Colors.LIGHT_GREEN + "[Skype] " + Colors.LIGHT_GRAY + arg0.getSenderDisplayName() + ": " + Colors.WHITE + arg0.getContent());
	}

	@Override
	public void chatMessageSent(ChatMessage arg0) throws SkypeException {
		// TODO Auto-generated method stu
		Canary.getServer().broadcastMessage(Colors.LIGHT_GREEN + "[Skype] " + Colors.LIGHT_GRAY + arg0.getSenderDisplayName() + ": " + Colors.WHITE + arg0.getContent());
	}
	
	@HookHandler
	public void onChat(ChatHook hook) {
		hook.getPlayer().sendMessage("1");
		plugin.getLogman().info("1");
		if (chat == null) return;
		hook.getPlayer().sendMessage("2");
		plugin.getLogman().info("2");
		if (hook.getMessage().split(" ")[0] == ".skype") {
			hook.getPlayer().sendMessage("3");
			plugin.getLogman().info("3");
			String message = hook.getMessage().replaceFirst(".skype ", "");
			try {
				hook.getPlayer().sendMessage("4");
				plugin.getLogman().info("4");
				chat.send(message);
			} catch (SkypeException e) {
				hook.getPlayer().sendMessage("5");
				plugin.getLogman().info("5");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
	
}