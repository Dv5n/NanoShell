#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
#include "include/plugin.h"

Plugin* loadplugin(const char* pluginN)
{
	void* handle = dlopen(pluginN, RTLD_LAZY);
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