package portscanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PortScannerClass {
	
	static boolean visible = false;
	static ArrayList<Integer> openports = new ArrayList();
	static int[] ports = {20,21,22,23,25,53,67,68,79,80,110,115,119,123,135,137,138,139,143,161,194,389,443,445,465,587,993,994,995,1026,1214,1433,1434,2381,3101,3389,4040,4444,5190,5900,6112,8080};

    public static void portscan(){
    	 	
        System.out.print("Please enter Remote-Server: ");
        Scanner scan = new Scanner(System.in);
        
        String remoteServer = scan.next();
        
        InetAddress oldAddress = null;
        try {
            oldAddress = InetAddress.getByName(remoteServer);
        } catch (UnknownHostException e) {
             
            e.printStackTrace();
        }
        
        String serverName = oldAddress.getHostName();
        String remoteServerIP = oldAddress.getHostAddress();
        String canonicalServerName = oldAddress.getCanonicalHostName();
        
         
        System.out.println("Server Name:           " + serverName);
        System.out.println("Server IP-Address:     " + remoteServerIP);
        System.out.println("Canonical Server Name: " + canonicalServerName);
        System.out.println("----------------------------------------------\n");
        
        System.out.print("Verbose or Hidden mode? (v/h): ");
        String verbose = scan.next();
        while(true){
        	if(verbose.equals("v")){
        		System.out.print("Verbose mode\n");visible = true; break;}
        	else if(verbose.equals("h")){
        		System.out.print("Hidden mode\n");visible = false; break;}
        	else{
        		System.out.print("Bad input!\n");}	
        }
        
        System.out.print("Standard ports = 1 | IP-Address range = 2: ");
        while(true){
        	int portchoice = scan.nextInt();
        	
        	if(portchoice==1){
        		System.out.println("Scanning default ports...\n");
        		
        		int d1 = (int) (new Date().getTime()/1000);
                tcpconnWarray(oldAddress,ports);
                int d2 = (int) (new Date().getTime()/1000);
                
                System.out.println("Scanning time was " + (d2-d1) + " seconds");	
                break;
                
        	}
        	
        	else if(portchoice==2){
        		System.out.println("Scanning with custom range...\n");
        		System.out.print("Enter start port: ");
        		int startport = scan.nextInt();
        		System.out.print("\nEnter endport port: ");
        		int endport = scan.nextInt();
        		
        		System.out.print("\n");
        		int d1 = (int) (new Date().getTime()/1000);
                tcpconnWrange(oldAddress,startport,endport);
                int d2 = (int) (new Date().getTime()/1000);
                
                System.out.println("------------------");
                
                for(int b=0;b<openports.size();b++){System.out.println(openports.get(b) + " open");}
                
                System.out.println("Scanning time was " + (d2-d1) + " seconds");
        		break;
        	}
        	
        	else{System.out.println("Bad input");}
        }
     
    }
    
    
    public static void tcpconnWrange(InetAddress oldAddress, int startport, int endport) {
    	for(int port = startport; port <= endport; port ++){ 
    		Socket socket = new Socket();
    		SocketAddress sockaddr = new InetSocketAddress(oldAddress, port);
    		try {
				socket.connect(sockaddr, port); openports.add(port);
				socket.close();
			} catch (IOException e) {
				
				if(visible == true){
					System.out.println(port + " closed");}
			}
    		if(socket.isConnected()){System.out.println(port + " open");}}
    	}
    
    
    
    	public static void tcpconnWarray(InetAddress oldAddress, int[]ports) {
        	for(int port = 0; port < ports.length; port ++){ 
        		Socket socket = new Socket();
        		SocketAddress sockaddr = new InetSocketAddress(oldAddress, ports[port]);
        		try {
    				socket.connect(sockaddr, ports[port]);
    				socket.close();
    			} catch (IOException e) {
    				
    				
    				if(visible==true){
    					System.out.println(port + " closed");}
    			}
        		if(socket.isConnected()){System.out.println(port + " open");}
        	}
   }
}


