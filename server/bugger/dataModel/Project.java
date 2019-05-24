package bugger.dataModel;

import java.sql.*;
import java.util.ArrayList;

public class Project
	{
	public String projectID;
	public String projectName;
	public String discription;
		
	public Project(String projectID, String projectName, String discription)
		{
		this.projectID = projectID;
		this.projectName = projectName;
		this.discription = discription;
		}
	}
