#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <dlfcn.h>
#include "include/plugin.h"

#define PLUGIN_DIR	"plugins"

Plugin* loadplugin(const char* pluginName)
{
	if (pluginName == NULL) return NULL;

	void* handle = dlopen(pluginName, RTLD_LAZY);
	if (!handle)
	{
		fprintf(stderr, "Error loading plugin: %s\n", dlerror());
		return NULL;
	}

	Plugin* plugin = (Plugin*)dlsym(handle, "plugin");
	const char* error = dlerror();
	if (error)
	{
		fprintf(stderr, "Error finding symbol: %s\n", error);
		dlclose(handle);
		return NULL;
	}
	return plugin;
}

void discover()
{
	DIR *dir = opendir(PLUGIN_DIR);
	if (dir == NULL)
	{
		perror("discover");
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
				dlclose(handle);
				return;
			}

			printf("Discovered plugin: %s\n", plugin_path);
			printf("\tName       : %s\n", info->name);
			printf("\tDescription: %s\n", info->description);
			printf("\tVersion    : %s\n", info->version);

			dlclose(handle);
		}
	}
	closedir(dir);
}