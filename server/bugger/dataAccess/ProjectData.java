package bugger.dataAccess;

import java.sql.*;
import java.util.ArrayList;
import bugger.dataModel.Project;
import bugger.dataModel.Permission;

public class ProjectData
	{
	public static Project[] GetProjects()
		{
		ArrayList<Project> projectList = new ArrayList<Project>();

		try
			{
			Connection connect = DriverManager.getConnection(DataAccess.databaseConnection);
			Statement statement = connect.createStatement();
			
			//Get the permissions
			ResultSet result = statement.executeQuery("SELECT * FROM Projects" );

			while(result.next())
				{
				String projectID = result.getString("projectID");
				String projectName = result.getString("projectName");
				String discription = result.getString("discription");
				projectList.add(new Project(projectID, projectName,discription));
				}

			connect.close();
			}
		catch (Exception e)
			{
			System.out.println(e.getMessage()); 
        	}

		return(projectList.toArray(new Project[projectList.size()]));
		}
	}
