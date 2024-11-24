package com.HappyCow.ShellUtilities;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import com.HappyCow.NanoShell.NanoShell;

public class MoreUtilities
{
	public static List<String> commandHistory = new ArrayList<>();
	private static final int MaxHistorySize = 64;

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
}
