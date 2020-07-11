package sudukoPackage;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.util.Pair;

public class VirtSudukoMethods {
	
	static private String difficulty="extreme";
	static private Cell[][] solvedBoard=new Cell[9][9];
	
	
	
	public static String getDifficulty() {
		return difficulty;
	}


	public static void setDifficulty(String difficulty) {
		VirtSudukoMethods.difficulty = difficulty;
	}


	private static void initializeGame() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				Cell c=new Cell(i,j,0,true);
				solvedBoard[i][j]=c;
			}
		}
	}
	

	private static void displaySolvedBoard() {
		System.out.println();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(solvedBoard[i][j].getVal()+" ");
				if((j+1)%3==0) {
					System.out.print("|");
				}
			}
			System.out.println();
			if((i+1)%3==0) {
				System.out.println("---------------------");
			}
		}
		
	}
	
	private static boolean checkBoardSolved() {
		Set uniqueNum=new HashSet();//a set to keep track of all the numbers in each row
		int val;
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				
				val=solvedBoard[i][j].getVal();

				if(val==0) {
					return false;
				}
				else {
					uniqueNum.add(val);
				}
			}

			if(uniqueNum.size()!=9) {
				return false;
			}else {
				uniqueNum.clear();
			}
			
		}
		return true;
	}
	
	private static boolean checkValid(int row,int col,int num) {
//		System.out.println("Row: "+row+" Col: "+col+" num: "+num);

		for(int i=0;i<9;i++) {//row check

			if(solvedBoard[row][i].getVal()==num && i!=col) {//changes the column and makes sure not the same place as the entered number
				
				return false;
			}
		}
		
		for(int i=0;i<9;i++) {//col check

			if(solvedBoard[i][col].getVal()==num && i!=row){//fixes the col now

				return false;
			}
		}
		
		//panel check
		if(row==0||row==1||row==2) {
			if(col==0||col==1||col==2) {
				for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
			else if(col==3||col==4||col==5) {
				for(int i=0;i<3;i++) {
					for(int j=3;j<6;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
				
			}
			
			else if(col==6||col==7||col==8) {
				for(int i=0;i<3;i++) {
					for(int j=6;j<8;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
		}
		
		else if(row==3||row==4||row==5) {
			if(col==0||col==1||col==2) {
				for(int i=3;i<6;i++) {
					for(int j=0;j<3;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
			else if(col==3||col==4||col==5) {
				for(int i=3;i<6;i++) {
					for(int j=3;j<6;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
				
			}
			
			else if(col==6||col==7||col==8) {
				for(int i=3;i<6;i++) {
					for(int j=6;j<8;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
		}
		
		else {
			if(col==0||col==1||col==2) {
				for(int i=6;i<8;i++) {
					for(int j=0;j<3;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
			else if(col==3||col==4||col==5) {
				for(int i=6;i<8;i++) {
					for(int j=3;j<6;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
				
			}
			
			else if(col==6||col==7||col==8) {
				for(int i=6;i<8;i++) {
					for(int j=6;j<8;j++) {
						if(solvedBoard[i][j].getVal()==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
		}
		return true;
	}
	

	
	private static boolean checkSolveable() {

		int row=0;
		int col=0;
		int val=1;
		Stack<Cell>stack=new Stack<Cell>();//helps to backtrack
		Cell c=new Cell(row,col,val,true);
		
		final boolean[] timer= {false};
		
		long start = System.currentTimeMillis();
		long end = 3*1000; // 6 seconds * 1000 ms/sec
		
		while(!checkBoardSolved()) {//while the board is not solved
		//	System.out.println(System.currentTimeMillis()-start);
			//displaySolvedBoard();
			if(System.currentTimeMillis()-start > end) {
		//		System.out.println("False");
				return false;
			}
			
			c=solvedBoard[row][col]; //Gets the cell at a specific row and column
			
			if(c.isEditable()) {//only do this if it is not a constraint
				c.setVal(val);
				
				if(checkValid(c.getRow(),c.getCol(),c.getVal())) {//if it is a valid number
					val=1;//reset the value number
					stack.add(c);// add that cell which is all good to the stack
					if(col==8) {//we need to check for a new row before going to the next cell
						row=row+1;
						col=0;
					}//move to the next cell
					else {
						col=col+1;
					}
				}
				else {//uh-uh that is not a valid number
					if(System.currentTimeMillis()-start > end) {
						return false;
					}
					if(val==9) {//if we have tried to set it to all possible numbers,therefore must go backwards
						c.setVal(0);//set that block to -1, to prevent incorrect clashes
						if(stack.size()==0) {
							return false;
						}
						c=stack.remove(stack.size()-1);//c gets the top value of the stack, the last okay cell
						while(c.getVal()==9) {//we need to keep going back and getting cells when they are at 9
							c.setVal(0);
							if(stack.size()==0) {
								return false;
							}
							c=stack.remove(stack.size()-1);
						}

						row=c.getRow();
						col=c.getCol();//this ensures we go back to that position,then forward once again,trying new problems

						val=c.getVal()+1;//we need it to try the next higher number , as that previous number led to a clash
					}
					else {
						val=val+1;//we need to try the next number
					}
				}
				
			}
			else {
				if(col==8) {//same as if the cell was valid
					row=row+1;
					col=0;
				}
				else {
					col=col+1;
				}
			}
			
		}
		return true;
		
	
	}
	
	private static void makeRandomGame() {
		
		solvedBoard[0][0]=new Cell(0,0,ThreadLocalRandom.current().nextInt(2,9),false);
		
		int randomRow;
		int randomCol;
		int randomVal;
		int numRows = 5;
		int numCols = 3;
	
		
		for(int i=0;i<numRows;i++) {
			randomRow = ThreadLocalRandom.current().nextInt(0,8);
			for(int j=0;j<numCols;j++) {
				
				randomCol = ThreadLocalRandom.current().nextInt(0,8);
				randomVal = ThreadLocalRandom.current().nextInt(1,9);
				
				while(!checkValid(randomRow,randomCol,randomVal)) {
					
					randomVal = ThreadLocalRandom.current().nextInt(1,9);

				}
				Cell c=new Cell(randomRow,randomCol,randomVal,false);

				solvedBoard[randomRow][randomCol]=c;
			}
		}
		
		
	}
	
	public static Cell[][] createGame() {
		initializeGame();
		makeRandomGame();
		
		while(!checkSolveable()) {
			makeRandomGame();
		}
		//displaySolvedBoard();
		return solvedBoard;
		
	}
	
	public void clearBoard() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<0;j++) {
				Cell c=new Cell(i,j,0,true);
				solvedBoard[i][j]=c;

			}
		}
	}
	
}
