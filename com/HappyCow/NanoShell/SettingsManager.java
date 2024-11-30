package com.HappyCow.NanoShell;

public class SettingsManager
{
	// Predefined settings.
	public static boolean LogDogEnable = true;
	public static boolean IsDeveloperMode = false;
	public static boolean hr12Format = true;

	// List the setting values.
	public static void getSetting()
	{
		System.out.println("LogDogEnable = "+LogDogEnable+
					"\nIsDeveloperMode = "+IsDeveloperMode+
					"\n12hrFormat = "+hr12Format);
	}

	// Set the specified setting value.
	public static void setSetting(String Setting, boolean value)
	{
		switch (Setting)
		{
			case "LogDogEnable":
				LogDogEnable = value;
				LogDog.log("LogDogEnable set to: "+value);
				break;
			case "IsDeveloperMode":
				IsDeveloperMode = value;
				LogDog.log("IsDeveloperMode set to: "+value);
				break;
			case "12hrFormat":
				hr12Format = value;
				LogDog.log("12hrFormat set to: "+value);
				break;
			default:
				System.out.println("Setting not found: "+Setting);
				break;
		}
	}
}
