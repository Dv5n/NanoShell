package com.HappyCow.ShellUtilities;

import java.io.File;
import java.io.IOException;

import com.HappyCow.NanoShell.NanoShell;

public class MoreUtilities
{
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

/* Not important now
	public static void alias(String alias, String command)
	{
		aliases.put(alias, command);
		System.out.println("Alias set: "+alias+"-> "+command);
	}

	public static void unalias(String alias)
	{
		if (aliases.remove(alias) != null)
		{
			System.out.println("Alias removed: "+alias);
		}
		else
		{
			System.out.println("Alias not found: "+alias);
		}
	}
*/

}
