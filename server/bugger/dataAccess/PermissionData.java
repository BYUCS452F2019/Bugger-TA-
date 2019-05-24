package bugger.dataAccess;

import java.sql.*;
import java.util.ArrayList;
import bugger.dataModel.Permission;

public class PermissionData
	{
	//Creates a new entry into the database
	public static Permission CreateNewPermission(String permissionName, String discription)
		{
		if(permissionName == null || discription == null)
			{
			return null;
			}

		String permissionID = Permission.GeneratePermissionID(permissionName);

		try
			{
			Connection connect = DriverManager.getConnection(DataAccess.databaseConnection);
			Statement statement = connect.createStatement();
			statement.executeUpdate("INSERT INTO Permission(permissionID,permissionName,discription) VALUES ('"
									+ permissionID + "','"
									+ permissionName + "','"
									+ discription + "')");
			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}

		return(new Permission(permissionID,permissionName,discription));
		}

	public static Permission[] GetPermissions()
		{
		ArrayList<Permission> permissionList = new ArrayList<Permission>();

		try
			{
			Connection connect = DriverManager.getConnection(DataAccess.databaseConnection);
			Statement statement = connect.createStatement();
			
			//Get the permissions
			ResultSet result = statement.executeQuery("SELECT * FROM Permission" );

			while(result.next())
				{
				String permissionID = result.getString("permissionID");
				String permissionName = result.getString("permissionName");
				String discription = result.getString("discription");
				permissionList.add(new Permission(permissionID, permissionName,discription));
				}

			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}

		return(permissionList.toArray(new Permission[permissionList.size()]));
		}

	public static Permission GetByName(String permissionName)
		{
		Permission returnValue = null;

		if(permissionName == null)
			{
			return null;
			}

		try
			{
			Connection connect = DriverManager.getConnection(DataAccess.databaseConnection);
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery("SELECT permissionID,permissionName,discription FROM Permission WHERE permissionName = '"+ permissionName +"'" );

			String permissionID = null;
			String discription = null;

			while(result.next())
				{
				permissionID = result.getString("permissionID");
				permissionName = result.getString("permissionName");
				discription = result.getString("discription");
				}

			if(permissionID != null && permissionName != null && discription != null)
				{
				returnValue = new Permission(permissionID,permissionName,discription);
				}
			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}

		return(returnValue);
		}
	}
