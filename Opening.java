package connectfourpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Opening extends JFrame{
	
	public Opening() {
		OpeningGUI whatsup = new OpeningGUI();
		add(whatsup);
	}

	private class OpeningGUI extends JPanel implements ActionListener{

		public OpeningGUI(){
			welcome();
		}
		
		public void welcome() {
			JPanel title = new JPanel(new BorderLayout());
			title.setBackground(Color.WHITE);
			JLabel welcome = new JLabel("Welcome! Please Type in the names of Player 1 and Player 2 :)");
			add(title, BorderLayout.NORTH);
			title.add(welcome, BorderLayout.CENTER);
			
			JPanel inputs = new JPanel(new BorderLayout());
			inputs.setBackground(Color.ORANGE);
			
			JLabel p1label = new JLabel("Player 1 Name:");
			JLabel p2label  = new JLabel("Player 2 Name:");
			JTextField p1 = new JTextField();
			JTextField p2 = new JTextField();
			add(p1label);
			add(p1);
			add(p2label);
			add(p2);
			
			JButton begin = new JButton("START");
			begin.addActionListener(this);
			add(begin);
			
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					Game gui = new Game();
					gui.setTitle("Welcome!!");
					gui.setVisible(true);
					gui.setSize(1000,700);
					gui.setResizable(false);
					gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensures everything terminates

				}
				
				/*
				@Override
				public void run() {
					Game gui = new Game();
					gui.setTitle("Brianna's Connect Four");
					gui.setVisible(true);
					gui.setSize(1000,700);
					gui.setResizable(false);
					gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensures everything terminates

				}
				*/
			});
			
		}
		
	}
	
}
