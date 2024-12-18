#include <stdio.h>
#include "../include/plugin.h"

void execute()
{
	printf("Big Fat Cat.\n");
}

Plugin plugin =
{
	.name = "ExamplePlugin",
	.description = "Use this as an example!",
	.version = "1.0",
	.execute = execute
};
