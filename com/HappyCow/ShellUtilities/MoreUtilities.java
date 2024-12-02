package com.HappyCow.ShellUtilities;

import java.io.File;
import java.io.Console;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.nio.file.StandardCopyOption;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import com.HappyCow.NanoShell.NanoShell;

/**
* MoreUtilities class, More functions and utilities.
*/
public class MoreUtilities
{
	public static List<String> commandHistory = new ArrayList<>();
	private static final int MaxHistorySize = 64;

	private static StringBuilder textBuffer = new StringBuilder();

	/**
	* Function to find a file.
	*
	* @param filename The name of the file.
	*/
	public static void find(String filename)
	{
		findRecursive(NanoShell.getCurrentDir(), filename);
	}

	/**
	* Helper function to "find(...)"
	*
	* @param dir The folder to find the file.
	* @param filename The name of the file.
	*/
	private static void findRecursive(File dir, String filename)
	{
		File[] files = dir.listFiles();
		if (files != null)
		{
			for (File file : files)
			{
				if (file.getName().equals(filename))
				{
					System.out.println("Found: "+file.getAbsolutePath());
				}
				if (file.isDirectory())
				{
					findRecursive(file, filename);
				}
			}
		}
	}

	/**
	* Function to display information about files.
	*
	* @param filename The name of the file.
	*/
	public static void stat(String filename)
	{
		File file = new File(NanoShell.getCurrentDir(), filename);
		if (file.exists())
		{
			System.out.println("Name: "+file.getName());
			System.out.println("Path: "+file.getAbsolutePath());
			System.out.println("Size: "+file.length()+" bytes");
			System.out.println("Last Modified: "+ new java.util.Date(file.lastModified()));
			System.out.println("Readable: "+file.canRead());
			System.out.println("Writable: "+file.canWrite());
			System.out.println("Executable: "+file.canExecute());
		}
		else
		{
			System.out.println("File not found: "+filename);
		}
	}

	/**
	* Function to add commands to history.
	*
	* @param cmd The Command to include.
	*/
	public static void addCommandToHistory(String cmd)
	{
		commandHistory.add(cmd);
		if (commandHistory.size() > MaxHistorySize)
		{
			commandHistory.remove(0);
		}
	}

	/**
	* Function to list the commands from the history.
	*/
	public static void showHistory()
	{
		commandHistory.forEach(System.out::println);
	}

	/**
	* Function to display information about the system.
	*/
	public static void systeminfo()
	{
		System.out.println("OS: "+System.getProperty("os.name")+
						"\nArchitecture: "+System.getProperty("os.arch")+
						"\nAvailable cores: "+Runtime.getRuntime().availableProcessors()+
						"\nMemory (free/total): "+(Runtime.getRuntime().freeMemory() / 1024 / 1024) +" MB / "+
						(Runtime.getRuntime().totalMemory() / 1024 / 1024) +" MB");

		File root = new File("/");
		long totalSpace = root.getTotalSpace();
		long freeSpace = root.getFreeSpace();
		System.out.println("Disk (free/total): "+(freeSpace / 1024 / 1024) + " MB / "+
						(totalSpace / 1024 / 1024) + " MB");
	}

	/**
	* Simple text editor, can only create files
	*
	* @param filename The name of the file to edit.
	*/
	public static void textEdit(String filename)
	{
		Console console = System.console();
		if (console == null)
		{
			System.out.println("No console available.");
			return;
		}

		System.out.println("+---------------------[Text-Editor]---------------------+\nType \"<$-exit\" to exit, or \"<$-exitNsave\" to exit without saving.");

		String line;
		StringBuilder text = new StringBuilder();
		while (true)
		{
			line = console.readLine(">");
			if (line.equals("<$-exit"))
			{
				save(filename);
				break;
			}
			if (line.equals("<$-exitNsave"))
			{
				break;
			}
			textBuffer.append(line).append("\n");
		}
	}

	/**
	* Helper function to edit(...)
	*
	* @param filename The name of the file saved.
	*/
	private static void save(String filename)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
		{
			writer.write(textBuffer.toString());
			System.out.println("File saved.");
		}
		catch (IOException e)
		{
			System.out.println("Error while saving to file: "+e.getMessage());
			com.HappyCow.NanoShell.LogDog.log("Exception in MoreUtilities:\n"+e.toString());
			if (com.HappyCow.NanoShell.SettingsManager.IsDeveloperMode)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	* Function to copy files to destination
	*/
	public static void cp(String source, String destination)
	{
		File src = new File(source);
		File dest = new File(destination);

		if (!src.exists())
		{
			System.out.println("Source file does not exist.");
			return;
		}

		if (src.isDirectory())
		{
			System.out.println("Cannot copy a directory.");
			return;
		}

		try
		{
			Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File copied successfully.");
		}
		catch (IOException e)
		{
			System.out.println("Error copying file: "+e.getMessage());
		}
	}

	/**
	* Function to move files to destination
	*/
	public static void mv(String source, String destination)
	{
		File src = new File(source);
		File dest = new File(destination);

		if (!src.exists())
		{
			System.out.println("Source file does not exist.");
			return;
		}

		if (src.isDirectory() && !dest.isDirectory())
		{
			System.out.println("Cannot copy a directory.");
			return;
		}

		if (src.renameTo(dest))
		{
			System.out.println("File moved successfully.");
		}
		else
		{
			System.out.println("Error moving file.");
		}

	}

	/**
	* Function to display the directory structure of a directory.
	*/
	public static void tree(String path)
	{
		treeRecursive(path, 0);
	}
	private static void treeRecursive(String path, int level)
	{
		File dir = new File(path);

		if (!dir.exists() || !dir.isDirectory())
		{
			System.out.println("Invalid directory: "+path);
			return;
		}

		String indent = " ".repeat(level * 2);

		for (File file : dir.listFiles())
		{
			System.out.println(indent + file.getName());

			if (file.isDirectory())
			{
				treeRecursive(file.getAbsolutePath(), level + 1);
			}
		}
	}

	/**
	* Function to pause execution for the specified amount of time.
	*/
	public static void sleep(int seconds)
	{
		if (seconds < 0)
		{
			System.out.println("Invalid time.");
			return;
		}

		try
		{
			Thread.sleep(seconds * 1000); // Convert to milliseconds.
		}
		catch (InterruptedException e)
		{
			System.out.println("Sleep interrupted.");
			com.HappyCow.NanoShell.LogDog.log("Exception in MoreUtilities:\n"+e.toString());
			if (com.HappyCow.NanoShell.SettingsManager.IsDeveloperMode)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	* Function to pause execution until the user types a key.
	*/
	public static void pause()
	{
		System.out.print("Press any key to continue... ");
		try
		{
			System.in.read();
		}
		catch (IOException e)
		{
			System.out.println("Error: "+e.getMessage());
			com.HappyCow.NanoShell.LogDog.log("Exception in MoreUtilities:\n"+e.toString());
			if (com.HappyCow.NanoShell.SettingsManager.IsDeveloperMode)
			{
				e.printStackTrace();
			}
		}
	}
}
