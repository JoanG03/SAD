package P1;
import java.io.*;
public class EchoServer {
    public static void main(String[] args) throws NumberFormatException, IOException {
        MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
        while (true) {
            MySocket s = ss.accept();
            new Thread(){
                public void run() {
                    String line;
                    while ((line = s.readLine()) != null) {
                        s.writeLine(line);
                    }
                    s.close();
                }
            }.start();
        }
    }
}
