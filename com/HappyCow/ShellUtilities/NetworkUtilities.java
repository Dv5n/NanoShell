package com.HappyCow.ShellUtilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URLConnection;
import java.net.URL;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;

import com.HappyCow.NanoShell.NanoShell;

/**
* NetworkUtilities, Utilities for network tasks like ping and wget.
*/
public class NetworkUtilities
{
	/**
	* Function to ping a host.
	*
	* @param hostname The address to contact.
	*/
	public static void ping(String hostname)
	{
		try
		{
			InetAddress inet = InetAddress.getByName(hostname);
			System.out.println("Pinging "+hostname+" ["+inet.getHostAddress()+"]...");
			System.out.println("Ping successfull.");
		}
		catch (UnknownHostException e)
		{
			System.out.println("Ping failed: "+e.getMessage());
			LogDog.log("Exception in NetworkUtilities:\n"+e.toString());
		}
	}

	/**
	* Function to download a file from the internet (WAN) or LAN
	*
	* @param urlString The address to download.
	*/
	public static void wget(String urlString)
	{
		try
		{
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			String fileName = getFileNameFromURL(urlString);

			try (InputStream in = connection.getInputStream();
				FileOutputStream out = new FileOutputStream(new File(NanoShell.getCurrentDir(), fileName)))
			{
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1)
				{
					out.write(buffer, 0, bytesRead);
				}
				System.out.println("File downloaded: "+fileName);
			}
		}
		catch (IOException e)
		{
				System.out.println("Error downloading file: "+e.getMessage());
				LogDog.log("Exception in NetworkUtilities:\n"+e.toString());
		}
	}

	private static String getFileNameFromURL(String urlString)
	{
		String filename = urlString.substring(urlString.lastIndexOf('/') + 1);
		if (filename.isEmpty())
		{
			filename = "unknown_name";
		}
		return filename;
	}
}
