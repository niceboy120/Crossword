package gui;

import javax.swing.JLabel;

public class StatusBar extends JLabel {
	public StatusBar() {
		super();
		setMessage("Gotowy");
	}
	public void setMessage(String _msg) {
		setText(" "+_msg);
	}
}
