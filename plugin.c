#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <dlfcn.h>
#include "include/plugin.h"

#define PLUGIN_DIR	"plugins"

Plugin* loadplugin(const char* pluginName)
{
	// If the pluginName is empty. (null)
	if (pluginName == NULL) return NULL;

	void* handle = dlopen(pluginName, RTLD_LAZY);
	if (!handle)
	{
		fprintf(stderr, "Error loading plugin: %s\n", dlerror());
		return NULL; // Plugin failed to load.
	}

	Plugin* plugin = (Plugin*)dlsym(handle, "plugin");
	const char* error = dlerror();
	if (error)
	{
		fprintf(stderr, "Error finding symbol: %s\n", error);
		dlclose(handle);
		return NULL; // Plugin failed to load.
	}
	return plugin; // Plugin loaded successfully.
}

void discover()
{
	DIR *dir = opendir(PLUGIN_DIR); // Open the directory.
	if (dir == NULL)
	{
		perror("Error");
		return;
	}

	struct dirent* entry;
	char plugin_path[64];

	printf("Scanning directory: %s\n", PLUGIN_DIR);
	while ((entry = readdir(dir)) != NULL)
	{
		if (strstr(entry->d_name, ".so"))
		{
			snprintf(plugin_path, sizeof(plugin_path), "%s/%s", PLUGIN_DIR, entry->d_name);

			void *handle = dlopen(plugin_path, RTLD_LAZY);
			if (!handle)
			{
				fprintf(stderr, "Failed to load plugin for discovery: %s\n", dlerror());
				return;
			}

			Plugin* info = dlsym(handle, "plugin");
			if (!info)
			{
				fprintf(stderr, "Failed to find plugin in %s: %s\n", plugin_path, dlerror());
				dlclose(handle); // Close the handle to prevent memory leaks.
				return;
			}

			printf("Discovered plugin: %s\n", plugin_path);
			printf("\tPlugin Name: %s\n", info->name);
			printf("\tDescription: %s\n", info->description);
			printf("\tVersion    : %s\n\n", info->version);

			dlclose(handle); // Close the handle to prevent memory leaks.
		}
	}
	closedir(dir); // Close the directory to prevent memory leaks.
}