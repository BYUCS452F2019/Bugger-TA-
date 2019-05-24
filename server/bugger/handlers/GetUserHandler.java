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


public class GetUserHandler implements HttpHandler
	{
	public void handle(HttpExchange exchange) throws IOException
		{
		String returnMessage = "";

		if(exchange.getRequestMethod().toLowerCase().equals("get"))
			{
			System.out.println("\n -- Getting User Info  --");
			System.out.println(" -> Authenticating: ");
			Headers headers = exchange.getRequestHeaders();

			String cookieContents = HandlerUtilites.GetCookieIDFromCookie(headers);

			System.out.print(" -> Cookie # " + cookieContents);

			if(CookieData.VerifyCookie(cookieContents))
				{
				User user = HandlerUtilites.GetUserFromCookie(cookieContents);
				returnMessage = new Gson().toJson(new ReturnUser(user));

				exchange.sendResponseHeaders(HttpURLConnection.HTTP_CREATED, returnMessage.length());
				}
			else
				{
				//remove invalid cookies
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
				returnMessage = "Invalid or expired Cookie!";
				}

			//System.out.println(headers.entrySet());
			}

		OutputStream stream = exchange.getResponseBody();
		Utility.WriteStringToStream(stream,returnMessage);			
		stream.close();
		exchange.close();
    	System.out.println(" -- REQUEST COMPLETE -- \n");
		}
	}
