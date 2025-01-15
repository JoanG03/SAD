package P0;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.*;
public class EditableBufferedReader extends BufferedReader{
    public final Line line;

    public EditableBufferedReader(InputStreamReader text){
        super(text);
        line = new Line();
    }

    public void setRaw() throws IOException{ 
        try{
            ProcessBuilder process = new ProcessBuilder("sh", "-c", "stty -echo raw < /dev/tty");
            process.inheritIO().start();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void unsetRaw() throws IOException{  
        try{
            ProcessBuilder process = new ProcessBuilder("sh", "-c", "stty echo cooked < /dev/tty");
            process.inheritIO().start();
 
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public int read() throws IOException{
        setRaw();
        int caracter = super.read();
        if (caracter == Keys.ESC) { // Comprobamos si es una secuencia de escape
            caracter = super.read(); // Leemos el siguiente carácter para verificar si es una tecla especial

            if (caracter == Keys.CSI) {
                caracter = super.read(); // Leemos el tercer carácter para identificar la tecla exacta
                switch (caracter) {
                    case Keys.RIGHT:
                        return Keys.RIGHT;                   
                    case Keys.LEFT:
                        return Keys.LEFT;                    
                    case Keys.INSERT:
                        return Keys.INSERT;                     
                    case Keys.FIN:
                        return Keys.FIN;                     
                    case Keys.HOME:
                        return Keys.HOME;                   
                    case Keys.SUPR:
                        super.read();
                        return Keys.SUPR;
                    default:                
                        break;
                }
            } 
        }
    unsetRaw(); 
    return caracter; 
    }

    public String readline() throws IOException{
        setRaw();
        int caracter = this.read();

        while (caracter != Keys.RETURN) {
           caracter = this.read(); 
            switch (caracter) {
                case Keys.INSERT:
                    line.insert();
                    break;
                case Keys.RIGHT:
                    line.moveRight();
                    break;
                case Keys.LEFT:
                    line.moveLeft();
                    break;
                case Keys.HOME:
                    line.moveToStart();
                    break;
                case Keys.FIN:
                    line.moveToEnd();
                    break;
                case Keys.SUPR:
                    line.supr();
                    break;
                case Keys.BACKSPACE:
                    line.backspace();                    
                    break;
                default:
                    line.write((char) caracter);
                    System.out.print((char) caracter);
                    break;
            }
        }
        unsetRaw();
        return line.toString();
    }
}
