package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class server {
	static Socket nextServerSocket;
	static ClientConnection cc;
	public static void connect(){
		try{
			nextServerSocket = new Socket("localhost", 6001); // Connection Started
			System.out.println("Conneted to the other server");
			
		} catch(Exception e){
			System.out.println("Connection to the other server failed!");
		}
	}
	
	public static void main(String []  args) throws UnknownHostException, IOException{
		
		Scanner sc = new Scanner(System.in);
		ServerSocket ss;
		
		
		//int port = sc.nextInt();
		
		 ss = new ServerSocket(6000);
		 
		 System.out.println("type to start connecting to the other server");
		sc.next();
		
		connect();
		DataInputStream dinForServer = new DataInputStream(nextServerSocket.getInputStream());
		 DataOutputStream doutForServert = new DataOutputStream(nextServerSocket.getOutputStream());
		ClientHandler serverAsAclient = new ClientHandler(nextServerSocket, dinForServer, doutForServert,true);
		
		 while (true){
			 Socket s = null;
			 
			 try{
				 s = ss.accept();
				 System.out.println("new client is connected:" +s);
				 DataInputStream din = new DataInputStream(s.getInputStream());
				 DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				 ClientHandler ch = new ClientHandler(s,din,dout,false);
				 
				 ch.start();
				 //listenForInput();
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
