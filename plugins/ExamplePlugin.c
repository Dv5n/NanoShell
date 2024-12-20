#include <stdio.h>
// Include the plugin header for exporting the plugin details.
#include "../include/plugin.h"

// Function that is called when loading plugins.
void execute()
{
	printf("Big Fat Cat.\n"); // Print text.
	// Add more here...
}

// Export details for plugin discovery.
Plugin plugin =
{
	.name = "ExamplePlugin",
	.description = "Use this as an example!",
	.version = "1.0",
	.execute = execute
};
