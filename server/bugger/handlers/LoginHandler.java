package bugger.handlers;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import com.google.gson.*;
import bugger.utility.*;
import bugger.dataAccess.UserData;
import bugger.dataAccess.CookieData;
import bugger.dataModel.User;
import bugger.dataModel.Cookie;


public class LoginHandler implements HttpHandler
	{
	public void handle(HttpExchange exchange) throws IOException
		{
		boolean loginSuccess = false;
		String returnMessage = "";
		if(exchange.getRequestMethod().toLowerCase().equals("post"))
			{
			LoginDetails login = new Gson().fromJson(Utility.InputStreamToString(exchange.getRequestBody()), LoginDetails.class);
			System.out.println("--  '" + login.username + "'  is attempting to login  --");

			User user = UserData.GetUserByUsername(login.username);
			if(user != null)
				{
				loginSuccess = user.password.equals(User.HashPassword(login.password));
				}

			if(loginSuccess == true)
				{
				System.out.println("Guess they get to log in!");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

				returnMessage = new Gson().toJson(new ReturnUser(user));

				//Create a cookie for their session
				Cookie cookie = CookieData.CreateNewCookie(user.userID);
				}
			else
				{
				System.out.println("Login was a failure");
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
				returnMessage = "Invalid username/password combination!";
				}
			}

		OutputStream stream = exchange.getResponseBody();
		Utility.WriteStringToStream(stream,returnMessage);			
		stream.close();
		}

	private class ReturnUser
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
			userID = userIn.userID;
			username = userIn.username;
			email = userIn.email;
			alias = userIn.alias;
			firstName = userIn.lastName;

			permissions = new String[userIn.permissions.length];

			for(int i= 0; i < permissions.length; i++)
				{
				permissions[i] = userIn.permissions[i].permissionName;
				}
			}

		}

	private class LoginDetails
		{
		public String username;
		public String password;
		}
	}
