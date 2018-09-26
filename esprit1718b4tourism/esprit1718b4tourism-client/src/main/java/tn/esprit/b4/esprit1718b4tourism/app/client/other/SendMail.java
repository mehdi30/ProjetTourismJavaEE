package tn.esprit.b4.esprit1718b4tourism.app.client.other;

public class SendMail {
public static void main(String[] args) {
    String[] to = {"hassen.tayech@gmail.com"};
    if(EMailSender.sendMail("tourism.orientt@gmail.com", "123azert", "hello", to,"Subject"))
    	System.out.println("email sent successfully");
    else System.out.println("error");
}   
}
//String[] to = {"hassen.tayech@gmail.com"};
//EMailSender.sendMail("tourism.orientt@gmail.com", "123azert", "hello", to,"")