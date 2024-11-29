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
}
