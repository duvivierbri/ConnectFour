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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Game extends JFrame{
	private JPanel jpanel;
	private ConnectFourBoard board;
	private Player currPlayer;
	private Player champion;
	private Player lastWinner;
	private Player player1;
	private Player player2;
	private int player1score;
	private int player2score;
	private int roundNum;
	
	public Game(){
		player1 = new Player();
		player2 = new Player();
		currPlayer = new Player();
		champion = new Player();
		lastWinner = new Player();
//		champion = player1;
		currPlayer = player1;
		player1score = 0;
		player2score = 0;
		roundNum = 0;
		ConnectFourBoard gameboard = new ConnectFourBoard();
		add(gameboard);
	}
	
	private class ConnectFourBoard extends JPanel implements ActionListener{
		private JLabel [][] board; //game board
		private JLabel player1Label;
		private JLabel player2Label;
		private JLabel cpLabel;
		private JLabel p1score;
		private JLabel p2score;
		private JPanel names;
		private JPanel scores;
		private JPanel chart;
		private JPanel choices;
		private JPanel cpDisplay;
		private JButton [][] buttons;
		private FileHandler handler = new FileHandler();
		
		
		public ConnectFourBoard() {
			setLayout(new GridLayout(5,7) );
			start();
		}
		
		public void showBoard() { //Good :)
			FileInputStream fileStream = null;
			Scanner outstream = null;
			handler.createNewFile("Connect4-Results.txt");
			handler.appendToFile("Connect4-Results.txt", "New game started between " + player1.getName() + " and " + player2.getName() + ".");
			GridBagConstraints c = new GridBagConstraints();
			names = new JPanel(new GridBagLayout());
			scores = new JPanel(new GridBagLayout());
			chart = new JPanel(new GridLayout(6,7));
			choices = new JPanel(new GridLayout(1,7));
			cpDisplay = new JPanel(new GridBagLayout());
			player1Label = new JLabel("Champion: "+ champion.getName());
			player2Label = new JLabel("Last Winner: " + lastWinner.getName());
			p1score = new JLabel("Player1 - " + player1.getName() + "               Score: " + player1score);
			p2score = new JLabel("Player2 - " + player2.getName() + "               Score: " + player2score);
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
			scores.add(p1score);
			scores.add(p2score, c);
			
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
							break;
						} else {
							placeMarker(row, col);
							break;
						}
					}
				}
			} else {
				if (currPlayer == player1) {
					placeMarker(5, col);
				} else {
					placeMarker(5, col);
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
							player1score++;
							lastWinner = player1;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
							break;
						}
					} else if (board[row][col].getText().equalsIgnoreCase("O")){
						p2Num++;
						p1Num = 0;
						if (p2Num == 4) {
							JOptionPane.showMessageDialog(null, "Player2 wins!");
							player2score++;
							lastWinner = player2;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
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
							player1score++;
							lastWinner = player1;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
							break;
						}
					} else if(board[row][col].getText().equalsIgnoreCase("O")) {
						p2Num++;
						p1Num = 0;
						if (p2Num == 4) {
							JOptionPane.showMessageDialog(null,"Player2 wins!");
							player2score++;
							lastWinner = player2;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
							break;
						}
					} else {
						p2Num = 0;
						p1Num = 0;
					}
				}
			}
		}
		
		public void diagonalCheck1() { //FINISH LATER
			int p1Num;
			int p2Num;
			int rowNum;
			int colNum;
			int row = 0;
			int col = 0;
			
			rowNum = 3;
			colNum = 0;
			
			while (rowNum <= 5) { //(From top to bottom)
				row = rowNum;
				col = colNum;
				p1Num = 0;
				p2Num = 0;
				while (row >= 0 && col <= 6) {
					if (board[row][col].getText().equalsIgnoreCase("X")) {
						p1Num++;
						
						if (p1Num == 4) { //check if Player1 won
							JOptionPane.showMessageDialog(null, "Player1 wins!");
							player1score++;
							lastWinner = player1;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
						}
						
					} else if (board[row][col].getText().equalsIgnoreCase("O")){
						p2Num++;
						
						if (p2Num == 4) { //check if Player2 won
							JOptionPane.showMessageDialog(null, "Player2 wins!");
							player2score++;
							lastWinner = player2;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
						}
						
					} else {
						p1Num = 0;
						p2Num = 0;
					}
					
					if (row == 0 && col == 5) {
						break;
					}
					
					row--;
					col++;
				}
				
				
				rowNum++;
			}
		
		}
		
		public void diagonalCheck2() {
			int p1Num;
			int p2Num;
			int rowNum;
			int colNum;
			int row = 0;
			int col = 0;
			
			rowNum = 5;
			colNum = 1;
			
			while (rowNum <= 5) { //(From top to bottom)
				row = rowNum;
				col = colNum;
				p1Num = 0;
				p2Num = 0;
				
				if (row == 5 && col == 4) { //makes sure there is no out of bounds exception
					break;
				}
				
				while (row >= 0 && col <= 6) {
					if (board[row][col].getText().equalsIgnoreCase("X")) {
						p1Num++;
						
						if (p1Num == 4) { //check if Player1 won
							JOptionPane.showMessageDialog(null, "Player1 wins!");
							player1score++;
							lastWinner = player1;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
						}
						
					} else if (board[row][col].getText().equalsIgnoreCase("O")){
						p2Num++;
						
						if (p2Num == 4) { //check if Player2 won
							JOptionPane.showMessageDialog(null, "Player2 wins!");
							player2score++;
							lastWinner = player2;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
						}
						
					} else {
						p1Num = 0;
						p2Num = 0;
					}
					
					row--;
					col++;
				}
				
				
				colNum++;
			}
		}
		
		public void diagonalCheck3() {
			int p1Num;
			int p2Num;
			int rowNum;
			int colNum;
			int row = 0;
			int col = 0;
			
			rowNum = 0;
			colNum = 3;
			
			while (rowNum <= 5) { //(From top to bottom)
				row = rowNum;
				col = colNum;
				p1Num = 0;
				p2Num = 0;
				
				while (row >= 0 && col <= 6) {
					if (board[row][col].getText().equalsIgnoreCase("X")) {
						p1Num++;
						
						if (p1Num == 4) { //check if Player1 won
							JOptionPane.showMessageDialog(null, "Player1 wins!");
							player1score++;
							lastWinner = player1;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
						}
						
					} else if (board[row][col].getText().equalsIgnoreCase("O")){
						p2Num++;
						
						if (p2Num == 4) { //check if Player2 won
							JOptionPane.showMessageDialog(null, "Player2 wins!");
							player2score++;
							lastWinner = player2;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
						}
						
					} else {
						p1Num = 0;
						p2Num = 0;
					}
					
					if (row == 5 && col == 5) {
						break;
					}
					row++;
					col++;
				}
				
				if (colNum == 0) {
					break;
				}
				colNum--;
			}
		}
		
		public void diagonalCheck4() {
			int p1Num;
			int p2Num;
			int rowNum;
			int colNum;
			int row = 0;
			int col = 0;
			
			rowNum = 1;
			colNum = 0;
			
			while (rowNum <= 5) { //(From top to bottom)
				row = rowNum;
				col = colNum;
				p1Num = 0;
				p2Num = 0;
				
				while (row >= 0 && col <= 6) {
					if (board[row][col].getText().equalsIgnoreCase("X")) {
						p1Num++;
						
						if (p1Num == 4) { //check if Player1 won
							JOptionPane.showMessageDialog(null, "Player1 wins!");
							player1score++;
							lastWinner = player1;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							clearBoard();
						}
						
					} else if (board[row][col].getText().equalsIgnoreCase("O")){
						p2Num++;
						
						if (p2Num == 4) { //check if Player2 won
							JOptionPane.showMessageDialog(null, "Player2 wins!");
							player2score++;
							lastWinner = player2;
							player2Label.setText("Last Winner: " + lastWinner.getName());
							
							clearBoard();
						}
						
					} else {
						p1Num = 0;
						p2Num = 0;
					}
					
					if(row == 5 && col == 4) {
						break;
					}
					
					if(row == 5 && col == 3) {
						break;
					}
					row++;
					col++;
				}
				
				if (rowNum == 2) {
					break;
				}
				rowNum++;
			}
		
		}
		
		public void clearBoard() {
			System.out.println("Round " + roundNum + " has just finished.");
			for (int row = 0; row < board.length; row++) {
				for(int col = 0; col <= board.length; col++) {
					board[row][col].setText("[" + row + "]" + " [" + col + "]");
				}
			}
			roundNum++;
			updateSaveFile();
		}
		
		public void updateSaveFile() {
				if (player1score > player2score) {
					handler.appendToFile("Connect4-Results.txt","Round " + roundNum + ": "+ player1.getName() + " is the current champion.");
					champion = player1;
					player1Label.setText("Champion "+ player1.getName());
				} else if (player2score > player1score) {
					handler.appendToFile("Connect4-Results.txt","Round " + roundNum + ": "+ player2.getName() + " is the current champion.");
					champion = player2;
					player1Label.setText("Champion "+ player2.getName());
				} else {
					handler.appendToFile("Connect4-Results.txt", "Round " + roundNum + ": " + "There is currently a tie between " + player1.getName() + " and " + player2.getName());
				}
		}
		
		public void updateScore() {
			p1score.setText("Player1 - " + player1.getName() + "               Score: " + player1score);
			p2score.setText("Player2 - " + player2.getName() + "               Score: " + player2score);
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
				diagonalCheck1();
				diagonalCheck2();
				diagonalCheck3();
				diagonalCheck4();
				updateScore();
			}
		}
		
		
		} //END OF CONNECTFOURBOARD CLASS
	
}