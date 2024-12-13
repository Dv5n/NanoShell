package com.HappyCow.ShellUtilities;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import com.HappyCow.NanoShell.NanoShell;

/**
* FolderManagement class, for creating or removing files or folders.
*/
public class FolderManagement
{
	/**
	* Function to create a empty file.
	*
	* @param filename The file name to create.
	*/
	public static void touch(String filename)
	{
		File file = new File(NanoShell.getCurrentDir(), filename);
		try
		{
			if (!file.createNewFile()) System.out.println("File already exists.");
		}
		catch (IOException e)
		{
			System.out.println("Error creating file: "+e.getMessage());
			com.HappyCow.NanoShell.LogDog.log("Exception in FolderManagement:\n"+e.toString());
			if (com.HappyCow.NanoShell.SettingsManager.IsDeveloperMode) e.printStackTrace();
		}
	}

	/**
	* Function to delete a file.
	*
	* @param filename The file name to delete.
	*/
	public static void rm(String filename)
	{
		File file = new File(NanoShell.getCurrentDir(), filename);
		if (file.exists() && file.isFile()) if (!file.delete()) System.out.println("Failed to delete file.");

		else System.out.println("File not found: "+filename);
	}

	/**
	* Function to create a empty folder
	*
	* @param dirname The folder name to create.
	*/
	public static void mkdir(String dirname)
	{
		File dir = new File(NanoShell.getCurrentDir(), dirname);
		if (!dir.mkdir()) System.out.println("Failed to create directory or it already exists.");
	}

	/**
	* Function to remove a folder.
	*
	* @param dirname The folder name to delete.
	*/
	public static void rmdir(String dirname)
	{
		File dir = new File(NanoShell.getCurrentDir(), dirname);
		if (dir.exists() && dir.isDirectory()) if (!dir.delete()) System.out.println("Failed to delete directory. Make sure it is empty.");

		else System.out.println("Directory not found:"+dirname);
	}

	/**
	* Function to view a file.
	*
	* @param filename The file name to view.
	*/
	public static void cat(String filename)
	{
		File file = new File(NanoShell.getCurrentDir(), filename);
		if (file.exists() && file.isFile())
		{
			try (Scanner fileScanner = new Scanner(file))
			{
				while (fileScanner.hasNextLine())
				{
					System.out.println(fileScanner.nextLine());
				}
			}
			catch (IOException e)
			{
				System.out.println("Error reading file: "+e.getMessage());
				com.HappyCow.NanoShell.LogDog.log("Exception in FolderManagement:\n"+e.toString());
				if (com.HappyCow.NanoShell.SettingsManager.IsDeveloperMode) e.printStackTrace();
			}
		}
		else System.out.println("File not found: "+filename);
	}

	/**
	* Function to list files and directories. If a path is provided, it lists the contents
	* of the specified directory. If no path is provided it lists the contents from the current directory.
	*
	* @param path Optional directory path to list contents from.
	*/
	public static void list(String path)
	{
		File directory = path.isEmpty() ? NanoShell.getCurrentDir() : new File(NanoShell.getCurrentDir(), path);

		if (!directory.exists() || !directory.isDirectory())
		{
			System.out.println("Directory not found: "+path);
			return;
		}

		File[] files = directory.listFiles();
		if (files != null && files.length > 0)
		{
			for (File file : files)
			{
				System.out.println((file.isDirectory() ? "[dir ]: " : "[file]: ") + file.getName());
			}
		}
		else System.out.println("No files or directories found.");
	}
}
