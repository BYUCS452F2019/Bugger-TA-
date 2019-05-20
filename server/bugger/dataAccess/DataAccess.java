package bugger.dataAccess;

import java.sql.*;

public class DataAccess
	{
	public static final String databaseConnection = "jdbc:mysql://localhost/bugger?" + "user=bugger&password=bugger&serverTimezone=UTC";

	//May put this into a seperate class later, but we'll see
	public static void ValidateDatabase()
		{
		try
			{
			Connection connect = DriverManager.getConnection(databaseConnection);
			Statement statement = connect.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS User("
									+ "userID VARCHAR(255) NOT NULL,"
									+ "username VARCHAR(255) NOT NULL,"
									+ "email VARCHAR(255) NOT NULL,"
									+ "password TEXT NOT NULL," 
									+ "alias VARCHAR(255),"
									+ "firstName VARCHAR(255) NOT NULL,"
									+ "lastName VARCHAR(255) NOT NULL)");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS UserPermission("
									+ "permissionID VARCHAR(255) NOT NULL,"
									+ "userID VARCHAR(255) NOT NULL)");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cookies("
									+ "cookieID VARCHAR(255) NOT NULL,"
									+ "userID VARCHAR(255) NOT NULL,"
									+ "timestamp DATETIME NOT NULL)");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Permission("
									+ "permissionID VARCHAR(255) NOT NULL,"
									+ "permissionName VARCHAR(255) NOT NULL,"
									+ "discription TEXT)");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Project("
									+ "projectID VARCHAR(255) NOT NULL,"
									+ "projectName VARCHAR(255) NOT NULL,"
									+ "discription TEXT)");

			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}
		finally
			{
            
        	}
		}
	}
