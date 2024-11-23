package com.HappyCow.Plugins.PluginManager;

import java.lang.reflect.InvocationTargetException;

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
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			System.out.println("Error instantiating the plugin: "+e.getMessage());
		}
		catch (NoSuchMethodException | InvocationTargetException e)
		{
				System.out.println("Error invoking plugin constructor: "+e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error loading plugin: "+e.getMessage());
		}
	}
}
