package bugger.dataAccess;

import java.sql.*;

public class DataAccess
	{
	//Will change this so it can be set somewhere else later
	public static final int salt = 365;

	//Will also make these customizable later
	public static final String databaseUserName = "bugger";
	public static final String databasePassword = "bugger";
	public static final String databaseConnection = "jdbc:mysql://localhost/bugger?user="+databaseUserName+"&password="+databasePassword+"&serverTimezone=UTC";

	//Makes sure that the database is set up correctly. If any tables are missing, or
	//there is not an admin user/admin permission group, then it will create them
	public static void ValidateDatabase()
		{
		try
			{
			Connection connect = DriverManager.getConnection(databaseConnection);
			Statement statement = connect.createStatement();

			//Create user table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS User("
									+ "userID VARCHAR(255) NOT NULL PRIMARY KEY,"
									+ "username VARCHAR(255) NOT NULL,"
									+ "email VARCHAR(255) NOT NULL,"
									+ "password TEXT NOT NULL," 
									+ "alias VARCHAR(255),"
									+ "firstName VARCHAR(255) NOT NULL,"
									+ "lastName VARCHAR(255) NOT NULL)");

			//Create a Table to store user cookies
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cookies("
									+ "cookieID VARCHAR(255) NOT NULL PRIMARY KEY,"
									+ "userID VARCHAR(255) NOT NULL,"
									+ "timestamp DATETIME NOT NULL)");

			//Create User Permission Table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS UserPermission("
									+ "permissionID VARCHAR(255) NOT NULL PRIMARY KEY,"
									+ "userID VARCHAR(255) NOT NULL)");

			//Create a table to store Permission groups
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Permission("
									+ "permissionID VARCHAR(255) NOT NULL PRIMARY KEY,"
									+ "permissionName VARCHAR(255) NOT NULL,"
									+ "discription TEXT)");

			//Create a Table to store Project Information
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Project("
									+ "projectID VARCHAR(255) NOT NULL PRIMARY KEY,"
									+ "projectName VARCHAR(255) NOT NULL,"
									+ "discription TEXT)");

			//Create the admin user/admin permission

			if(UserData.GetUserByUsername("admin") == null)
				{
				System.out.println("admin account not found -- Creating new one. . .");
				UserData.CreateNewUser("admin", "admin@bugger.admin", "admin", "admin", "Sudo", "Su");
				}

			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}
		}
	}
