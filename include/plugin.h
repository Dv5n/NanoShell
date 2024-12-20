#ifndef PLUGIN_H
#define PLUGIN_H

// Plugin interface.
typedef struct
{
	const char* name;
	const char* description;
	const char* version;

	void (*execute)(); // execute function.
} Plugin;

#endif // PLUGIN_H