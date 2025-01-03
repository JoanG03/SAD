package XatGrafic;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocket {
    private Socket s;     
    private BufferedReader reader;
    private PrintWriter writer;  

    public MySocket(String host, int port) throws UnknownHostException, IOException {
        this.s = new Socket(host, port);    
        streamInit();            
    }
     public MySocket(Socket s) throws IOException {
        this.s = s;
        streamInit();
    }
    private void streamInit() throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.writer = new PrintWriter(s.getOutputStream(), true);
    }
    public String readLine() {
        try {
            return reader.readLine(); 
        } catch (IOException e) {
            return null; 
        }
    }
    public void writeLine(String message) {
        writer.println(message); 
    }

    public void close() {
        try {
            s.close(); // Tanca el socket.
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
