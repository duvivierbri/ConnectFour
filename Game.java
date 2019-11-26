package connectfourpackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Game extends JFrame{
	private JPanel jpanel;
	private ConnectFourBoard board;
	private Player currPlayer;
	private Player player1;
	private Player player2;
	private final ImageIcon HEART = new ImageIcon("images/heart.png");
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
		private JButton [][] board;
		private JLabel player1score;
		private JLabel player2score;
		
		public ConnectFourBoard() {
			//GridLayout(height, width)
			setLayout(new GridLayout(6,7) );
			showBoard();
		}
		
		public void showBoard() { //Good :)
			board = new JButton[6][7];		
			
			for (int col = 0; col <= board.length; col++) {
				for(int row = 0; row < board.length; row++) {
					board[row][col] = new JButton();
					board[row][col].addActionListener(this);
					add(board[row][col]);
				}
			}// end of board setup
		}

		@Override
		public void actionPerformed(ActionEvent b) {
			JButton button = (JButton)b.getSource();
			
				for(int row=0; row<board.length; row++){
					for(int col=0; col<board[row].length; col++){
						if (canPlace(row, col) == false) {
							System.out.println("Sorry you cannot go there");
						}else {
							if (board[row][col] == button) {
								placeMarker(row,col);
								takeTurn();
							}
					}
				}
				

			}// end of turn ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
		
		}
		
		public boolean canPlace(int row, int col) {
			return true;
		}
		
		public void takeTurn() { //Good :)
			if (currPlayer == player1) {
				currPlayer = player2;
			} else {
				currPlayer = player1;
			}
			
		}
		
		public void placeMarker(int row, int col) {
			if(currPlayer == player1) {
//				board[row][col].setIcon(new ImageIcon(((Image) HEART.getImage()).getScaledInstance(200, 200, Image.SCALE_FAST)));
				board[row][col].setText("1");
			} else {
				board[row][col].setText("2");
			}
		}
		
		public void rowCheck() {
			
		}
	}
}
