package P1;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocket {
    private Socket s;     
    private BufferedReader reader;
    private PrintWriter writer;  

    public MySocket(String host, int port) throws UnknownHostException, IOException {
        this.s = new Socket(host, port);     // Crea un socket conectat al servidor
        streamInit();               // Inicialitza els Streams.streamInit
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
            return reader.readLine(); // Llegeix una linea de text desde el socket.
        } catch (IOException e) {
            return null; 
        }
    }

    public void writeLine(String message) {
        writer.println(message); // Escriu el misatge a la sortida.
    }

    public void close() {
        try {
            s.close(); // Tanca el socket.
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

	public void println(String line) {
		this.writer.println(line);
	}
}
