package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class ClientHandler extends Thread  
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    String name;
    final Socket s;
    static ArrayList<ClientHandler> a = new ArrayList<ClientHandler>(); 
    boolean isServer;
      
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos,boolean isServer)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        a.add(this);
        this.name = name;
        this.isServer =  isServer;
        
    } 
  
    @Override
    public void run()  
    { try{
        String received; 
        String toreturn; 
        while (true)  
        { 
            try { 
  
                // Ask user what he wants 
               // dos.writeUTF(""+ 
                 //           ""); 
                  
                // receive the answer from client 
                received = dis.readUTF(); 
                 
                if(received.equalsIgnoreCase("Bye")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed");

                    for(int i = 0 ; i<a.size() ; i++){
                    if(a.get(i).equals(this)){
                    	a.remove(i);
                    	}
                    
                    
                    } this.dis.close(); 
                    this.dos.close();
                    this.dis.close(); 
                    this.dos.close();
                    return; 
                } 
                  
               
                  
                 
                  else{
                	  if(received.charAt(0) != '$'){
                	  //boolean fromServer = false;
                	   
                        for(int i = 0 ; i<a.size() ; i++){
                        if(a.get(i).equals(this) == false && !a.get(i).isServer){
                        	//System.out.println(a.get(i).getId());
                        	a.get(i).dos.writeUTF(received);}
                        	
                        else{if(a.get(i).isServer){
                        		String xxx = "$"+received;
                        		boolean flag = false;
                        		System.out.print("here");
                        		a.get(i).dos.writeUTF(xxx);
                        	}
                        }
                        
                        
                        
                        
                        }
                        
                        
                  }
                	  else{
                		  
                		  for(int i = 0 ; i<a.size() ; i++){
                              if(a.get(i).equals(this) == false && !a.get(i).isServer){
                              	//System.out.println(a.get(i).getId());
                              	a.get(i).dos.writeUTF(received.substring(1));
                              	}
                           
                              }  
                	  }
                }  } 
                     
             catch (IOException e) { 
            	 this.dis.close(); 
                 this.dos.close();
                 this.dis.close(); 
                 this.dos.close();
                 return;    
            } 
        } 
          
    }
    catch(Exception e1){
    	try {
    		System.out.println("client disconnected");
			this.dos.close();
			this.dis.close(); 
	        this.dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
        
        return;
    }
    } 
} 