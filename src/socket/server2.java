package socket;

import java.io.*;
import java.net.*;
import java.util.*;


public class server2 {
	
	static Socket nextServerSocket;
	
	public static void connect(){
		try{
			nextServerSocket = new Socket("localhost", 6000); // Connection Started
			System.out.println("Conneted to the other server");
			
		} catch(Exception e){
			System.out.println("Connection to the other server failed!");
		}
	}

	public static void main(String []  args) throws UnknownHostException, IOException{
		
		Scanner sc = new Scanner(System.in);
		ServerSocket ss;
		
		
		//int port = sc.nextInt();
		
		 ss = new ServerSocket(6001);
		
		System.out.println("write anything to start connecting to the other server");
		sc.next();
		
		connect();
		
		 
		 while (true){
			 Socket s = null;
			// Socket sss = new Socket("localhost",6000);
			//cc = new ClientConnection(s,this);
			//   
		     // cc.start();  
			 
			 try{
				 s = ss.accept();
				 System.out.println("new client is connected:" +s);
				 DataInputStream din = new DataInputStream(s.getInputStream());
				 DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				 ClientHandler ch = new ClientHandler(s,din,dout,false);
				 
				 ch.start();
			        	//System.out.println(ch.a.size());
			        	/*for(int i=0 ; i<ch.a.size() ; i++){
			        		System.out.println(ch.a.get(i).getName().substring(7,ch.a.get(i).getName().length()));
			        	}*/
			        
				
			 }
			 catch(Exception e){
				 s.close();
				 //e.printStackTrace();
				 
			 }
			 
		 }
		 
	}
}
