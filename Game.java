package connectfourpackage;

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

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
			setLayout(new GridLayout(5,7) );
			start();
		}
		
		public void showBoard() { //Good :)
			GridBagConstraints c = new GridBagConstraints();
			names = new JPanel(new GridBagLayout());
			scores = new JPanel(new BorderLayout());
			chart = new JPanel(new GridLayout(6,7));
			choices = new JPanel(new GridLayout(1,7));
			cpDisplay = new JPanel(new GridBagLayout());
			player1Label = new JLabel("Player1: "+ player1.getName());
			player2Label = new JLabel("Player2: " + player2.getName());
			cpLabel = new JLabel("Current Player: "+ currPlayer.getName());
			
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
					buttons[row][col] = new JButton(""+ (col));
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
		
		public void start() { //Still thinking whether or not I can do this
			
			player1.setName(JOptionPane.showInputDialog(null, "Welcome To Connect Four!\nWho Is Player1?", "[PLAYER1]"));
			player2.setName(JOptionPane.showInputDialog(null, "Who Is Player2?", "[PLAYER2]"));
			showBoard();
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
		
		public void rowCheck() { //Good
			for(int row = 0; row <= 5; row++) {
				int p1Num = 0; //X's
				int p2Num = 0; //O's
				for (int col = 0; col <= 6; col++) {
					if (board[row][col].getText().equalsIgnoreCase("X")) {
						p1Num++;
						p2Num = 0;
						if (p1Num == 4) {
							JOptionPane.showMessageDialog(null, "Player1 wins!");
							break;
						}
					} else if (board[row][col].getText().equalsIgnoreCase("O")){
						p2Num++;
						p1Num = 0;
						if (p2Num == 4) {
							JOptionPane.showMessageDialog(null, "Player2 wins!");
							break;
						}
					} else {
						p1Num = 0;
						p2Num = 0;
					}
				}
				
				
			}
		}
		
		public void columnCheck() { //GOOD :)
			for(int col = 0; col <= 6; col++) {
				int p1Num = 0;
				int p2Num = 0;
				for(int row = 0; row<=5; row++) {
					if(board[row][col].getText().equalsIgnoreCase("X")) {
						p1Num++;
						p2Num=0;
						if (p1Num == 4) {
							JOptionPane.showMessageDialog(null,"Player1 wins!");
							break;
						}
					} else if(board[row][col].getText().equalsIgnoreCase("O")) {
						p2Num++;
						p1Num = 0;
						if (p2Num == 4) {
							JOptionPane.showMessageDialog(null,"Player2 wins!");
							break;
						}
					} else {
						p2Num = 0;
						p1Num = 0;
					}
				}
			}
		}
		
		public void diagonalCheck() {
			
		}
		
		@Override
		public void actionPerformed(ActionEvent b) {
			JButton button = (JButton)b.getSource();
			int column = Integer.parseInt(button.getText()); //get the number of the button
			
			if (button.getText().equalsIgnoreCase("start")) {
				showBoard();
			}else{
				playTurn(column);
				takeTurn();
				rowCheck();
				columnCheck();
			}
		}
		
		
		} //END OF CONNECTFOURBOARD CLASS
	
}
