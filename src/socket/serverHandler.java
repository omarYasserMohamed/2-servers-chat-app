package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class serverHandler extends Thread {
Socket s ;
DataInputStream din;
DataOutputStream dout;
	public serverHandler(Socket s, DataInputStream dis, DataOutputStream dos){
     this.s = s;
     this.din = dis;
     this.dout = dos;
		
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
			if(reply.charAt(0) == '$'){
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
			
			
			
			
			
			
			//I do not know if the else is here or in another class
			//need to know how to send the message that starts with $
			//
			//
			
		}} catch (IOException e1) {
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
