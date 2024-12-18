// Licensed under GPL (General Public License)
/*
Copyright (C) 2024 Dv5n

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

#include "include/shell.h"

void NanoShell(const char* prompt)
{
	running = 1;
	while (running)
	{
		printf("%s", prompt);
		fgets(cmd, BUFFER, stdin);

		cmd[strcspn(cmd, "\n")] = 0;

		if (strlen(cmd) == 0) continue; // Skip empty input.

		// Split, the actual command, and one argument, for now.
		char* command = strtok(cmd, " ");
		char* arg1 = strtok(NULL, " ");

		if (command != NULL)
		{
			if (strcmp(command, "exit") == 0)
			{
				printf("Goodbye, have a good day!\n");
				running = 0;
			}

			else if (strcmp(command, "help") == 0)
			{
				if (arg1 != NULL) MoreHelp(arg1);
				else
				{
					printf("#=============================#\n"
							"#\tNanoShell %s\n"
							"#=============================#\n"
							"Simple shell, but with many useful features!\n"
							"See also \"help help\" for more information.\n"
							"Commands:\n"
							"1.  help    = Shows information about commands.\n"
							"2.  exit    = Exits the shell.\n"
							"3.  clear   = Clears the screen.\n"
							"4.  ls      = Lists files in the current directory.\n"
							"5.  cd      = Changes to specified directory.\n"
							"6.  pwd     = Displays current working directory.\n"
							"7.  mkdir   = Creates a folder.\n"
							"8.  rmdir   = Removes an empty folder.\n"
							"9.  rm      = Removes a file.\n"
							"10. touch   = Creates an empty file.\n"
							"11. date    = Displays date and time.\n"
							"12. lplugin = Loads a specified plugin.\n"
							"13. cat     = Prints the text in the specified file.\n"
							"14. stat    = Displays information about the specified file.\n"
							"15. tree    = Displays the specified directory structure.\n"
							"16. sysinfo = Displays information about the system\n", version);
				}
			}

			else if (strcmp(command, "clear") == 0) printf(CLEAR_SCREEN);

			else if (strcmp(command, "pwd") == 0) Pwd();

			else if (strcmp(command, "date") == 0) Date();

			else if (strcmp(command, "sysinfo") == 0) Sysinfo();

			else if (strcmp(command, "lplugin") == 0)
			{
				if (arg1 != NULL)
				{
					Plugin* plugin = loadplugin(arg1);  // Load the plugin.
					if (!plugin) printf("Try again.\n");
					else plugin->execute(); // Run the plugin.
				}

				else printf("Use: lplugin <plugin name>\n");
			}

			else if (strcmp(command, "ls") == 0)
			{
				if (arg1 != NULL) Ls(arg1);

				else Ls(".");
			}

			else if (strcmp(command, "cd") == 0)
			{
				if (arg1 != NULL) Cd(arg1);

				else printf("Use: cd <path>\n");
			}

			else if (strcmp(command, "cat") == 0)
			{
				if (arg1 != NULL) Cat(arg1);

				else printf("Use: cat <filename>\n");
			}

			else if (strcmp(command, "tree") == 0)
			{
				if (arg1 != NULL) Tree(arg1);

				else printf("Use: tree <directory>\n");
			}

			else if (strcmp(command, "stat") == 0)
			{
				if (arg1 != NULL) Stat(arg1);

				else printf("Use: stat <filename>\n");
			}

			else if (strcmp(command, "mkdir") == 0)
			{
				if (arg1 != NULL) Mkdir(arg1, 0755);

				else printf("Use: mkdir <folder>\n");
			}

			else if (strcmp(command, "rmdir") == 0)
			{
				if (arg1 != NULL) Rmdir(arg1);

				else printf("Use: rmdir <folder>\n");
			}

			else if (strcmp(command, "rm") == 0)
			{
				if (arg1 != NULL) Rm(arg1);

				else printf("Use: rm <filename>\n");
			}

			else if (strcmp(command, "touch") == 0)
			{
				if (arg1 != NULL) Touch(arg1);

				else printf("Use: touch <filename>\n");
			}

			else printf("Command not found: \"%s\"\n"
						"Type \"help\" for information on commands.\n", cmd);
		}
	}
}
