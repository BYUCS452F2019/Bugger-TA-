package bugger.dataModel;

import java.lang.StringBuilder;
import java.util.Random;
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
    private static final String alpNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

	public User(String userID, String username, String email, String password, String alias, String firstName, String lastName)
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
	public static String GenerateUserID(String firstName,String lastName)
		{
		StringBuilder returnValue = new StringBuilder();

		returnValue.append(firstName.charAt(0));
		returnValue.append(lastName.charAt(0));
		
		long seed = (DataAccess.salt * firstName.charAt(0) + lastName.charAt(lastName.length()-1) );

		Random gen = new Random();
		gen.setSeed(seed);

		for(int i = 0; i < 250; i++)
			{
			int indexAlpNum = (int)(gen.nextFloat() * (alpNum.length() - 1));
			returnValue.append(alpNum.charAt(indexAlpNum));
			}

		return(returnValue.toString());
		}

	//Hashes a password
	public static String HashPassword(String password)
		{
		StringBuilder returnValue = new StringBuilder();
		long seed = (DataAccess.salt * password.charAt(0) + password.charAt(password.length()-1) );

		Random gen = new Random();
		gen.setSeed(seed);

		for(int i = 0; i < 255; i++)
			{
			int indexAlpNum = (int)(gen.nextFloat() * (alpNum.length() - 1));
			returnValue.append(alpNum.charAt(indexAlpNum));
			}

		return(returnValue.toString());
		}
	}
