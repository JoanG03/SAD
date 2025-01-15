package P0
public class Line {

	private int cursor;
	private StringBuilder text;
	private boolean insercio;

	public Line() {
		cursor = 0;
		text = new StringBuilder();
		insercio = false;
	}
	public int getPos() {
		return this.cursor;
	}
	
	public void write(char ch) {
        if (cursor < text.length()) {
            if (insercio) text.setCharAt(cursor, ch);
            else {
                System.out.print("\u001b[1@");
                text.insert(cursor, ch);
            }
        } else {
            text.append(ch);
        }
        cursor++;
    }

	public void backspace() {
		if (cursor > 0) {
			cursor--;
			text.deleteCharAt(cursor);
			System.out.print("\u001b[D");
			System.out.print("\u001b[P");
		}
	}

	public void supr() {
		if (cursor >= 0 && cursor < text.length()) {
			text.deleteCharAt(cursor);
			System.out.print("\u001b[P");
		}
	}

	public void insert() {
		insercio = !insercio;
	}

	public void moveRight() {
		if (cursor < text.length()) {
			cursor++;
			System.out.print("\u001b[C");
		}
	}

	public void moveLeft() {
		if (cursor > 0) {
			cursor--;
			System.out.print("\u001b[D");
		}
	}

	public void moveToStart() {
		cursor = 0;
		System.out.print("\u001b[G");
	}

	public void moveToEnd() {
		cursor = text.length();
		System.out.print("\u001b[" + (text.length() + 1) + "G");
	}

	public String getLine() {
		return text.toString();
	}

	@Override
	public String toString() {
		return text.toString();
	}
}
