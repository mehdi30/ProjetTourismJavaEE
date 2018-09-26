package tn.esprit.b4.esprit1718b4tourism.app.client.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import tn.esprit.b4.esprit1718b4tourism.app.client.gui.StaticVars;
import tn.esprit.b4.esprit1718b4tourism.entities.Message;


public class ClientxObject {

	public static void main(String[] args) {
		
	      final Socket clientSocket;
	
	      try {
	  	         clientSocket = new Socket ("localhost", 234);
	  	       OutputStream os = clientSocket.getOutputStream();
          	 ObjectOutputStream oos = new ObjectOutputStream(os);
          	 
     Thread send = new Thread (new Runnable () {
	              @Override
	              public void run () {
	                try {
	                	int i=0;
	               	while (true) {
	               		Message msg = new Message("msggg", "sender", "picture", null,"type",null);
	               		msg.setMsg("mmmm"+i);
	               		i++;
	              			oos.writeObject(msg);
	              			oos.flush();
	                	}
					} catch (Exception e) {
						e.printStackTrace();
					}
	             }
	         });
	         send.start ();
	 
	         
	         InputStream is = clientSocket.getInputStream();
	         ObjectInputStream ois = new ObjectInputStream(is);
	         
	        Thread receive = new Thread (new Runnable () {
	        	
	            @Override
	            public void run () {
	               try {
	 
	                 while (clientSocket.isConnected()) {
	                	Message msg = null;
	                	 msg = (Message)ois.readObject();
	                	 if(msg!=null) {
	                    System.out.println ("Server: " + msg.getMsg());
	                	 }
	                 }
	               } catch(Exception e) {
	                   e.printStackTrace ();
	               }
	            }
	        });
	        receive.start ();
	 
	      } catch(IOException e) {
	           e.printStackTrace ();
	      }
	  }
	}