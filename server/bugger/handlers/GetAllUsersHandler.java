package bugger.handlers;

import java.io.*;
import java.net.*;
import bugger.utility.*;
import com.sun.net.httpserver.*;
import com.google.gson.*;
import bugger.dataAccess.UserData;
import bugger.dataAccess.CookieData;
import bugger.dataAccess.PermissionData;
import bugger.dataAccess.ProjectData;
import bugger.dataModel.User;

public class GetAllUsersHandler implements HttpHandler
	{
	public void handle(HttpExchange exchange) throws IOException
		{
		if(exchange.getRequestMethod().toLowerCase().equals("post") == true)
			{

			}
		else if(exchange.getRequestMethod().toLowerCase().equals("get") == true)
			{
			GetProjects(exchange);
			}
		}

	private void GetProjects(HttpExchange exchange) throws IOException
		{
        System.out.println("\n -- Getting Projects -- ");

		String returnMessage = "";
		Headers headers = exchange.getRequestHeaders();

		String cookieContents = HandlerUtilites.GetCookieIDFromCookie(headers);

		System.out.println(" -> Authenticating Cookie: " + cookieContents);
		if(CookieData.VerifyCookie(cookieContents))
			{
			User[] users = UserData.GetAllUsers();
			ReturnUser[] jsonResponce = new ReturnUser[users.length];

			for(int i = 0; i < users.length; i++)
				{
				jsonResponce[i] = new ReturnUser(users[i]);
				}

			returnMessage = new Gson().toJson(jsonResponce);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			}
		else
			{
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
			returnMessage = "Invalid or expired Cookie! Try loging in again!";
			}

		OutputStream stream = exchange.getResponseBody();
		Utility.WriteStringToStream(stream,returnMessage);			
		stream.close();
		exchange.close();
    	System.out.println(" -- REQUEST COMPLETE -- \n");
		}
	}
