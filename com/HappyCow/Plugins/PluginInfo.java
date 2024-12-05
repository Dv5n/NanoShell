package com.HappyCow.Plugins;

import com.HappyCow.Plugins.PluginManager.Plugin;
import com.HappyCow.Plugins.PluginManager.PluginManager;
import com.HappyCow.Plugins.PluginManager.PluginMetadata;

// Plugin metadata for discovery.
@PluginMetadata(name = "PluginInfo", description = "Shows information about the plugin system.", version = "1.0")

/**
* PluginInfo class, information about the plugin system.
*/
public class PluginInfo implements Plugin
{
	@Override
	public void execute()
	{
		System.out.println("Api Version: "+PluginManager.API_VERSION);
		System.out.println("Incompatible Api Versions: "+PluginManager.INCOMPATIBLE_API_VERSIONS);
	}
}
