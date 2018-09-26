package tn.esprit.b4.esprit1718b4tourism.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

public class Message implements Serializable {

   
    private String msg;

    private String sender;
       
    private byte[] picture;
    
    private byte[] voice;
    
    private String type;
    
    private ArrayList<String> connectedUsers;
    



	public Message() {
		super();
	}

	public Message(String msg, String sender, byte[] picture, byte[] voice, String type,
			ArrayList<String> connectedUsers) {
		super();
		this.msg = msg;
		this.sender = sender;
		this.picture = picture;
		this.voice = voice;
		this.type = type;
		this.connectedUsers = connectedUsers;
	}

	

	public ArrayList<String> getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(ArrayList<String> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public byte[] getVoice() {
		return voice;
	}


	public void setVoice(byte[] voice) {
		this.voice = voice;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}