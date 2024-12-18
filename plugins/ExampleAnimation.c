#include <stdio.h>
#include <unistd.h>
#include "../include/plugin.h"

void execute()
{
	const char spinner[] = "/-\\|";
	int i = 0;

	printf("Example\n");
	printf("Updating system... ");
	fflush(stdout);

	while (1)
	{
		printf("%c\b", spinner[i]);
		fflush(stdout);
		usleep(200000);
		i = (i+1) % 4;
	}
}

Plugin plugin =
{
	.name = "ExampleAnimation",
	.description = "Use this as an example!",
	.version = "1.0",
	.execute = execute
};
