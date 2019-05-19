package bugger.handlers;

import java.io.*;
import java.net.*;
import bugger.utility.*;
import com.sun.net.httpserver.*;

public class LoginHandler implements HttpHandler
	{
	public void handle(HttpExchange exchange) throws IOException
		{
        System.out.println("-- Login Request made -- ");

		System.out.println(Utility.InputStreamToString(exchange.getRequestBody()));

		if(exchange.getRequestMethod().toLowerCase().equals("put") == true)
			{
			}

		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
	}
