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

		String userID = User.GenerateUserID();

		try
			{
			Connection connect = DriverManager.getConnection(DataAccess.databaseConnection);
			Statement statement = connect.createStatement();
			statement.executeUpdate("INSERT INTO");
			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}

		return(new User(username,email,password,alias,firstName,lastName));
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
