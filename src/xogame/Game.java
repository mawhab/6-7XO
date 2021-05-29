package xogame;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Cell[][] arr = new Cell[6][7]; // creating array of cell objects
		
		for(int i=0;i<6;i++) { // initializing each element of array
			for(int j=0;j<7;j++) {
				arr[i][j] = new Cell(i,j);
			}
		}
		Scanner in = new Scanner(System.in); // creating scanner object to read input from user
		boolean turn = true; // turn variable to keep track of whose turn it is
		int x,y; // variables to hold coordinates entered by user
		Cell.display(arr); // displaying board first time
		while(true) { // main game loop
			if(turn) // if x turn
				System.out.println("X to play!");
			else 
				System.out.println("O to play!");
			
			System.out.println("Please enter row:");
			x = in.nextInt();
			System.out.println("Please enter column:");
			y = in.nextInt();
			x--; // user will input row and column starting from 1 so decrement to get correct position
			y--;
			if(x>5 || x<0 || y>6 || y<0) { // checking if input is out of bounds of board
				System.out.println("Invalid input, please try again.");
				continue;
			}
			if(arr[x][y].getType()!='-') { // checking if cell is already occupied
				System.out.println("Space already occupied, try another square.");
				continue;
			}
			
			if(turn) // if x turn
				arr[x][y].assign('X', arr);
			else
				arr[x][y].assign('O', arr);
			
			if(Cell.gameOver) // in case a winner has been found break out of the game loop
				break;
			
			turn = !turn; // switching turn

		}
		in.close(); // closing scanner object

	}

}
