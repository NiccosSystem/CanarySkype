package uk.niccossystem.canaryskype;

import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.plugin.Plugin;

public class CanarySkype extends Plugin {

	private MessageListener listener = new MessageListener(this);
	
	
	public void disable() {
		
	}
	
	public boolean enable() {
		
		try {
			Canary.commands().registerCommands(listener, this, false);
		} catch (CommandDependencyException e) {
			e.printStackTrace();
		}		
		
		System.out.println("Enabled!");
		return true;
		
	}
}