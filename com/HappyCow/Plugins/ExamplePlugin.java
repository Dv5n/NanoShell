package com.HappyCow.Plugins;

import com.HappyCow.Plugins.PluginManager.Plugin;
import com.HappyCow.Plugins.PluginManager.PluginMetadata;

// Plugin metadata for discovery.
@PluginMetadata(name = "ExamplePlugin", description = "Simple example plugin.", version = "1.0")

/**
* ExamplePlugin class.
*/
public class ExamplePlugin implements Plugin
{
	/**
	* Function that is executed by PluginManager.
	*/
	@Override
	public void execute()
	{
		System.out.println("Hello, this is a simple plugin!"); // Prints text.
		// Add more variables or function.
	}
}
