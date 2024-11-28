package com.HappyCow.Plugins.PluginManager;

import java.lang.reflect.InvocationTargetException;

/**
* PluginManager class, for plugin management.
*/
public class PluginManager
{
	public static void loadPlugin(String pluginName)
	{
		try
		{
			Class<?> pluginClass = Class.forName(pluginName);
			Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
			plugin.execute();
		}

		catch (ClassNotFoundException e)
		{
			System.out.println("Plugin class not found: "+pluginName);
			LogDog.log("Exception in PluginManager:\n"+e.toString());
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			System.out.println("Error instantiating the plugin: "+e.getMessage());
			LogDog.log("Exception in PluginManager:\n"+e.toString());
		}
		catch (NoSuchMethodException | InvocationTargetException e)
		{
			System.out.println("Error invoking plugin constructor: "+e.getMessage());
			LogDog.log("Exception in PluginManager:\n"+e.toString());
		}
		catch (Exception e)
		{
			System.out.println("Error loading plugin: "+e.getMessage());
			LogDog.log("Exception in PluginManager:\n"+e.toString());
		}
	}
}
