package com.HappyCow.NanoShell;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogDog
{
	public static final String LogFile = "NanoShell.log";

	public static void log(String message)
	{
		if (SettingsManager.LogDogEnable)
		{
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true)))
			{
				writer.write(com.HappyCow.SimpleClock.Clock.clock()+" - "+message);
				writer.newLine();
			}
			catch (IOException e)
			{
				System.out.println("Error writing to log file: "+e.getMessage());
				if (SettingsManager.IsDeveloperMode)
				{
					e.printStackTrace();
				}
			}
		}

		else
		{
			System.out.println("Logging not enabled.");
		}
	}
}
