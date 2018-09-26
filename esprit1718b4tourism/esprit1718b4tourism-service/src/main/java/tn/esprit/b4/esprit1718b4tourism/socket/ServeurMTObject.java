package tn.esprit.b4.esprit1718b4tourism.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.b4.esprit1718b4tourism.entities.Message;


public class ServeurMTObject extends Thread {
private int nbClients=0;
private List<Conversation> clientsConnectes = new ArrayList<>();
private ArrayList<String> connectedUsers= new ArrayList<>();


	@Override
	public void run() {
		try {  //  telnet localhost 234
			ServerSocket ss = new ServerSocket(234);
			while (true) {
				System.out.println("waiting");
				Socket s = ss.accept();
				++nbClients;
				//connectedUsers.add(">>>-----");
				OutputStream os = s.getOutputStream();
				 ObjectOutputStream oos = new ObjectOutputStream(os);
				Conversation c = new Conversation(s, nbClients,s.getRemoteSocketAddress().toString(),"",oos);
				
				clientsConnectes.add(c);
				c.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void broadCast(Message message) {
		try { 
			//message.setConnectedUsers(connectedUsers);
			
			for(Conversation c:clientsConnectes) {
				//		if(!c.email.equals(message.getSender())) {
							 
							 c.myOos.writeObject(message);
								
			//	}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class Conversation extends Thread {
		protected Socket socket;
		protected int numeroClient;
		protected String ipUser;
		protected String email;
		protected ObjectOutputStream myOos;
		
		public Conversation(Socket socket, int num, String ipUser,String email,ObjectOutputStream myOos) {
			super();
			this.socket = socket;
			this.numeroClient=num;
			this.ipUser=ipUser;
			this.email = email;
			this.myOos = myOos;
		}
		


		@Override
		public void run() {
			try {
				String IP=socket.getRemoteSocketAddress().toString();
				
				System.out.println("Connexion du client num"+numeroClient+" IP= "+IP);
		
				Message msg = new Message();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				while (true) {
					
			   	System.out.println("waiting for input Object");
				msg = (Message)ois.readObject();
					email=msg.getSender();
					
				System.out.println("message recu : "+msg.getMsg());
				
				if(msg.getType().equals("online")) {
					connectedUsers.add(msg.getSender());
					
				}
				
				if(msg.getType().equals("offline")) {
					////
					for(String s:connectedUsers) {
						if(s.equals(msg.getSender())) {
							connectedUsers.remove(s);	
							break;
						}
					}
					////
					for(Conversation c:clientsConnectes) {
						if(c.email.equals(msg.getSender())) {
							clientsConnectes.remove(c);	
							break;
						}
					}
					////
				}
				
						
			msg.setConnectedUsers(connectedUsers);
				msg.setSender(email);
				broadCast(msg);		
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		new ServeurMTObject().start();
	}
	

}
