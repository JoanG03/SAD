package P1_JL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
        MySocket s = new MySocket(args[0], Integer.parseInt(args[1]));
        
        new Thread() {
            public void run() {
                String line;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while ((line = kbd.readLine()) != null) {
                        s.writeLine(line);
                    }
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                s.close();
            }
        }.start();

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

