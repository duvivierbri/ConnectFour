package connectfourpackage;

import javax.swing.JFrame;

public class ShowGUI {

	public static void main(String [] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Game gui = new Game();
				gui.setTitle("Brianna's Connect Four");
				gui.setVisible(true);
				gui.setSize(1000,700);
				gui.setResizable(false);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensures everything terminates

			}
		});
	}
}
