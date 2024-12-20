#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include "../include/plugin.h"

void execute()
{
	time_t now;
	struct tm* tm_struct;

	while (1)
	{
		now = time(NULL);
		tm_struct = localtime(&now);

		if (tm_struct == NULL) return;

		printf("\033[H\033[2J\033[3J"
				"#=[Date&Time]==============#\n"
				"# %s"
				"#=[NanoShell]==============#\n",asctime(tm_struct));
		sleep(1);
	}
}

Plugin plugin =
{
	.name = "ClockScreensaver",
	.description = "Simple clock screensaver.",
	.version = "1.1",
	.execute = execute
};
