package connectfourpackage;

import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import java.util.Scanner;

public class Game extends JFrame{
	private JPanel jpanel;
	private ConnectFourBoard board;
	private Player currPlayer;
	private Player player1;
	private Player player2;
	private final ImageIcon HEART = new ImageIcon("heart.png");
	private final ImageIcon STAR = new ImageIcon("star.png");
	
	public Game(){
		player1 = new Player();
		player2 = new Player();
		currPlayer = new Player();
		currPlayer = player1;
		ConnectFourBoard gameboard = new ConnectFourBoard();
		add(gameboard);
	}
	
	private class ConnectFourBoard extends JPanel implements ActionListener{
		private JLabel [][] board; //game board
		private JLabel player1Label;
		private JLabel player2Label;
		private JLabel cpLabel;
		private JPanel names;
		private JPanel scores;
		private JPanel chart;
		private JPanel choices;
		private JPanel cpDisplay;
		private JButton [][] buttons;
		
		public ConnectFourBoard() {
			//GridLayout(height, width)
			setLayout(new GridLayout(6,7) );
			welcome();
			//showBoard();
		}
		
		public void showBoard() { //Good :)
			GridBagConstraints c = new GridBagConstraints();
			names = new JPanel(new GridBagLayout());
			scores = new JPanel(new BorderLayout());
			chart = new JPanel(new GridLayout(6,7));
			choices = new JPanel(new GridLayout(1,7));
			cpDisplay = new JPanel(new GridBagLayout());
			player1Label = new JLabel(player1.getName());
			player2Label = new JLabel(player2.getName());
			cpLabel = new JLabel(currPlayer.getName());
			
			names.setBackground(Color.PINK);
			scores.setBackground(Color.YELLOW);
			chart.setBackground(Color.BLUE);
			cpDisplay.setBackground(Color.ORANGE);
			names.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Game History"));
			scores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Scores"));
			add(names, BorderLayout.NORTH);
			add(scores,BorderLayout.NORTH);
			add(cpDisplay);
			add(choices,BorderLayout.NORTH);
			add(chart, BorderLayout.CENTER);
			names.add(player1Label, c);
			c.gridy = 5;
			names.add(player2Label, c);
			cpDisplay.add(cpLabel);
			
			// Placing the buttons
			buttons = new JButton[1][7];
			for (int col = 0; col < 7; col++) {
				for(int row = 0; row < buttons.length; row++) {
					buttons[row][col] = new JButton(""+ (col + 1));
					buttons[row][col].addActionListener(this);
					choices.add(buttons[row][col]);
				}
			}// end of board setup

			
			board = new JLabel[6][7];		
			for (int row = 0; row < board.length; row++) {
				for(int col = 0; col <= board.length; col++) {
					board[row][col] = new JLabel("[" + row + "]" + " [" + col + "]");
					chart.add(board[row][col]);
				}
			}// end of board setup
			
		}
		
		public void placeMarker(int row, int col) {
			if(currPlayer==player1) {
				board[row][col].setText("X");
			}else if (currPlayer == player2) {
				board[row][col].setText("O");
			}
		}
		
		public void takeTurn() { //Good :)
			if (currPlayer == player1) {
				currPlayer = player2;
				cpLabel.setText("Current Player: " + currPlayer.getName());
			} else {
				currPlayer = player1;
				cpLabel.setText("Current Player: " + currPlayer.getName());
			}
			
		}
		
		public void welcome() { //Still thinking whether or not I can do this
			Scanner scnr = new Scanner(System.in);
			System.out.println("Welcome to Connect Four! \nPlease enter Player 1");
			player1.setName(scnr.nextLine().toUpperCase());
			System.out.println("Please enter Player 2");
			player2.setName(scnr.nextLine().toUpperCase());
			System.out.println("Beginning the game of " + player1.getName() + " VS " + player2.getName());
			showBoard(); //opens GUI
			
			
			//SAME THING BUT IN GUI
			/*
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
			*/
		}
		
		public void playTurn(int col) {
			if (board[5][col].getText().equalsIgnoreCase("O") || board[5][col].getText().equalsIgnoreCase("X")){
				for(int row = 0; row < 5; row++) {
					if (board[row + 1][col].getText().equalsIgnoreCase("O") || board[row + 1][col].getText().equalsIgnoreCase("X")) {
						if (currPlayer == player1) {
							placeMarker(row, col);
							System.out.println("An X was placed at " + row + "," +col);
							break;
						} else {
							placeMarker(row, col);
							System.out.println("An O was placed at " + row + "," +col);
							break;
						}
					}
				}
			} else {
				if (currPlayer == player1) {
					placeMarker(5, col);
					System.out.println("An X was placed at the bottom of column " + col);
				} else {
					placeMarker(5, col);
					System.out.println("An O was placed at the bottom of column " + col);
				}
			}
			
		}
		
		@Override
		public void actionPerformed(ActionEvent b) {
			JButton button = (JButton)b.getSource();
			
			int column = Integer.parseInt(button.getText()); //get the number of the button
			
			if (button.getText().equalsIgnoreCase("start")) {
				showBoard();
			}else {
				playTurn(column - 1);
				takeTurn(); 
			}
			
		}
			
		
		} //END OF CONNECTFOURBOARD CLASS
	
}