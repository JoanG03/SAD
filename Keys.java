interface Keys {

// Codificaciones de las secuencias de escape 

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
    int INICIO = 72;        // ^[[H  (Inicio)
    int INSERT = 50;        // ^[[2~ (Insertar)
    int AVPAG = 54;         // ^[[6~ (Avanzar página)
    int REPAG = 53;         // ^[[5~ (Retroceder página)
    int SUPR = 51;          // ^[[3~ (Suprimir)
    int ESTILO = 101;       // ^[e   (Estilo)

}