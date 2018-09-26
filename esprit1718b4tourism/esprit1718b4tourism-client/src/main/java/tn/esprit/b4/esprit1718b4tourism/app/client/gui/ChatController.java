package tn.esprit.b4.esprit1718b4tourism.app.client.gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import tn.esprit.b4.esprit1718b4tourism.app.client.other.BubbleSpec;
import tn.esprit.b4.esprit1718b4tourism.app.client.other.BubbledLabel;
import tn.esprit.b4.esprit1718b4tourism.app.client.voice.VoicePlayback;
import tn.esprit.b4.esprit1718b4tourism.app.client.voice.VoiceRecorder;
import tn.esprit.b4.esprit1718b4tourism.app.client.voice.VoiceUtil;
import tn.esprit.b4.esprit1718b4tourism.entities.Message;
import tn.esprit.b4.esprit1718b4tourism.entities.Role;

public class ChatController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private ListView chatPane;
    @FXML
    private TextArea messageBox;
    @FXML
    private Button buttonSend;
    @FXML
    private Label currentLabel;
    @FXML
    private ListView<String> connectedUsers;
    @FXML
    private Button exitChat;
    @FXML
    private Button recording;
    @FXML
    private Button showVoice;
    @FXML
    private Button back;
    @FXML
    private ImageView imgv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	back.setDisable(true);
        recording.setStyle("-fx-background-color: #00ff00");
        showVoice.setDisable(true);
        recording.setText("Press to record");
        ////////////////////////////////////
        final Socket clientSocket;

        try {
            clientSocket = new Socket("localhost", 234);
            OutputStream os = clientSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            //////////////////////SEND//////////////////////////
            Thread send = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        Message m1 = new Message();

                        try {
                            m1.setSender(StaticVars.currentUser.getEmail());
                            m1.setMsg("online");
                            m1.setType("online");
                            chatPane.getItems().add("you joined the room");
                            oos.writeObject(m1);
                            oos.flush();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                        while (true) {
                            Message m = new Message();
                            m.setSender(StaticVars.getCurrentUser().getEmail());

                            buttonSend.setOnAction(e -> {
                                try {
                                    m.setMsg(messageBox.getText());
                                    m.setType("message");
                                    msgToScreenMe("Me : " + messageBox.getText());
                                    messageBox.clear();
                                    oos.writeObject(m);
                                    oos.flush();
                                    System.out.println("ollé " + m.getMsg());
                                } catch (IOException e1) {

                                    e1.printStackTrace();
                                }

                            });
           
                            exitChat.setOnAction(e -> {
                                try {
                                    m.setType("offline");
                                    oos.writeObject(m);
                                    oos.flush();
                     
                                  	chatPane.setDisable(true);
                                	buttonSend.setDisable(true);
                                	messageBox.setDisable(true);
                                	
                                	exitChat.setDisable(true);
                                	recording.setDisable(true);
                                	showVoice.setDefaultButton(true);
                                	back.setDisable(false);
                                	connectedUsers.setDisable(true);
                                	

                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            });

                            showVoice.setOnAction(e -> {
                                try {
                                    m.setVoice(StaticVars.voiceByte);
                                    m.setType("voice");
                                    oos.writeObject(m);
                                    oos.flush();
                                    showVoice.setDisable(true);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            });

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            send.start();

            InputStream is = clientSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            //////////////////////////RECEIVE////////////////////////////////////////////
            Thread receive = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        while (clientSocket.isConnected()) {

                            Message m = null;
                            m = (Message) ois.readObject();
                            if (m != null) {


                                if (m.getType().equals("message")) {
                                    if (!m.getSender().equals(StaticVars.currentUser.getEmail())) {
                                        msgToScreen(m.getSender() + " : " + m.getMsg());
                                    }
                                } else if (m.getType().equals("online")) {
                                    if (!m.getSender().equals(StaticVars.currentUser.getEmail())) {
                                        chatPane.getItems().add(m.getSender() + " joined the room");
                                    }

                                    connectedUsers.getItems().clear();


                                    for (String s : m.getConnectedUsers()) {
                                  //      connectedUsers.getItems().add(s);
                                        System.out.println(">>>>" + s);
                                        System.out.println("Server: " + m.getMsg());
                                    }


                                } else if (m.getType().equals("offline")) {
                                    chatPane.getItems().add(m.getSender() + " left the room");
                                    /*
								for(String s:connectedUsers) {
									if(!s.equals(m.getSender())) {
										connectedUsers.rem
										break;
									}
								}*/

                                } else if (m.getType().equals("voice")) {
                                    //chatPane.getItems().add("voice received");
                                    //  StaticVars.voiceByte=m.getVoice();
                                    byte[] vocalRecu = m.getVoice();

                                    Button button = new Button();
                                    button.setText("voice message from " + StaticVars.currentUser.getEmail());
                                    chatPane.getItems().add(button);

                                    button.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e) {
                                            System.out.println("ellooooelloooo");

                                            VoicePlayback.playAudio(vocalRecu);
                                        }
                                    });

                                    chatPane.getItems().add(button);
                                    //byte[] b = m.getMsg().getBytes();
                                    // VoicePlayback.playAudio(vocalRecu);
                                    //}

                                }

                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            receive.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ////////////////////////////////////
        currentLabel.setText(StaticVars.currentUser.getEmail());

    }

    @FXML
    private void sendMethod(KeyEvent event) throws IOException {

    }

    @FXML
    private void sendButtonAction(ActionEvent event) throws IOException {

    }

    /* Displays Notification when a user joins */
    public void newUserNotification(Message msg) {/*
        Platform.runLater(() -> {
            //     Image profileImg = new Image(getClass().getClassLoader().getResource("images/" + msg.getPicture().toLowerCase() +".png").toString(),50,50,false,false);
            TrayNotification tray = new TrayNotification();
            tray.setTitle("A new user has joined!");
            tray.setMessage(" has joined the JavaFX Chatroom!");
            tray.setRectangleFill(Paint.valueOf("#2C3E50"));
            tray.setAnimationType(AnimationType.POPUP);
            //   tray.setImage(profileImg);
            tray.showAndDismiss(Duration.seconds(5));
            try {
                //    Media hit = new Media(getClass().getClassLoader().getResource("sounds/notification.wav").toString());
                //   MediaPlayer mediaPlayer = new MediaPlayer(hit);
                //  mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });*/
    }

    public void msgToScreen(String chaine) {
        BubbledLabel bl6 = new BubbledLabel();
        bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        HBox x = new HBox();
        x.setMaxWidth(chatPane.getWidth() - 20);
        x.setAlignment(Pos.TOP_LEFT);
        bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
        // x.getChildren().addAll(bl6, profileImage);
        x.getChildren().addAll(bl6);
        bl6.setText(chaine);
        chatPane.getItems().add(bl6);
    }

    public void msgToScreenMe(String chaine) {
        BubbledLabel bl6 = new BubbledLabel();
        bl6.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));
        HBox x = new HBox();
        x.setMaxWidth(chatPane.getWidth() - 20);
        x.setAlignment(Pos.TOP_RIGHT);
        bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
        // x.getChildren().addAll(bl6, profileImage);
        x.getChildren().addAll(bl6);
        bl6.setText(chaine);
        chatPane.getItems().add(bl6);
    }

    public void onlineToList(String chaine) {

        connectedUsers.getItems().add(chaine);
    }

    @FXML
    private void exitChat_Action(ActionEvent event) throws IOException {
        String fenetre = null;
        if (StaticVars.currentUser.getRole() == Role.Admin) {
            fenetre = "AdminHome.fxml";
        } else if (StaticVars.currentUser.getRole() == Role.Premium) {
            fenetre = "PremiumHome.fxml";
        } else if (StaticVars.currentUser.getRole() == Role.Standard) {
            fenetre = "ClientHome.fxml";
        }

        Parent root = FXMLLoader.load(getClass().getResource(fenetre));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    @FXML
    private void recording(ActionEvent event) {
        if (VoiceUtil.isRecording()) {
            Platform.runLater(() -> {
                recording.setStyle("-fx-background-color: #00ff00");
                System.out.println("not recording");
                recording.setText("Press to record");
                showVoice.setDisable(false);
            }
            );
            VoiceUtil.setRecording(false);

        } else {
            Platform.runLater(() -> {
                System.out.println("recording now");
                recording.setStyle("-fx-background-color: #ff4800");
                recording.setText("Recording now");
            }
            );

            VoiceRecorder.captureAudio();
        }
    }

    @FXML
    private void showVoice_action(ActionEvent event) {

        // VoicePlayback.playAudio(StaticVars.voiceByte);
    }

    @FXML
    private void back_action(ActionEvent event) throws IOException {
  

        Parent root = FXMLLoader.load(getClass().getResource("ClientHome.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    	
    }

}
