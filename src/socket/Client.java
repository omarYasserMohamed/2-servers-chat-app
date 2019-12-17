package socket;

import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
import java.util.Scanner; 

import org.omg.CORBA.ServerRequest;

import com.sun.javafx.applet.ExperimentalExtensions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

  
// Client class 
public class Client extends JFrame  { 
	ClientConnection cc;
 String name;
 
public static void main(String [] args){
	new Client();
}
	  public Client(){
		  try{
			  ArrayList<Client> allClients = cc.allClients;
			  Scanner sc  = new Scanner(System.in);
			  boolean flag ;
			  System.out.println("enter your name");
			  
			  
			  String n = sc.nextLine();
			  
			  while(true)
			  {
			   flag = false;
			  
			  
			 
			  

			  if(!flag){
				   
			  break;}
			  else{
				  
			     n = sc.nextLine();
			  }
			  }
			  this.name = n;
			  allClients.add(this);
			  Socket s;
			  System.out.println("the servers' port numbers are 6000 and 6001");
			  System.out.println("enter the server port number you want to connect to");
			  while(true){
			  try{
				  int port = sc.nextInt();
			   s = new Socket("localhost" , port);
			   break;}
			  catch(Exception e){
				  System.out.println("port number entered is not valid");
				  System.out.println("enter a valid port number");
			  }
			  }
			  System.out.println("please enter your message in this form");
			  System.out.println("target_client_name#your message");
			  
			   cc = new ClientConnection(s,this);
			   
			   
			   cc.start();
			   listenForInput();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	  } 
	  public void listenForInput() throws IOException{
		  Scanner console  = new Scanner(System.in);
		  
		  while(true){
			  while(!console.hasNextLine()){
				  try
				  {
					  Thread.sleep(1);;
				  }
				  catch(Exception e){
					  e.printStackTrace();
				  }
				
			  }
			  String input = console.nextLine();
			  if(input.equalsIgnoreCase("Bye"))
				  break;
			cc.sendStringToServer(input);  
			  
		  }
		  cc.close();
	  }
}