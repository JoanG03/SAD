import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

public class EditableBufferedReader extends BufferedReader{

public EditableBufferedReader(Reader text){
    super(text);
    this.vista= new View();
}

public void setRaw() throws IOException{ 
    try{
        String[] comm = {"/bin/sh", "-c", "stty raw -echo </dev/tty"}; 

    }catch(InterruptedException ex){
        ex.printStackTrace();
    }
}

public void unsetRaw() throws IOException{  
    try{
        String[] comm = {"/bin/sh", "-c", "stty cooked echo </dev/tty"};
        Runtime.getRuntime().exec(comm).waitFor();
 
    }catch(InterruptedException ex){
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
            caracter = Keys.xESTILO;
            return caracter;
        }
        caracter= super.read();
        if(caracter>64){//Si després llegim una lletra
            switch (caracter) {
                case Keys.INSERT: caracter = Keys.xINSERT;
                    break;
                case Keys.RIGHT: caracter = Keys.xRIGHT;
                    break;
                case Keys.LEFT: caracter = Keys.xLEFT;
                    break;
                case Keys.INICIO: caracter= Keys.xINICIO;
                    break;
                case Keys.FIN: caracter= Keys.xFIN;
                    break;
            } 
        }else{ //Si llegim un número
            switch (caracter){
                case Keys.INSERT: caracter=Keys.xINSERT;
                    break;
                case Keys.SUPR: caracter= Keys.xSUPR;
                    break;
            }
            super.read(); //Obviem el "~"
        }
    }

    return caracter;
}
