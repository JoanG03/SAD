package XatGrafic;
import java.io.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static ConcurrentHashMap<String, MySocket> usuarios = new ConcurrentHashMap<>();
    public static void main(String[] args) throws NumberFormatException, IOException {
        MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
        while (true) {
            MySocket s = ss.accept();
            new Thread(){
                public void run() {
                    s.writeLine("Introduce tu nombre de usuario: ");
                    String nickname = s.readLine();
                    if(usuarios.containsKey(nickname)){
                        s.writeLine("Este nickname ya esta en uso");
                        s.close();
                    }
                    else{
                        addUsr(nickname, s);
                        String line;
                        while ((line = s.readLine()) != null) {
                            broadcast(nickname, line);
                            System.out.println(nickname + ": " + line);
                        }
                        removeUsr(nickname, s);
                        s.close();
                    }
                }
            }.start();
        }
    }
    
    public static void addUsr(String nickname, MySocket s){
            System.out.println(nickname+ " se ha unido al chat");
            usuarios.put(nickname, s);            
    }

    public static void removeUsr(String nickname, MySocket s){
        System.out.println(nickname+ " ha salido del chat");
        usuarios.remove(nickname);
    }

    public static void broadcast(String nickname, String line){
    	for (Entry<String, MySocket> entry : usuarios.entrySet()) {
            String currentUsr = entry.getKey();
            MySocket currentSocket = entry.getValue();
            if(!currentUsr.equals(nickname)){
                currentSocket.writeLine(nickname + ": " + line);
            }
	      }
    }
}
