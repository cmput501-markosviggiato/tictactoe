package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 */
public class TicTacToeGame {

    private static Board board;
    private static int numberWonGames[]; // number of games won by players X and O, respectively
    private static int numberLostGames[];// number of games lost by players X and O, respectively
    private static int numberTiedGames;

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

    public Player playGame(){
        Scanner keyboardScanner = new Scanner(System.in);

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
                    System.out.println("Tied game!");
                    numberTiedGames =+ 1;
                    return null;
                }

                System.out.println("Invalid coordinates. Try again");
            }
        }

        board.printBoard();
        System.out.println("Player " + board.getWinner() + " has won the game!");
        return board.getWinner();
    }

    public static void calculateScores() {
        if (board.getWinner().equals(Player.X)) {
            numberWonGames[0] = numberWonGames[0] + 1;
            numberLostGames[1] = numberLostGames[1] + 1;
        }
        else {
            numberWonGames[1] = numberWonGames[1] + 1;
            numberLostGames[0] = numberLostGames[0] + 1;
        }
    }

    public static void printScoreboard() {
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

    public static void initScoreboard() {
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


    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        String keepPlaying = "yes";
        initScoreboard();
        Scanner keepPlayingScanner = new Scanner(System.in);

        while(keepPlaying.toLowerCase().equals("yes")) {
            board = new Board();
            Player winner = game.playGame();
            if (winner != null)
                calculateScores();
            printScoreboard();
            System.out.println("\nDo you want to play another game?");
            keepPlaying = keepPlayingScanner.nextLine();
            if (keepPlaying.toLowerCase().equals("no")) {
                System.out.println("This is the end of the game! You can see the final scoreboard below.");
                printScoreboard();
            }
        }
    }
}
