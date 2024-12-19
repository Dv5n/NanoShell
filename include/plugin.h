#ifndef PLUGIN_H
#define PLUGIN_H

typedef struct
{
	const char* name;
	const char* description;
	const char* version;

	void (*execute)();
} Plugin;

#endif // PLUGIN_H