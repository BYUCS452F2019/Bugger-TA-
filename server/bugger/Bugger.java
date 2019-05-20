package bugger;

import bugger.handlers.*;
import bugger.dataAccess.*;
import java.io.*;
import java.sql.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

public class Bugger
	{
	//May change this to a parameter or something later. . . for now this will do
	protected static final String dbName= "../database/buggerDatabase.sqlite";
	protected static final String connectionURL= "jdbc:sqlite:" + dbName;

    public static void main(String[] args)
		{
		//Making sure we have a correct number of arguments
		if(args.length != 1)
			{
			System.out.println("Invalid number of arguments. Please only provide the port number");
			return;
			}

		//Creates the tables in the database if it doesn't exist
		DataAccess.ValidateDatabase();

		//Setting up the ports
		int portAddress = Integer.parseInt(args[0]);
		InetSocketAddress port = new InetSocketAddress(portAddress);
		HttpServer server;

		//Try getting the server all up and running
		try
			{
	        System.out.println("Starting up Bugger v0.3 [||] on Port: " + portAddress);
			//Set up the port
			server = HttpServer.create(port,0);

			//Listen in for requests
			server.createContext("/api/users/login", new LoginHandler());
			server.createContext("/api/users/register", new RegisterHandler());

			server.createContext("/", new RegisterHandler());

			//Execute the database!
			server.setExecutor(null);
			server.start();
			}
		catch(IOException e)
			{
			System.out.println("IOException Detected!");
			e.printStackTrace();
			}

		//Let us know that bugger has started successfully!
		System.out.println("# -- Running Bugger -- #");
    	}
	//End of Bugger Class
	}
