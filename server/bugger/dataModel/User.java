package bugger.dataModel;

import java.sql.*;
import bugger.dataAccess.DataAccess;

public class User
	{
	public String userID;
	public String username;
	public String email;
	public String password;
	public String alias;
	public String firstName;
	public String lastName;

	public User(String username, String email, String password, String alias, String firstName, String lastName)
		{
		if(username == null || email == null || password == null || firstName == null || lastName == null)
			{
			return;
			}
		this.username = username;
		this.email =  email;
		this.password = password;
		this.alias = alias;
		this.firstName = firstName;
		this.lastName = lastName;
		}

	//Hashes out a userID
	public static String GenerateUserID()
		{
		String returnValue = "";

		return(returnValue);
		}
	}
