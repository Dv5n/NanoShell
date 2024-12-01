package com.HappyCow.Plugins.PluginManager;

import java.lang.reflect.InvocationTargetException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.File;

import java.net.URL;
import java.net.URLClassLoader;

/**
* PluginManager class, for plugin management.
*/
public class PluginManager
{
//	public static final String API_VER = "0.2";
	private static final ExecutorService pluginExecutor = Executors.newCachedThreadPool();

	public static void loadPlugin(String pluginName)
	{
		pluginExecutor.submit(() -> {
			try
			{
				Class<?> pluginClass = Class.forName(pluginName);
				Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
				plugin.execute();
			}

			catch (ClassNotFoundException e)
			{
				exceptionHandler("Plugin class not found: "+pluginName, e);
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				exceptionHandler("Error instantiating the plugin: "+e.getMessage(), e);
			}
			catch (NoSuchMethodException | InvocationTargetException e)
			{
				exceptionHandler("Error invoking plugin constructor: "+e.getMessage(), e);
			}
			catch (Exception e)
			{
				exceptionHandler("Error loading plugin: "+e.getMessage(), e);
			}
		});
	}

	public static void shutdown()
	{
		try
		{
			pluginExecutor.shutdownNow();
			System.out.println("PluginManager thread pool shut down...");
		}
		catch (Exception e)
		{
			exceptionHandler("Error shutting down PluginManager: "+e.getMessage(), e);
		}
	}

	private static void exceptionHandler(String message, Exception e)
	{
		System.out.println(message);
		com.HappyCow.NanoShell.LogDog.log("Exception in PluginManager:\n"+e.toString());
		if (com.HappyCow.NanoShell.SettingsManager.IsDeveloperMode)
		{
			e.printStackTrace();
		}
	}

	public static void discover()
	{
		try
		{
			String path = "com/HappyCow/Plugins";

			File pluginDir = new File(path);

			if (!pluginDir.exists() || !pluginDir.isDirectory())
			{
				System.out.println("Plugins directory not found: "+path);
				return;
			}

			System.out.println("Scanning directory: "+pluginDir.getAbsolutePath());

			try (URLClassLoader classLoader = new URLClassLoader(new URL[] { pluginDir.toURI().toURL() }))
			{

				File[] files = pluginDir.listFiles((dir, name) -> name.endsWith(".class"));

				if (files == null || files.length == 0)
				{
					System.out.println("No plugins found in the directory.");
					return;
				}

				for (File file : files)
				{
					String classPath = path.replace("/", ".");
					String className = classPath+"."+file.getName().replace(".class", "");
				
					try
					{
						Class<?> pluginClass = Class.forName(className, true, classLoader);

						if (pluginClass.isAnnotationPresent(PluginMetadata.class))
						{
							PluginMetadata metadata = pluginClass.getAnnotation(PluginMetadata.class);
							System.out.println("Discovered plugin: "+metadata.name()+" - "+metadata.description());
						}
						else
						{
							System.out.println("Class does not have @PluginMetadata: "+className);
						}
					}
					catch (ClassNotFoundException e)
					{
						exceptionHandler("Error loading class: "+className+" - "+e.getMessage(), e);
					}
				}
			}
		}
		catch (Exception e)
		{
			exceptionHandler("Error discovering plugins: "+e.getMessage(), e);
		}
	}
}
