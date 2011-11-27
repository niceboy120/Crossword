package gui;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

class ErrorDialog implements ActionListener {

		ErrorDialog(Component _dialogParent, String _message) {
			dialogParent = _dialogParent;
			message = _message;
		}
		
		public void actionPerformed(ActionEvent e) {
			show();
		}
		
		public void show() {
			JOptionPane.showMessageDialog(dialogParent, message, 
				    "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);
		}
		
		Component dialogParent;
		String message;
	}