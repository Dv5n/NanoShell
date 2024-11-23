package com.HappyCow.Plugins;
import com.HappyCow.Plugins.PluginManager.Plugin;

public class ExamplePlugin implements Plugin
{
	@Override
	public void execute()
	{
		System.out.println("Hello, this is a simple plugin!");
	}
}
