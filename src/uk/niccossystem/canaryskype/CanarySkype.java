package uk.niccossystem.canaryskype;

import com.skype.*;

import net.canarymod.Canary;
import net.canarymod.plugin.Plugin;

public class CanarySkype extends Plugin {

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
		MessageListener listener = new MessageListener(this);
		Canary.hooks().registerListener(listener, this);
		
		System.out.println("Enabled!");
		
	}

}