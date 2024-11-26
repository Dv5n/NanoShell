package com.HappyCow.NanoShell;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class LogDog
{
	public static final String LogFile = "NanoShell.log";

	public static void log(String message)
	{
		if (NanoShell.Developer)
		{
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true)))
			{
				writer.write(message);
				writer.newLine();
			}
			catch (IOException e)
			{
			System.out.println("Error writing to log file: "+e.getMessage());
			}
		}
	}
}