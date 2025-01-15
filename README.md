SAD

Pràctiques

P0: EditableBufferedReader

Aplicació per editar text des de la consola de comandes.

Hem desenvolupat dues versions:



Versió sense MVC

Programació de la classe EditableBufferedReader amb extensió BufferedReader i afegint capacitats d'edició.



right, left, up, down: amb les fletxes.

home, fin: principi, final de línia.

ins: commuta mode inserció/sobre-escriptura.

del, bksp: esborra caràcter actual o caràcter a l’esquerra.



Versió amb MVC

Model (Línia): representa l'estat de l'aplicació i pot canviar en funció de les accions que s'hi realitzin.

View (Consola): Hem fet que s'encarregui de mostrar l'estat de l'aplicació. Un mateix model pot estar associat a diverses vistes diferents.

Controller (EditableBufferedReader): hem fet que actuí com un gestor, amb això aconseguim provocar canvis respecte a l'estat del model.





P1: ClientServerChat

Hem prograamat l'aplicació de Xat textual amb servidor centralitzat amb les clases MySocket i MyServerSocket que contenen les excepcions i streams de text BufferedReader i PrintWriter.



P2: Client gràfic amb Swing

Amb la base de la P1 hem implementat el client de xat amb la biblioteca gràfica Swing, això ens ha permès veure i editar l'entorn gràfic com hem volgut.







Projecte: Buscaminas



Hem creat un buscaminas a partir d'un fitxer html d'un script java encarregat de controlar la lògica del joc i d'un fitxer d'estils css. Per a jugar el que cal fer és executar el fitxer html amb doble clic i seleccionar la dificultat que volem. Una vegada escollida la dificultat es genera un tauler i s'activa el temporitzador. A partir d'aquí el joc segueix les normes clàssiques del buscamines. Per marcar les bombes has de fer clic dret sobre les seves caselles. En cas de voler netejar les caselles segures, has de fer clic esquerre sobre elles.

