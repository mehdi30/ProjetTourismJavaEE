package tn.esprit.b4.esprit1718b4tourism.app.client.voice;


import javax.sound.sampled.*;

import tn.esprit.b4.esprit1718b4tourism.app.client.gui.StaticVars;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class VoiceRecorder extends VoiceUtil {

    public static void captureAudio() {
        try {
            final AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            final TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            Runnable runner = new Runnable() {
                int bufferSize = (int)format.getSampleRate() * format.getFrameSize();
                byte buffer[] = new byte[bufferSize];

                public void run() {
                    out = new ByteArrayOutputStream();
                    isRecording = true;
                    try {
                        while (isRecording) {
                            int count = line.read(buffer, 0, buffer.length);
                            if (count > 0) {
                                out.write(buffer, 0, count);
                            }
                        }
                    } finally {
                        try {
                            out.close();
                            out.flush();
                            line.close();
                            line.flush();
                          //  Listener.sendVoiceMessage(out.toByteArray());
                            StaticVars.voiceByte = out.toByteArray();

                            /*
                            for(int i = 0; i < out.toByteArray().length; i++)
                            {
                                StaticVars.voice += (char)out.toByteArray()[i];
                            }
                             */
                           //StaticVars.voiceByte = out.toByteArray();
                          //  StaticVars.voice = new String(out.toByteArray());
                            
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread captureThread = new Thread(runner);
            captureThread.start();
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " );
            e.printStackTrace();
        }
    }
}
