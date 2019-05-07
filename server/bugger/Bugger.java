package bugger;

import bugger.handlers.*;
import java.io.*;
import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;

public class Bugger
	{
    public static void main(String[] args)
		{
		//Making sure we have a correct number of arguments
		if(args.length != 1)
			{
			System.out.println("Invalid number of arguments. Please only provide the port number");
			return;
			}

		//Setting up the ports
		int portAddress = Integer.parseInt(args[0]);
		InetSocketAddress port = new InetSocketAddress(portAddress);
		HttpServer server;

		try
			{
	        System.out.println("Starting up Bugger v0.3 [||] on Port: " + portAddress);
			server = HttpServer.create(port,0);
			server.createContext("/user/register", new RegisterHandler());

			//Final setup
			server.setExecutor(null);
			server.start();
			}
		catch(IOException e)
			{
			System.out.println("IOException Detected!");
			e.printStackTrace();
			}

		System.out.println("# -- Running Bugger -- #");
    	}
	}
