package bugger.handlers;

import java.io.*;
import java.net.*;
import bugger.utility.*;
import com.sun.net.httpserver.*;

public class RegisterHandler implements HttpHandler
	{
	public void handle(HttpExchange exchange) throws IOException
		{
        System.out.println("-- Registration Request made -- ");
		System.out.println(Utility.InputStreamToString(exchange.getRequestBody()));
		System.out.println(exchange.getHttpContext().getPath());
		if(exchange.getRequestMethod().toLowerCase().equals("put") == true)
			{
			}

		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
	}