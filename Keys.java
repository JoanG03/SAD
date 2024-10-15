interface Keys {

// Codificaciones de las secuencias de escape 
    int DELETE = 8;
    int RETURN = 13;
    int BACKSPACE = 127;

    int ESC = 27;           // ESC => ^[
    int CSI = 91;           // CSI => [


    // Funciones con ESC + CSI

    int RIGHT = 67;         // ^[[C  (Flecha derecha)
    int LEFT = 68;          // ^[[D  (Flecha izquierda)
    int UP = 65;            // ^[[A  (Flecha arriba)
    int DOWN = 66;          // ^[[B  (Flecha abajo)
    int FIN = 70;           // ^[[F  (Fin)
    int HOME = 72;        // ^[[H  (Home)
    int INSERT = 50;        // ^[[2~ (Insertar)
    int SUPR = 51;          // ^[[3~ (Suprimir)

}
