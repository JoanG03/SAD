package XatGrafic;
import java.net.ServerSocket;
import java.io.*;


public class MyServerSocket {
    private ServerSocket ss;

    public MyServerSocket(int port) throws IOException {
        this.ss = new ServerSocket(port);
    }

    public MySocket accept() throws IOException{
       return new MySocket(ss.accept());       
    }

    public void close() {
        try {
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
