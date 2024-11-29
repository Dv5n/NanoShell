package com.HappyCow.NanoShell;

public class SettingsManager
{
	// Predefined settings.
	public static boolean LogDogEnable = false;
	public static boolean IsDeveloperMode = false;

	// List the setting values.
	public static void getSetting()
	{
		System.out.println("LogDogEnable = "+LogDogEnable+
						"\nIsDeveloperMode = "+IsDeveloperMode);
	}

	// Set the specified setting value.
	public static void setSetting(String Setting, boolean value)
	{
		switch (Setting)
		{
			case "LogDogEnable":
				LogDogEnable = value;
				break;
			case "IsDeveloperMode":
				IsDeveloperMode = value;
				break;
			default:
				System.out.println("Setting not found: "+Setting);
				break;
		}
	}
}
