// Use this as an example!
package com.HappyCow.Plugins;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.HappyCow.Plugins.PluginManager.Plugin;

public class ClockScreensaver implements Plugin
{
	@Override
	public void execute()
	{
		new ClockScreensaver().Screensaver();
	}

	public void Screensaver()
	{
		DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		while (true)
		{
			LocalDateTime localTime = LocalDateTime.now();
			String localClock = localTime.format(clockFormat);

			System.out.print("\033[H\033[2J\033[3J"); // Clear the screen and scrollback.
			System.out.flush();

			System.out.println("#=[Clock]=============#\n"+
							"# "+localClock+" #\n"+
							"#=====================#");
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				System.out.println("Clock interrupted...");
				break;
			}
		}
	}
}
