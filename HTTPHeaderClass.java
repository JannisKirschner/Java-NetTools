package portscanner;


import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class HTTPHeaderClass {
	
	static String url;
	public static void httpheader(){
		System.out.print("Please enter Website: ");
		Scanner scan = new Scanner(System.in);
		while(true){
		url = scan.next();
			if(url.toLowerCase().contains("http")){break;}
			else{url = "http://" + url; break;}
		}
			
		URL urlobject = null;
		try {
			urlobject = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URLConnection connection = null;
		try {
			connection = urlobject.openConnection();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//get the headers
		Map<String, List<String>> map = connection.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()){
			System.out.println("Key : " + entry.getKey() + 
	        " ,Value : " + entry.getValue());
		}
		
		//get header (by key)
		String server = connection.getHeaderField("Server");
		
	}
	
