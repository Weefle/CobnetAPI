package fr.cobnet.core.opti;

import fr.cobnet.api.events.Listener;

public abstract class OptimisationModule extends Listener
{
	protected String name;
	private boolean isEnabled;
	
	public OptimisationModule(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public boolean isEnabled()
	{
		return isEnabled;
	}
	
	public void enable()
	{
		isEnabled = true;
	}
	
	public void disable()
	{
		isEnabled = false;
	}

}
