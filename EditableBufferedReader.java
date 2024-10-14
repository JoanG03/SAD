
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class EditableBufferedReader extends BufferedReader{
 

    public EditableBufferedReader(Reader text){
        super(text);
    }

    public void setRaw() throws IOException{ 
        try{
            String[] comm = {"/bin/sh", "-c", "stty raw -echo </dev/tty"}; 
            Runtime.getRuntime().exec(comm).waitFor();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public void unsetRaw() throws IOException{  
        try{
            String[] comm = {"/bin/sh", "-c", "stty cooked echo </dev/tty"};
            Runtime.getRuntime().exec(comm).waitFor();
    
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
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
                    case Keys.INICIO:
                        return Keys.INICIO;                   
                    case Keys.SUPR:
                        super.read();
                        return Keys.SUPR;
                    default:                
                        break;
                }
            } else if (caracter == Keys.ALTRES_SEQ) {
                return Keys.ALTRES_SEQ; 
            }
        }
    unsetRaw(); 
    return caracter; 
    }

    public String readline() throws IOException{
        setRaw();
        Line line = new Line();
        int caracter = 0;

        while (caracter != '\r') {
           caracter = read(); 
            switch (caracter) {
                case '\r':
                    break;
                case Keys.RIGHT:
                    line.moveRight();
                    System.out.print("\u001b[1C");
                    break;
                case Keys.LEFT:
                    line.moveLeft();
                    System.out.print("\u001b[1D");
                    break;
                case Keys.INICIO:
                    line.moveToStart();
                    break;
                case Keys.FIN:
                    line.moveToEnd();
                    break;
                case Keys.SUPR:
                    line.supr();
                    System.out.print("\u001b[1C");
                    break;
                case Keys.BACKSPACE:
                    line.backspace();                    
                    break;
                case '\n': 
                    unsetRaw();
                    System.out.print("\n"); 
                    return line.toString();
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
