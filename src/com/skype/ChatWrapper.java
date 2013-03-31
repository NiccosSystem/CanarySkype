package com.skype;

public class ChatWrapper {
	public static Chat getChat(String chatId) {
		return Chat.getInstance(chatId);
	}
}