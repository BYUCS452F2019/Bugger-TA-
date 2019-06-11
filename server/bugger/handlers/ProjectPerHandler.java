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

public class ProjectPerHandler implements HttpHandler
	{
	public void handle(HttpExchange exchange) throws IOException
		{
		if(exchange.getRequestMethod().toLowerCase().equals("put") == true)
			{
			UpdatePermission(exchange);
			}
		else if(exchange.getRequestMethod().toLowerCase().equals("delete") == true)
			{
			RemovePermission(exchange);
			}
		}

	private void UpdatePermission(HttpExchange exchange) throws IOException
		{
        System.out.println("\n -- Adding Project Permission -- ");

		String returnMessage = "";
		Headers headers = exchange.getRequestHeaders();

		String cookieContents = HandlerUtilites.GetCookieIDFromCookie(headers);

		System.out.println(" -> Authenticating Cookie: " + cookieContents);
		if(CookieData.VerifyCookie(cookieContents))
			{
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

	private void RemovePermission(HttpExchange exchange) throws IOException
		{
        System.out.println("\n -- Remove Project Permission -- ");

		String returnMessage = "";
		Headers headers = exchange.getRequestHeaders();

		String cookieContents = HandlerUtilites.GetCookieIDFromCookie(headers);

		System.out.println(" -> Authenticating Cookie: " + cookieContents);
		if(CookieData.VerifyCookie(cookieContents))
			{
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

	private class ProjectPermission
		{
		String projectName;
		String permission;
		}
	}
