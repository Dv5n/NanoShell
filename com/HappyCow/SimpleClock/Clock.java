package com.HappyCow.SimpleClock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock
{
	LocalDateTime localTime = LocalDateTime.now();
	final DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	String localClock = localTime.format(clockFormat);
	final String SimpleClockGraphics[] =
	{
		"#=[Clock]=============#",
		"# "+localClock+" #",
		"#=====================#"
	};

	public static void main(String[] args)
	{
		new Clock().SimpleClock();
	}

	// Graphical clock.
	public void SimpleClock()
	{
		System.out.println(SimpleClockGraphics[0]+"\n"+
				SimpleClockGraphics[1]+"\n"+
				SimpleClockGraphics[2]);
	}

	public void Simple12hrClock()
	{
		final DateTimeFormatter clock12hrFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

		String local12hrClock = localTime.format(clock12hrFormat);

		final String Simple12hrClockGraphics[] =
		{
			"#=[Clock]================#",
			"# "+local12hrClock+" #",
			"#========================#"
		};

		System.out.println(Simple12hrClockGraphics[0]+"\n"+
				Simple12hrClockGraphics[1]+"\n"+
				Simple12hrClockGraphics[2]);
	}

	public static String clock()
	{
		return new Clock().localClock;
	}
}
