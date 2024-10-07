import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

public class EditableBufferedReader extends BufferedReader{
 

    public EditableBufferedReader(Reader text){
        super(text);
    }

    public void setRaw() throws IOException{ 
        try{
            String[] comm = {"/bin/sh", "-c", "stty raw -echo </dev/tty"}; 

        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
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

    public int read() throws IOException{
        int caracter = 0;
        setRaw();
        caracter = super.read();

        if(caracter == Keys.ESC){
            caracter = super.read(); 
            if(caracter == Keys.ESTILO){
                caracter = 
                return caracter;
            }

            caracter= super.read();
        
                switch (caracter) {
                    case Keys.INSERT:
                        caracter = 
                        break;
                    case Keys.RIGHT:
                        caracter =
                        break;
                    case Keys.LEFT:
                        caracter =
                        break;
                    case Keys.INICIO:
                        caracter = 
                        break;
                    case Keys.FIN:
                        caracter = 
                        break;
                    case Keys.SUPR:
                        caracter = 
                        break;
                    default:
                        // Omite el "~" si es una secuencia de escape numérica
                        if (caracter <= 64) {
                            super.read();
                        }
                        break;
                }
            }

            unsetRaw();
            return caracter;
    }
