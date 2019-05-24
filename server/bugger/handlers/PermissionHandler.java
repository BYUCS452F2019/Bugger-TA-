package bugger.handlers;

import java.io.*;
import java.net.*;
import bugger.utility.*;
import com.sun.net.httpserver.*;
import com.google.gson.*;
import bugger.dataAccess.UserData;
import bugger.dataAccess.CookieData;
import bugger.dataAccess.PermissionData;
import bugger.dataModel.User;
import bugger.dataModel.Permission;

public class PermissionHandler implements HttpHandler
	{
	public void handle(HttpExchange exchange) throws IOException
		{
		if(exchange.getRequestMethod().toLowerCase().equals("post") == true)
			{
			CreateNewPermission(exchange);
			}
		else if(exchange.getRequestMethod().toLowerCase().equals("get") == true)
			{
			GetPermissions(exchange);
			}
		}

	private void CreateNewPermission(HttpExchange exchange) throws IOException
		{
        System.out.println("\n -- Creating New Permission -- ");

		String returnMessage = "";
		Headers headers = exchange.getRequestHeaders();

		String cookieContents = HandlerUtilites.GetCookieIDFromCookie(headers);

		System.out.println(" -> Authenticating Cookie: " + cookieContents);
		if(CookieData.VerifyCookie(cookieContents))
			{
			PermissionJSON permission = new Gson().fromJson(Utility.InputStreamToString(exchange.getRequestBody()), PermissionJSON.class);

			User user = HandlerUtilites.GetUserFromCookie(cookieContents);

			if(user.HasPermission("admin") == true)
				{
				System.out.println(" -> Authenticated! Attempting to create permission!");
				if(permission.permissionName.equals("") || permission.permissionDisc.equals(""))
					{
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
					returnMessage = "You must have a discription and group name!";
					}
				else
					{
					returnMessage = CreatePermission(exchange, permission);
					}
				//End of verify cookie if
				}
			else
				{
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
				returnMessage = "User does not have required permissions!";
				}
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

	private String CreatePermission(HttpExchange exchange, PermissionJSON data) throws IOException
		{
		String returnMessage = "";
		if(PermissionData.GetByName(data.permissionName) != null)
			{
			System.out.println(" -> " + data.permissionName + " already exists!");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			returnMessage = "Security group already exists! Try another name!";			
			}
		else
			{
			PermissionData.CreateNewPermission(data.permissionName,data.permissionDisc);
			returnMessage = "Security group created!";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_CREATED, 0);
			}
		return(returnMessage);
		}

	private void GetPermissions(HttpExchange exchange) throws IOException
		{
        System.out.println("\n -- Getting Permissions -- ");

		String returnMessage = "";
		Headers headers = exchange.getRequestHeaders();

		String cookieContents = HandlerUtilites.GetCookieIDFromCookie(headers);

		System.out.println(" -> Authenticating Cookie: " + cookieContents);
		if(CookieData.VerifyCookie(cookieContents))
			{
			Permission[] permissions = PermissionData.GetPermissions();
			PermissionJSON[] jsonResponce = new PermissionJSON[permissions.length];

			for(int i = 0; i < permissions.length; i++)
				{
				jsonResponce[i] = new PermissionJSON(permissions[i].permissionName, permissions[i].discription);
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

	private class PermissionJSON
		{
		public String permissionName;
		public String permissionDisc;

		PermissionJSON(String permissionName, String permissionDisc)
			{
			this.permissionName = permissionName;
			this.permissionDisc = permissionDisc;
			}
		}
	}
