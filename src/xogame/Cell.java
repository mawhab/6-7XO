package xogame;

public class Cell {
	private char type; // either x or o
	private int yNeighbors; // holds number of similar neighbors on x axis
	private int  xNeighbors; // holds number of similar neighbors on y axis
	private int diagPosNeighbors; // hold number of similar neighbors on positive diagonal
	private int diagNgNeighbors; // holds number of similar neighbors on negative diagonals
	public static boolean gameOver; // static boolean to indicate game is over
	private static int turnsPlayed; // static variable to keep track of number of turns played
	private int x,y; // position of cell in array
	
	public Cell(int x, int y) { // constructor sets all default values
		type = '-';
		yNeighbors = 1; // sets neighbor variables to as this cell counts as 1
		xNeighbors = 1;
		diagPosNeighbors = 1;
		diagNgNeighbors = 1;
		gameOver = false;
		turnsPlayed = 0;
		this.x = x;
		this.y = y;
	}
	private void checker() { // checker function to check if game is over
		// if similar neighbors >= 3 game is over
		if(xNeighbors>=3 || yNeighbors>=3 || diagPosNeighbors>=3 || diagNgNeighbors>=3) // if there are 3 neighbors in any diagonal game is over
			winner();
		if(turnsPlayed>=42 && !gameOver) { // if all cells have been taken and game hasn't ended its a draw
			draw();
		}
	}
	
	public int assign(char c, Cell[][] arr) { // assign function to assign either x or o to cell
		if(c!='X' && c!='O') // returns 0 if input isn't x or y
			return 0;
		this.type = c;
		turnsPlayed++;
		display(arr); // displays array after applying changes
		checkNeighbors(arr); // checks neighbors of changed cell
		return 1;
	}
	public char getType() { // getter for type
		return this.type;
	}
	private void checkNeighbors(Cell[][] arr) { // check neighbor function that checks all 8 neighbors of a cell
		// if a neighbor is similar, it adds both neighbors lengths and sets both to the same number
		if((this.x-1>=0 && this.y-1>=0) && arr[this.x-1][this.y-1].getType()==this.type) {
			this.diagNgNeighbors += arr[this.x-1][this.y-1].getDiagNgNeighbors();
			arr[this.x-1][this.y-1].setDiagNgNeighbors(this.diagNgNeighbors);
		}
			
		
		if((this.x+1<arr.length && this.y+1<arr[0].length) && arr[this.x+1][this.y+1].getType()==this.type) {
			this.diagNgNeighbors += arr[this.x+1][this.y+1].getDiagNgNeighbors();
			arr[this.x+1][this.y+1].setDiagNgNeighbors(this.diagNgNeighbors);
		}
			
		
		if((this.y-1>=0) && arr[this.x][this.y-1].getType()==this.type) {
			this.yNeighbors += arr[this.x][this.y-1].getyNeighbors();
			arr[this.x][this.y-1].setyNeighbors(this.yNeighbors);
		}
			
		
		if((this.y+1<arr[0].length) && arr[this.x][this.y+1].getType()==this.type) {
			this.yNeighbors += arr[this.x][this.y+1].getyNeighbors();
			arr[this.x][this.y+1].setyNeighbors(this.yNeighbors);
		}
			
		
		if((this.x-1>=0) && arr[this.x-1][this.y].getType()==this.type) {
			this.xNeighbors += arr[this.x-1][this.y].getxNeighbors();
			arr[this.x-1][this.y].setxNeighbors(this.xNeighbors);
		}
			
		
		if((this.x+1<arr.length) && arr[this.x+1][this.y].getType()==this.type) {
			this.xNeighbors += arr[this.x+1][this.y].getxNeighbors();
			arr[this.x+1][this.y].setxNeighbors(this.xNeighbors);
		}
			
		
		if((this.x-1>=0 && this.y+1<arr[0].length) && arr[this.x-1][this.y+1].getType()==this.type) {
			this.diagPosNeighbors += arr[this.x-1][this.y+1].getDiagPosNeighbors();
			arr[this.x-1][this.y+1].setDiagPosNeighbors(this.diagPosNeighbors);
		}
			
		
		if((this.x+1<arr.length && this.y-1>=0) && arr[this.x+1][this.y-1].getType()==this.type) {
			this.diagPosNeighbors += arr[this.x+1][this.y-1].getDiagPosNeighbors();
			arr[this.x+1][this.y-1].setDiagPosNeighbors(this.diagPosNeighbors);
		}
			
			checker(); // calls checker after the operation
	}
	
	
	 // getters and setters
	public void setyNeighbors(int yNeighbors) {
		this.yNeighbors = yNeighbors;
	}
	public void setxNeighbors(int xNeighbors) {
		this.xNeighbors = xNeighbors;
	}
	public void setDiagPosNeighbors(int diagPosNeighbors) {
		this.diagPosNeighbors = diagPosNeighbors;
	}
	public void setDiagNgNeighbors(int diagNgNeighbors) {
		this.diagNgNeighbors = diagNgNeighbors;
	}
	public int getyNeighbors() {
		return yNeighbors;
	}
	public int getxNeighbors() {
		return xNeighbors;
	}
	public int getDiagPosNeighbors() {
		return diagPosNeighbors;
	}
	public int getDiagNgNeighbors() {
		return diagNgNeighbors;
	}
	public static void display(Cell x[][]) // display function for entire array
	{
		System.out.println(" \t1\t2\t3\t4\t5\t6\t7");
		for (int i = 0; i < 6; i++) {
			System.out.print((i+1)+"\t");
			for (int j = 0; j < 7; j++) {
				System.out.print(x[i][j].getType()+"\t");
			}
			System.out.println();
		}
	}
	
	private void winner() { // function to declare winner
		System.out.println("Player "+this.type+" wins!");
		gameOver = true;
	}
	
	private void draw() { // function to declare draw
		System.out.println("It's a draw!");
		gameOver = true;
	}
	
}
