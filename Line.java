
class Line {
    private StringBuilder text;
    private int cursor;

    public Line() {
        text = new StringBuilder();
        cursor = 0;
    }

    public void write(char c) {
        text.insert(cursor, c);
        cursor++;
        System.out.print(c);
    }

    public void backspace() {
        if (cursor > 0) {
            cursor--;
            text.deleteCharAt(cursor);
            System.out.print("\u001b[1D");
            System.out.print("\u001b[P");
        }
    }

    public void moveRight() {
        if (cursor < text.length()) {
            cursor++;
        }
    }

    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    public void moveToStart() {
        int col = cursor;
        cursor = 0;
        System.out.print("\u001b[" + cursor + "D");
        cursor = 0;
    }

    public void moveToEnd() {
        int numLletres = text.length();
        cursor = numLletres;
        System.out.print("\u001b[" + (numLletres) + "C");
    }

    public void insert(char c) {
        if (cursor < text.length()) {
            text.setCharAt(cursor, c);
        } else {
            text.append(c);
        }
        cursor++;
    }

    public void supr() {
        if (cursor < text.length()) {
            text.deleteCharAt(cursor);
            System.out.print("\u001b[P");
        }
    }

    public int getCol() {
        return cursor;
    }

    public int getNumLetters() {
        return text.length();
    }

    @Override
    public String toString() {
        return text.toString();
    }
}

