
public class Line {

	private int cursorPos;
	private StringBuilder text;
	private boolean insert;

	public Line() {
		cursorPos = 0;
		text = new StringBuilder();
		insercio = false;
	}

	public int getPos() {
		return this.cursorPos;
	}

	public void write(char ch) {
        if (cursorPos < text.length()) {
            if (insert) text.setCharAt(cursorPos, ch);
            else {
                System.out.print("\u001b[1@");
                text.insert(cursorPos, ch);
            }
        } else {
            text.append(ch);
        }
        cursorPos++;
    }


	public void backspace() {
		if (cursorPos > 0) {
			cursorPos--;
			text.deleteCharAt(cursorPos);
			System.out.print("\u001b[D");
			System.out.print("\u001b[P");
		}
	}

	public void delete() {
		if (cursorPos >= 0 && cursorPos < text.length()) {
			text.deleteCharAt(cursorPos);
			System.out.print("\u001b[P");
		}
	}

	public void insert() {
		insert = !insert;
	}

	public void moveRight() {
		if (cursorPos < text.length()) {
			cursorPos++;
			System.out.print("\u001b[C");
		}
	}

	public void moveLeft() {
		if (cursorPos > 0) {
			cursorPos--;
			System.out.print("\u001b[D");
		}
	}

	public void home() {
		cursorPos = 0;
		System.out.print("\u001b[G");
	}

	public void fin() {
		cursorPos = text.length();
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
