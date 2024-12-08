/**
*	NanoShell, Simple UNIX shell made in Java.
*	Copyright (C) 2024  Dv5N
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package com.HappyCow.NanoShell;

import java.util.Scanner; // Import for running commands.
import java.io.File; // Import for files and directories management.

// Local imports.
import com.HappyCow.SimpleClock.Clock; // For the clock command.
import com.HappyCow.ShellUtilities.FolderManagement; // For shell utilities (e.g. mkdir, rmdir, touch, cat)
import com.HappyCow.ShellUtilities.NetworkUtilities; // For the ping and wget command.
import com.HappyCow.ShellUtilities.MoreUtilities; // For more shell utilities (e.g. find, stat, edit)
import com.HappyCow.Plugins.PluginManager.PluginManager; // For loading plugins.

/**
* Main class.
*/
public class NanoShell
{
	private final String helpPrefix = "#=================================#";

	private final String welcomeText = "#=============================================#\n"+
									"# NanoShell, see \"help\" for more information. #\n"+
									"#=============================================#";

	private static File currentDirectory = new File(System.getProperty("user.dir"));

	public final String version = "0.8_alpha";
	public final String promptColor = "\033[32m";
	public static File getCurrentDir() {return currentDirectory;}

	public static void main(String[] args)
	{
		new File(LogDog.LogFile).delete(); // Delete the log file, if it exists.
		LogDog.log("NanoShell | LogDog"); // Start of the log file.
		new NanoShell().runShell("HappyCow"); // Run the shell with the specified prompt.
	}

	/**
	* Main shell function.
	*/
	public void runShell(String prompt)
	{
		System.out.println(welcomeText);
		final Scanner cmdScanner = new Scanner(System.in);

		while (true)
		{
			System.out.print(promptColor+prompt+": "+"\033[0m");
			final String cmd = cmdScanner.nextLine().trim();

			if (cmd.isEmpty())
			{
				continue;
			}

			else if (cmd.equals("exit"))
			{
				System.out.println("Exiting now... Be happy :-)");
				LogDog.log("Exit...");
				break;
			}
			executeCommand(cmd);
		}
		cmdScanner.close();
	}

	/**
	* Function to handle commands.
	*/
	private void executeCommand(String cmd)
	{
		MoreUtilities.addCommandToHistory(cmd);

		if (cmd.contains("&&"))
		{
			String[] commands = cmd.split("&&");
			for (String singleCommand : commands)
			{
				singleCommand = singleCommand.trim();
				if (!singleCommand.isEmpty())
				{
					executeCommand(singleCommand);
				}
			}
			return;
		}

		if (cmd.contains(" "))
		{
			String[] cmdParts = cmd.split(" ", 2);
			String command = cmdParts[0].trim();
			String argument = cmdParts[1].trim();

			switch (command)
			{
				case "echo":
					System.out.println(argument); // Displays the specified text.
					break;
				case "cd":
					changeDir(argument); // Change directory.
					break;
				case "ls":
					FolderManagement.list(argument); // Lists contents from the specified path.
					break;
				case "touch":
					FolderManagement.touch(argument); // Create empty file.
					break;
				case "mkdir":
					FolderManagement.mkdir(argument); // Create empty folder.
					break;
				case "rmdir":
					FolderManagement.rmdir(argument); // Remove empty folder.
					break;
				case "rm":
					FolderManagement.rm(argument); // Remove a specified file.
					break;
				case "cat":
					FolderManagement.cat(argument); // Displays a specified file's text.
					break;
				case "ping":
					NetworkUtilities.ping(argument); // Contact a specified host.
					break;
				case "find":
					MoreUtilities.find(argument); // Find a specified file.
					break;
				case "stat":
					MoreUtilities.stat(argument); // Display information about a specified file.
					break;
				case "wget":
					NetworkUtilities.wget(argument); // Download a file from the internet.
					break;
				case "loadplugin":
					PluginManager.loadPlugin(argument); // Load a specified specified plugin.
					break;
				case "prompt":
					runShell(argument); System.exit(0); // Reload the shell with the specified prompt, and make sure to exit.
					break;
				case "settingSet":
					settingSetHandler(argument); // Set the specified setting to a specified value.
					break;
				case "edit":
					MoreUtilities.textEdit(argument); // Simple text editor. TODO: Allow loading from file, and cursor control.
					break;
				case "cp":
					cp_mvHandler(argument, "cp"); // Copy a file to a specified path with the filename.
					break;
				case "mv":
					cp_mvHandler(argument, "mv"); // Move (or rename) a file to a specified path with the filename.
					break;
				case "tree":
					MoreUtilities.tree(argument); // Shows the directory structure.
					break;
				case "sleep":
					sleepHandler(argument); // Sleep (stop execution) for the specified amount of time.
					break;
				case "calc":
					System.out.println(MoreUtilities.calc(argument));
					break;
				default:
					System.out.println("Unknown command: "+cmd);
					System.out.println("Type \"help\" for a list of commands.");
					LogDog.log("Unknown command: "+cmd);
			}
		}
		else
		{
			handleMoreCommands(cmd);
		}
	}

	private void handleMoreCommands(String cmd)
	{
		switch (cmd)
		{
			case "help":
				// Using just one call to "System.out.println(...);" for efficiency.
				System.out.println(helpPrefix+"\n# NanoShell, version: "+version+"\n"+helpPrefix+
					"\n(Note: (o) = Optional argument)\n"+
					"Commands:\n"+
					"1. exit - Exits the shell.\n"+
					"2. help - Shows a list of commands.\n"+
					"3. echo <text> - Displays <text>.\n"+
					"4. ls (o)<path> - Lists contents in the specified path.\n"+
					"5. cd <path> - Change current directory to <path>.\n"+
					"6. pwd - Displays the full directory path.\n"+
					"7. clear - Clear the display.\n"+
					"8. clock - Displays the current date and time.\n"+
					"9. touch <filename> - Create a file.\n"+
					"10. mkdir <name> - Create a folder.\n"+
					"11. rmdir <name> - Delete a empty folder\n"+
					"12. rm <filename> - Delete a file\n"+
					"13. cat <filename> - Display a text file.\n"+
					"14. ping <host> - Test connection (i.e. ping a host)\n"+
					"15. find <filename> - Find the specified file.\n"+
					"16. stat <filename> - Display file information.\n"+
					"17. wget <host> - Download the specified file.\n"+
					"18. loadplugin <plugin> - Load the specified plugin\n"+
					"19. history - Shows the history of previously run commands.\n"+
					"20. systeminfo - Shows information about the system\n"+
					"21. settingSet <setting> <value> - Sets settings to specified values.\n"+
					"22. settingList - Shows available settings.\n"+
					"23. prompt <prompt> - Restarts the shell with the specified prompt.\n"+
					"24. edit <filename> - Simple text editor. (Can only create new files)\n"+
					"25. cp <file> <dest> - Copy specified file to specified folder.\n"+
					"26. mv <file> <dest> - Move specified file to specified folder.\n"+
					"27. tree <folder> - Shows the directory structure of the a folder.\n"+
					"28. sleep <time> - Sleep for the specified amount of time.\n"+
					"29. pause - Pause execution with \"Press any key to continue...\"\n"+
					"30. discover - Search for plugins in the default plugin directory.\n"+
					"31. netstat - Display information about network trafic.\n"+
					"32. calc <math> - Simple calculator (usage: calc <math(e.g. 5+3*9)>)");
				break;
			case "echo":
				System.out.println("Use: echo <text>"); // Default if no text is provided.
				break;
			case "cd":
				System.out.println("Use: cd <path>"); // Default if no path is provided.
				break;
			case "clear":
				System.out.print("\033[H\033[2J\033[3J"); // Clear the screen and scrollback.
				System.out.flush(); // Make sure the screen is cleared.
				break;
			case "clock":
				if (SettingsManager.hr12Format)
				{
					new Clock().Simple12hrClock();
				}
				else
				{
					new Clock().SimpleClock();
				}
				break;
			case "pwd":
				System.out.println(currentDirectory.getAbsolutePath()); // Displays the full path.
				break;
			case "touch":
				System.out.println("Use: touch <filename>"); // Default if no filename is provided.
				break;
			case "mkdir":
				System.out.println("Use: mkdir <folder name>"); // Default if no folder is provided.
				break;
			case "discover":
				PluginManager.discover();
				break;
			case "rmdir":
				System.out.println("Use: rmdir <folder name>"); // Default if no folder is provided.
				break;
			case "rm":
				System.out.println("Use: rm <filename>"); // Default if no filename is provided.
				break;
			case "cat":
				System.out.println("Use: cat <filename>"); // Default if no filename is provided.
				break;
			case "ping":
				System.out.println("Use: ping <host>"); // Default if no host is provided.
				break;
			case "prompt":
				System.out.println("Use: prompt <prompt name>"); // Default if no prompt is provided.
				break;
			case "settingList":
				SettingsManager.getSetting();
				break;
			case "netstat":
				NetworkUtilities.netstat();
				break;
			case "settingSet":
				System.out.println("Use: settingSet <setting> <value>"); // Default if no setting or value is provided.
				break;
			case "find":
				System.out.println("Use: find <filename>"); // Default if no filename is provided.
				break;
			case "calc":
				System.out.println("Use: calc <math>");
				break;
			case "cp":
				System.out.println("Use: cp <filename> <destination>"); // Default if no filename is provided.
				break;
			case "mv":
				System.out.println("Use: mv <filename> <destination"); // Default if no filename is provided.
				break;
			case "tree":
				System.out.println("Use: tree <folder>"); // Default if no folder is provided.
				break;
			case "sleep":
				System.out.println("Use: sleep <time>"); // Default if no time is provided.
				break;
			case "pause":
				MoreUtilities.pause();
				break;
			case "edit":
				System.out.println("Use: edit <filename>"); // Default if no filename is provided.
				break;
			case "stat":
				System.out.println("Use: stat <filename>"); // Default if no file is provided.
				break;
			case "wget":
				System.out.println("Use: wget <host>"); // Default if no host is provided.
				break;
			case "history":
				MoreUtilities.showHistory(); // Shows the history of previously run commands.
				break;
			case "systeminfo":
				MoreUtilities.systeminfo(); // Shows information about the system.
				break;
			case "ls":
				FolderManagement.list(""); // List contents from the current directory.
				break;
			case "loadplugin":
				System.out.println("Use: loadplugin <plugin>\nExample: loadplugin com.HappyCow.Plugins.ExamplePlugin"); // Default if no plugin is provided.
				break;
			default:
				System.out.println("Unknown command: "+cmd);
				System.out.println("Type \"help\" for a list of commands.");
				LogDog.log("Unknown command: "+cmd);
			}
		}

	/**
	* Function to change directory to specified path.
	*
	* @param path The target directory path to change to.
	*/
	private void changeDir(String path)
	{
		File newDirectory = new File(currentDirectory, path);
		if (newDirectory.isDirectory() && newDirectory.exists())
		{
			currentDirectory = newDirectory;
		}
		else
		{
			System.out.println("Directory not found: "+path+"\nPlease verify the path and try again.");
		}
	}

	private void settingSetHandler(String argument)
	{
		String[] settingsParts = argument.split(" ", 2);
		if (settingsParts.length == 2)
		{
			boolean value = Boolean.parseBoolean(settingsParts[1]);
			SettingsManager.setSetting(settingsParts[0], value);
		}
	}

	private void cp_mvHandler(String argument, String action)
	{
		String[] settingsParts = argument.split(" ", 2);
		if (settingsParts.length == 2)
		{
			if (action == "cp")
			{
				MoreUtilities.cp(settingsParts[0], settingsParts[1]);
			}
			else if (action == "mv")
			{
				MoreUtilities.mv(settingsParts[0], settingsParts[1]);
			}
		}
	}

	private void sleepHandler(String argument)
	{
		try
		{
			int seconds = Integer.parseInt(argument);
			MoreUtilities.sleep(seconds);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Error while running sleep: "+e.getMessage());
			com.HappyCow.NanoShell.LogDog.log("Exception in NanoShell (Main):\n"+e.toString());
			if (com.HappyCow.NanoShell.SettingsManager.IsDeveloperMode)
			{
				e.printStackTrace();
			}
		}
	}
}
