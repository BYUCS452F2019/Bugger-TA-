package bugger.handlers;

import bugger.dataModel.User;

public class ReturnUser
	{
	public String userID;
	public String username;
	public String email;
	public String alias;
	public String firstName;
	public String lastName;
	public String[] permissions;

	public ReturnUser(User userIn)
		{
		username = userIn.username;
		email = userIn.email;
		alias = userIn.alias;
		firstName = userIn.firstName;
		lastName = userIn.lastName;

		permissions = new String[userIn.permissions.length];

		for(int i= 0; i < permissions.length; i++)
			{
			permissions[i] = userIn.permissions[i].permissionName;
			}
		}

	}
