package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 */
public class TicTacToeGame {

    private Board board;
    private int numberWonGames[]; // number of games won by players X and O, respectively
    private int numberLostGames[];// number of games lost by players X and O, respectively
    private int numberTiedGames;

    public TicTacToeGame(){
        board = new Board();
    }

    public void promptNextPlayer(){
        switch(board.getCurrentPlayer()){
            case X:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;
            case O:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;

        }
    }

    public void playGame(){
        Scanner keyboardScanner = new Scanner(System.in);
        initScoreboard();

        while (board.getWinner() == null){
            board.printBoard();
            promptNextPlayer();
            String line = keyboardScanner.nextLine();
            String input[] = line.split(",");
            try {
                board.playMove(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            } catch (InvalidMoveException e) {
				
	    	if (e.getMessage().equals("tie")) {
            		board.printBoard();
            		System.out.println("Tied game! This is the end of the game.");
            		numberTiedGames =+ 1;
            		printScoreboard();
            		return;
            	}
				
            	System.out.println("Invalid coordinates. Try again");
            	promptNextPlayer();
              }
        }

        board.printBoard();
        System.out.println("Player " + board.getWinner() + " has won the game!");
        calculateScores();
        printScoreboard();
    }
    
    public void initScoreboard() {
    	// number of games won by players X and O, respectively
        numberWonGames = new int[2];
        numberWonGames[0] = 0;
        numberWonGames[1] = 0;
        // number of games lost by players X and O, respectively
        numberLostGames = new int[2];
        numberLostGames[0] = 0;
        numberLostGames[1] = 0;
        numberTiedGames = 0;
    }
    
    public void calculateScores() {
    	if (board.getWinner().equals(Player.X)) {
    		numberWonGames[0] =+ 1;
    		numberLostGames[1] =+ 1;
    	}
    	else {
    		numberWonGames[1] =+ 1;
    		numberLostGames[0] =+ 1;
    	}
    }
    
    public void printScoreboard() {
    	System.out.println("\nScoreboard:\n");
    	System.out.println("          |Won games |Lost games|Tied games");
    	System.out.println("------------------------------------------");
        System.out.print("Player X  |");
        System.out.print(numberWonGames[0] + "         |");
        System.out.print(numberLostGames[0] + "         |");
        System.out.println(numberTiedGames);
        System.out.println("------------------------------------------");
        System.out.print("Player O  |");
        System.out.print(numberWonGames[1] + "         |");
        System.out.print(numberLostGames[1] + "         |");
        System.out.println(numberTiedGames);
        System.out.println("------------------------------------------");   
    }
    

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
