package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection extends Thread{
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	Client c;
	static ArrayList<Client> allClients = new ArrayList<Client>();
    public ClientConnection(Socket socket , Client c) throws IOException{
    	s = socket;
    	
    	this.din = new DataInputStream(s.getInputStream());
    	this.dout = new DataOutputStream(s.getOutputStream());
    	this.c =c;
    	 
    }
    public void sendStringToServer(String text) throws IOException{
    	dout.writeUTF(text);
    	dout.flush();
    }
    public void run(){
    	while(true){
    	try {try{
			while(din.available() == 0){
				try{
					Thread.sleep(1);
					
				}
				catch(InterruptedException e){
					e.printStackTrace();
					close();
				}
			}}
    	
    	
    	
    	catch(Exception e){
    		close();
    		return;
    	}
    	
    	
    	
    	String reply  = din.readUTF();
			if(reply.charAt(0) != '$'){
				boolean hashFound = false;
			
			
			String target = "";
			int i;
			for(i=0 ; i<reply.length();i++){
      		  if((reply.charAt(i)+"").equals("#")){
      			 
      			  hashFound = true;
      			  break;
      		  }
      		  else{
      			  target = target+(reply.charAt(i)+"");
      			  
      		  }
      	  }
			String replyIthoutHash = "";
			if(hashFound){
				if(i!=reply.length()-1)
				replyIthoutHash = reply.substring(i+1);
			}
			if(reply.equalsIgnoreCase("bye")){
				close();
				return;
			}
			
			
			if(c.name.equals(target)){
	    	System.out.println(replyIthoutHash);}
    	}
			
			
			
			//I do not know if the else is here or in another class
			//need to know how to send the message that starts with $
			//
			//
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			close();
		}
    
    	
    	
    	
    }
    }
    public void close(){
    	 try{
    		 din.close();
    		 dout.close();
    		 s.close();
    		 
    	 }
    	 catch(IOException e){
    		 e.printStackTrace();
    	 }
    }
    }
