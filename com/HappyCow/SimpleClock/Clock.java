package com.HappyCow.SimpleClock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock
{
	LocalDateTime localTime = LocalDateTime.now();
	DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	String localClock = localTime.format(clockFormat);
	String SimpleClockGraphics[] =
	{
		"#=[Clock]=============#",
		"# "+localClock+" #",
		"#=====================#"
	};

	public static void main(String[] args)
	{
		new Clock().SimpleClock();
	}

	public void SimpleClock()
	{
		System.out.println(SimpleClockGraphics[0]);
		System.out.println(SimpleClockGraphics[1]);
		System.out.println(SimpleClockGraphics[2]);
	}

	public void HelpClock()
	{
		System.out.println("Clock: "+localClock);
	}
}
