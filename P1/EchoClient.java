package P1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
        MySocket s = new MySocket(args[0], Integer.parseInt(args[1]));
        // Keyboard thread
        new Thread() {
            public void run() {
                String line;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while ((line = kbd.readLine()) != null) {
                        s.println(line);
                    }
                    // close s for writing
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                s.close();
            }
        }.start();

        // Console thread
        new Thread() {
            public void run() {
                String line;
                while ((line = s.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }.start();
    }
}