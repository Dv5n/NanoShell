/*
	SimpleClock, a simple clock made in Java, part of NanoShell.
	Copyright (C) 2024  Dv5N

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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
		for (int i=0; i < SimpleClockGraphics.length; i++)
		{
			System.out.println(SimpleClockGraphics[i]);
		}
	}
}
