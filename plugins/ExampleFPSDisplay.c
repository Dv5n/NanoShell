#include <stdio.h>
#include <time.h>
#include "../include/plugin.h"

void execute()
{
	clock_t lastTime = time(NULL);
	clock_t currentTime;
	double deltaTime;
	int frames = 0;

	while (1)
	{
		frames++;
		currentTime = time(NULL);
		deltaTime = difftime(currentTime, lastTime);

		if (deltaTime >= 1.0)
		{
			printf("FPS: %d\n", frames);
			frames = 0;
			lastTime = currentTime;
		}

		// Program work here...
	}
}

// Export details for plugin discovery.
Plugin plugin =
{
	.name = "ExampleFPSDisplay",
	.description = "Displays the FPS.",
	.version = "1.0",
	.execute = execute
};
