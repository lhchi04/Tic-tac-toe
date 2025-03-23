// package whatever; // don't place package name!
// Installed Libraries: JSON-Simple, JUNit 4, Apache Commons Lang3
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


class TicTacToe {
	public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in);  // create a Scanner object
    boolean validSize = false;
    boolean validLength = false;
    boolean validSpot = false;
    int n = 0; // board size
    int num = 0; // length needed to win
		// Input: get board size: n
    while (!validSize) {
      try {
        System.out.print("Enter board size: ");
        n = scanner.nextInt();
        if (n < 3) {
          System.out.println("Error: Board size must be greater than or equal to 3x3.");
        } else {
          validSize = true;
        }
      } catch (InputMismatchException e) {
          System.out.println("Error: Please enter a valid size.");
          scanner.nextLine();
      }
    }
    
    // Input: get length needed to win: num
    while (!validLength) {
      try {
        System.out.print("Enter length needed to win: ");
        num = scanner.nextInt();
        if (num > n || num < 3) {
          System.out.println("Error: Length needed to win must be greater than 2 and less than or equal to size.");
        } else {
          validLength = true;
        }
      } catch (InputMismatchException e) {
          System.out.println("Error: Please enter a valid length.");
          scanner.nextLine();
      }
    }

    TicTacToe obj = new TicTacToe();
    int[][] board = new int[n][n]; // create  board n*n
    obj.printBoard(board, n);

    int a = 0; // row position
    int b = 0; // col position
    boolean gameInProgress = true;
    boolean player1turn = true; // player 1's turn
    int spotTaken = 0;
    while (gameInProgress) {
      // Check board if there is available spot
      if (spotTaken == n*n) { // no spot left
        System.out.println("Draw.");
        gameInProgress = false;
        break;
      }
      if (player1turn) { // player 1's turn
        player1turn = false;
		    // Input: get player 1's mark:
        while (!validSpot) {
          try {
            System.out.print("Player 1: Enter position: ");
            a = scanner.nextInt();
            b = scanner.nextInt(); 
            if (a < 0 || b < 0 || a > n-1 || b > n-1) {
              System.out.println("Error: Please enter a valid number.");
            } else {
              if (board[a][b] != 0) {
                System.out.println("Error: This spot is already taken.");
              } else {
                validSpot = true;
              }
            }
          } catch (InputMismatchException e) {
              System.out.println("Error: Please enter a valid number.");
              scanner.nextLine();
          }
        }
        validSpot = false;
        board[a][b] = 1;
        obj.printBoard(board, n);
        if (obj.win(board, num, 1, n)) {
          System.out.println("Player 1 wins!");
          gameInProgress = false;
          break;          
        }
      } else { // player 2's turn
        player1turn = true;
		    // Input: get player 2's mark:   
        while (!validSpot) {
          try {
            System.out.print("Player 2: Enter position: ");
            a = scanner.nextInt();
            b = scanner.nextInt(); 
            if (a < 0 || b < 0 || a > n-1 || b > n-1) {
              System.out.println("Error: Please enter a valid number.");
            } else {
              if (board[a][b] != 0) {
                System.out.println("Error: This spot is already taken.");
              } else {
                validSpot = true;
              }
            }
          } catch (InputMismatchException e) {
              System.out.println("Error: Please enter a valid number.");
              scanner.nextLine();
          }
        }
        validSpot = false;
        board[a][b] = 2;    
        obj.printBoard(board, n);
        if (obj.win(board, num, 2, n)) {
          System.out.println("Player 2 wins!");
          gameInProgress = false;
          break;          
        }
      }
      spotTaken++;
    }
	}
  
  public boolean win(int[][] board, int num, int player, int n) { // check if player wins
    int streakCount;
    int counter;
    for (int i = 0; i < n; i++) { // check horizontal lines
      streakCount = 0;
      int j = 0; // counter in a row
      while (streakCount < num && j < n) {
        if (board[i][j] == player) {
          streakCount++;
        }
        else {
          streakCount = 0;
        }
        j++;
      }
      if (streakCount == num) {
        return true; // player has 1 horizontal line
      }
    }
    for (int j = 0; j < n; j++) { // check vertical lines
      streakCount = 0;
      int i = 0; // counter in a col
      while (streakCount < num && i < n) {
        if (board[i][j] == player) {
          streakCount++;
        }
        else {
          streakCount = 0;
        }
        i++;
      }
      if (streakCount == num) {
        return true; // player has 1 vertical line
      }
    }
    for (int i = 0; i < n-num+1; i++) { // check 1st diagonal lines down right from every row
      counter = 0;
      streakCount = 0;
      while (streakCount < num && counter < (n-i)) { // limit is the last row
        if (board[i+counter][counter] == player) {
          streakCount++;
        } else {
          streakCount = 0;
        }
        counter++;
      }
      if (streakCount == num) {
        return true; // player has 1 diagonal line
      }
    }
    for (int j = 1; j < n-num+1; j++) { // check 1st diagonal lines down right from every col
      counter = 0;
      streakCount = 0;
      while (streakCount < num && counter < (n-j)) { // limit is the last col
        if (board[counter][j+counter] == player) {
          streakCount++;
        } else {
          streakCount = 0;
        }
        counter++;
      }
      if (streakCount == num) {
        return true; // player has 1 diagonal line
      }
    }
    for (int i = num-1; i < n; i++) { // check 2nd diagonal lines up right from every row
      counter = 0;
      streakCount = 0;
      while (streakCount < num && (i-counter >= 0)) { // limit is the first row
        if (board[i-counter][counter] == player) {
          streakCount++;
        } else {
          streakCount = 0;
        }
        counter++;
      }
      if (streakCount == num) {
        return true; // player has 1 diagonal line
      }
    }
    for (int j = 1; j < n-num+1; j++) { // check 2nd diagonal lines up right from every col
      int counter1 = n-1;
      int counter2 = 0;
      streakCount = 0;
      while (streakCount < num && (j+counter2 < n)) { // limit is the last col
        if (board[counter1][j+counter2] == player) {
          streakCount++;
        } else {
          streakCount = 0;
        }
        counter1--;
        counter2++;
      }
      if (streakCount == num) {
        return true; // player has 1 diagonal line
      }
    }
    return false;
  }

  public void printBoard(int[][] board, int n) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
    System.out.println("\n");    
  }
}