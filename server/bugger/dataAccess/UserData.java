package bugger.dataAccess;

import java.sql.*;
import bugger.dataModel.User;

public class UserData
	{
	//Creates a new entry into the database
	public static User CreateNewUser(String username, String email, String password, String alias, String firstName, String lastName)
		{
		if(username == null || email == null || password == null || firstName == null || lastName == null)
			{
			return null;
			}

		String userID = User.GenerateUserID(firstName,lastName);
		String hashedPassword = User.HashPassword(password);

		try
			{
			Connection connect = DriverManager.getConnection(DataAccess.databaseConnection);
			Statement statement = connect.createStatement();
			statement.executeUpdate("INSERT INTO User(userID,username,email,password,alias,firstName,lastName) VALUES ('"
									+ userID + "','"
									+ username + "','"
									+ email + "','"
									+ hashedPassword + "','"
									+ alias + "','"
									+ firstName + "','"
									+ lastName + "')");
			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}

		return(new User(userID,username,email,hashedPassword,alias,firstName,lastName));
		}

	public static User GetUserByUsername(String username)
		{
		User returnValue = null;

		if(username == null)
			{
			return null;
			}

		try
			{
			Connection connect = DriverManager.getConnection(DataAccess.databaseConnection);
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery("SELECT userID,email,password,alias,firstName,lastName FROM User WHERE username = '"+ username +"'" );

			String userID = null;
			String email = null;
			String password = null;
			String alias = null;
			String firstName = null;
			String lastName = null;

			while(result.next())
				{
				userID = result.getString("userID");
				email = result.getString("email");
				password = result.getString("password");
				alias = result.getString("alias");
				firstName = result.getString("firstName");
				lastName = result.getString("lastName");
				}

			if(username != null && email != null && password != null && firstName != null && lastName != null)
				{
				returnValue = new User(userID,username,email,password,alias,firstName,lastName);
				}
			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}

		return(returnValue);
		}

	public static boolean ValidateUserID()
		{
		boolean returnValue = false;

		return(returnValue);
		}

	private String GenerateUserID()
		{
		String returnValue = "";

		return(returnValue);
		}
	}
