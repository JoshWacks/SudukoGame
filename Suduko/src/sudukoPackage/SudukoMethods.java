package sudukoPackage;

import java.awt.*;

import java.util.*;
import javafx.util.Pair;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;


public class SudukoMethods {
	
	static private int[][] board=new int[9][9];
	
	static private JFrame gwFrame;
	static private GameWindow gw;
	
	static private boolean helpOn=true;
	static private boolean solveOn=false;
	
	static private VirtSudukoMethods vsm;

	public static void main(String[] args) {

		gw=new GameWindow();
		gwFrame=gw.frame;
		startGame();
//		//makePresetBoard();
		gwFrame.setVisible(true);
//		startGame();
//		//solve();
//		
		
		vsm=new VirtSudukoMethods();
		
		
	}
	
	private boolean checkBasicValid(int num) {
		if(num==-1) {
			return false;
		}
		else if(num==1||num==2||num==3||num==4||num==5||num==6||num==7||num==8||num==9) {
			return true;
		}
		
		return false;
	}
	
	private static boolean checkValid(int row,int col,int num) {
//		System.out.println("Row: "+row+" Col: "+col+" num: "+num);

		for(int i=0;i<9;i++) {//row check

			if(board[row][i]==num && i!=col) {//changes the column and makes sure not the same place as the entered number
				
				return false;
			}
		}
		
		for(int i=0;i<9;i++) {//col check

			if(board[i][col]==num && i!=row){//fixes the col now

				return false;
			}
		}
		
		//panel check
		if(row==0||row==1||row==2) {
			if(col==0||col==1||col==2) {
				for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						if(board[i][j]==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
			else if(col==3||col==4||col==5) {
				for(int i=0;i<3;i++) {
					for(int j=3;j<6;j++) {
						if(board[i][j]==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
				
			}
			
			else if(col==6||col==7||col==8) {
				for(int i=0;i<3;i++) {
					for(int j=6;j<8;j++) {
						if(board[i][j]==num && (row!=i || col!=j)) {
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
						if(board[i][j]==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
			else if(col==3||col==4||col==5) {
				for(int i=3;i<6;i++) {
					for(int j=3;j<6;j++) {
						if(board[i][j]==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
				
			}
			
			else if(col==6||col==7||col==8) {
				for(int i=3;i<6;i++) {
					for(int j=6;j<8;j++) {
						if(board[i][j]==num && (row!=i || col!=j)) {
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
						if(board[i][j]==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
			else if(col==3||col==4||col==5) {
				for(int i=6;i<8;i++) {
					for(int j=3;j<6;j++) {
						if(board[i][j]==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
				
			}
			
			else if(col==6||col==7||col==8) {
				for(int i=6;i<8;i++) {
					for(int j=6;j<8;j++) {
						if(board[i][j]==num && (row!=i || col!=j)) {
							return false;
						}
					}
				}
			}
			
		}
		return true;
	}
	
	private void flashField(JTextField txtField,String type) {
		long use=400;
		Color clr = null;
		if(type.equals("error")) {
			clr=Color.RED;
		}
		else if(type.equals("nine")) {
			clr=Color.CYAN;
		}
		final Color finalCLr=clr;
		
	    Thread th=new Thread(new Runnable()
		{
		    @Override
		    public void run()
		    {
					try {
						
							txtField.setBackground(finalCLr);
							Thread.sleep(use);
							txtField.setBackground(Color.WHITE);
							Thread.sleep(use);
							txtField.setBackground(finalCLr);
							Thread.sleep(use);
							txtField.setBackground(Color.WHITE);
							Thread.sleep(use);
							txtField.setBackground(finalCLr);
							Thread.sleep(use);
							txtField.setBackground(Color.WHITE);
							
						
						
						if(type.equals("end")) {
							long time=300;
							txtField.setEditable(false);
							txtField.setBackground(Color.blue);
							Thread.sleep(time);
							txtField.setBackground(Color.WHITE);
							Thread.sleep(time);
							txtField.setBackground(Color.PINK);
							Thread.sleep(time);
							txtField.setBackground(Color.WHITE);
							Thread.sleep(time);
							txtField.setBackground(Color.ORANGE);
							Thread.sleep(time);
							txtField.setBackground(Color.WHITE);
							Thread.sleep(time);
							txtField.setBackground(Color.green);
							Thread.sleep(time);
							txtField.setBackground(Color.WHITE);
							Thread.sleep(time);
							txtField.setBackground(Color.yellow);
							Thread.sleep(time);
							txtField.setBackground(Color.WHITE);
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    }
		    	
		    
		});
	    th.start();
	}
	
	private void flashSetNine(String type,int set) {
		Component[] container=gwFrame.getContentPane().getComponents();
		
		int panel;
		JPanel jp=new JPanel();
		JPanel jPanel = null;
		if(type.equals("panel")) {
			jPanel=(JPanel) container[set];
		}
		
		
		int txtField;
		JTextField jtxtField=new JTextField();
		
		for(int i=0;i<9;i++) {
			if(type.equals("row")) {
				panel=getPanel(set,i);
				jp=(JPanel) container[panel];
				txtField=getTxtField(set,i);
				jtxtField=(JTextField) jp.getComponent(txtField);
				flashField(jtxtField,"nine");
				
				
			}
			
			else if(type.equals("col")) {
				panel=getPanel(i,set);
				jp=(JPanel) container[panel];
				txtField=getTxtField(i,set);
				jtxtField=(JTextField) jp.getComponent(txtField);
				flashField(jtxtField,"nine");
				
				
			}
			else if(type.equals("panel")) {
				
				jtxtField=(JTextField) jPanel.getComponent(i);
				flashField(jtxtField,"nine");
			}
		}
		
		
	}
	
	public void checkSetNineCompleted(int row,int col) {
		Set rowSet=new HashSet();//a set to keep track of all the numbers in the row
		int rowVal;
		Set colSet=new HashSet();//a set to keep track of all the numbers in the col
		int colVal;
		
		Set panelSet=new HashSet();//a set to keep track of all the numbers in the panel
		int panelVal = 0;
		String temp;
		
		int panel=getPanel(row,col);
		
		Component[] container=gwFrame.getContentPane().getComponents();
		
		JPanel jp=(JPanel) container[panel];
		JTextField jtxtField=new JTextField();
		
		for(int i=0;i<9;i++) {
			rowVal=board[row][i];
			colVal=board[i][col];
			jtxtField=(JTextField) jp.getComponent(i);
			temp=jtxtField.getText();
			
			if(!temp.equals("")) {
				panelVal=Integer.parseInt(jtxtField.getText());
			}

			if(rowVal!=0) {
				rowSet.add(rowVal);
			}
			if(colVal!=0) {
				colSet.add(colVal);
			}
			if(panelVal!=0) {
				panelSet.add(panelVal);
			}
				
			
		}

		if(rowSet.size()==9) {
			flashSetNine("row",row);
		}
		
		if(colSet.size()==9) {
			flashSetNine("col",col);
		}
		
		if(panelSet.size()==9) {
			flashSetNine("panel",panel);
		}
		
		
		
	}
	
	public void addToBoard(int row,int col, int val,JTextField txtField) {
		boolean numeric=checkBasicValid(val);
		boolean valid=checkValid(row,col,val);

		if(numeric) {
			if(valid&&helpOn) {
				board[row][col]=val;
				if(!solveOn) {
					checkSetNineCompleted(row,col);
				}

				if(checkBoardSolved()) {
					endGame();
				}

			}else if(!helpOn) {
				board[row][col]=val;
				if(!solveOn) {
					checkSetNineCompleted(row,col);
				}

				if(checkBoardSolved()) {
					endGame();
				}
			}
		}
		else if(!numeric) {
			flashField(txtField,"error");
			JOptionPane.showMessageDialog( GameWindow.frame, "Please enter a valid number","Invalid Number",JOptionPane.INFORMATION_MESSAGE);
			 
		    Thread thRemoveText=new Thread(new Runnable()
			{
			    @Override
			    public void run()
			    {
			    	txtField.setText("");
			    	board[row][col]=0;
			    }
			    	
			    
			});	    
	
		    SwingUtilities.invokeLater(thRemoveText); 
		}

		if(helpOn&&!valid){
				flashField(txtField,"error");
				JOptionPane.showMessageDialog( GameWindow.frame, "There is a clash with that number","New Number",JOptionPane.INFORMATION_MESSAGE);
				
				 
			    Thread thRemoveText=new Thread(new Runnable()
				{
				    @Override
				    public void run()
				    {
				    	txtField.setText("");
				    	board[row][col]=0;
				    }
				    	
				    
				});	    
		
			    SwingUtilities.invokeLater(thRemoveText);
				
			}
		   
			
		}
	
	
	
	private static void displayBoard() {
		System.out.println();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(board[i][j]+" ");
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
	
	
	private static void makePresetBoard() {
		Component[] container=gwFrame.getContentPane().getComponents();
		JPanel jp0=(JPanel) container[0];
		JTextField jtf03=(JTextField) jp0.getComponent(3);
		JTextField jtf04=(JTextField) jp0.getComponent(4);
		JTextField jtf07=(JTextField) jp0.getComponent(7);
		
		jtf03.setText("2");
		jtf03.setEditable(false);
		jtf04.setText("7");
		jtf04.setEditable(false);
		jtf07.setText("6");
		jtf07.setEditable(false);
		
		JPanel jp1=(JPanel) container[1];
		JTextField jtf11=(JTextField) jp1.getComponent(1);
		JTextField jtf14=(JTextField) jp1.getComponent(4);
		JTextField jtf16=(JTextField) jp1.getComponent(6);
		
		jtf11.setText("8");
		jtf11.setEditable(false);
		jtf14.setText("3");
		jtf14.setEditable(false);
		jtf16.setText("7");
		jtf16.setEditable(false);
		
		JPanel jp2=(JPanel) container[2];
		JTextField jtf20=(JTextField) jp2.getComponent(0);
		JTextField jtf23=(JTextField) jp2.getComponent(3);

		jtf20.setText("1");
		jtf20.setEditable(false);
		jtf23.setText("4");
		jtf23.setEditable(false);
		
		JPanel jp3=(JPanel) container[3];
		JTextField jtf37=(JTextField) jp3.getComponent(7);
		JTextField jtf38=(JTextField) jp3.getComponent(8);

		jtf37.setText("5");
		jtf37.setEditable(false);
		jtf38.setText("3");
		jtf38.setEditable(false);
		
		JPanel jp4=(JPanel) container[4];
		JTextField jtf44=(JTextField) jp4.getComponent(4);
		JTextField jtf46=(JTextField) jp4.getComponent(6);
		
		jtf44.setText("2");
		jtf44.setEditable(false);
		jtf46.setText("6");
		jtf46.setEditable(false);
		
		JPanel jp5=(JPanel) container[5];
		JTextField jtf53=(JTextField) jp5.getComponent(3);
		JTextField jtf55=(JTextField) jp5.getComponent(5);
		JTextField jtf58=(JTextField) jp5.getComponent(8);
		
		jtf53.setText("5");
		jtf53.setEditable(false);
		jtf55.setText("4");
		jtf55.setEditable(false);
		jtf58.setText("9");
		jtf58.setEditable(false);
		
		JPanel jp6=(JPanel) container[6];
		JTextField jtf62=(JTextField) jp6.getComponent(2);
		JTextField jtf65=(JTextField) jp6.getComponent(5);
		
		jtf62.setText("5");
		jtf62.setEditable(false);
		jtf65.setText("8");
		jtf65.setEditable(false);

		JPanel jp7=(JPanel) container[7];
		JTextField jtf70=(JTextField) jp7.getComponent(0);
		JTextField jtf72=(JTextField) jp7.getComponent(2);
		JTextField jtf77=(JTextField) jp7.getComponent(7);
		
		jtf70.setText("1");
		jtf70.setEditable(false);
		jtf72.setText("8");
		jtf72.setEditable(false);
		jtf77.setText("4");
		jtf77.setEditable(false);
		
		JPanel jp8=(JPanel) container[8];
		JTextField jtf84=(JTextField) jp8.getComponent(4);
		JTextField jtf88=(JTextField) jp8.getComponent(8);
		
		jtf84.setText("9");
		jtf84.setEditable(false);
		jtf88.setText("7");
		jtf88.setEditable(false);

	
	}
	
	public static boolean checkBoardSolved() {
		Set uniqueNum=new HashSet();//a set to keep track of all the numbers in each row
		int val;
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				
				val=board[i][j];

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
	
	public static int getPanel(int row, int col) {
		if(row==0||row==1||row==2) {
			if(col==0||col==1||col==2) {
				return 0;
				
			}
			else if(col==3||col==4||col==5) {
				return 1;
				
			}
			else if(col==6||col==7||col==8) {
				return 2;
				
			}
		}
		
		else if(row==3||row==4||row==5) {
			if(col==0||col==1||col==2) {
				return 3;
				
			}
			else if(col==3||col==4||col==5) {
				return 4;
				
			}
			else if(col==6||col==7||col==8) {
				return 5;
				
			}
		}
		else {
			if(col==0||col==1||col==2) {
				return 6;
				
			}
			else if(col==3||col==4||col==5) {
				return 7;
				
			}
			else if(col==6||col==7||col==8) {
				return 8;
				
			}
		}
		return -1;
	}
	
	public static int getTxtField(int row, int col) {
		if(row%3==0) {
			if(col%3==0) {
				return 0;
			}
			else if(col==1||col==4||col==7) {
				return 1;
			}
			else if(col==2||col==5||col==8) {
				return 2;
			}
				
			
		}
		else if(row==1||row==4||row==7) {
			if(col%3==0) {
				return 3;
			}
			else if(col==1||col==4||col==7) {
				return 4;
			}
			else if(col==2||col==5||col==8) {
				return 5;
			}
		}
		else {
			if(col%3==0) {
				return 6;
			}
			else if(col==1||col==4||col==7) {
				return 7;
			}
			else if(col==2||col==5||col==8) {
				return 8;
			}
		}
		return -1;
	}
	
	public static boolean solve() {
		
		solveOn=true;
		helpOn=false;
		int panel=0;
		int txtField=0;
		int val=1;
		
		int row=0;
		int col=0;
		
		Pair<Integer,Integer>p;
		
		Stack<JTextField>stack=new Stack<JTextField>();//helps to backtrack
		Stack<Pair<Integer,Integer>>boardStack=new Stack<Pair<Integer,Integer>>();
		
		JPanel jp=new JPanel();
		JTextField jtxtField=new JTextField();
		
		Component[] container=gwFrame.getContentPane().getComponents();
		
		
		while(!checkBoardSolved()) {
			jp=(JPanel) container[panel];
			jtxtField=(JTextField) jp.getComponent(txtField);
			row=gw.getRow(panel, txtField);
			col=gw.getCol(panel, txtField);
			
			if(jtxtField.isEditable()) {
				
				
				if(checkValid(row,col,val)) {
					jtxtField.setText(val+"");
					val=1;
					stack.add(jtxtField);
					p=new Pair(row,col);
					boardStack.add(p);
					
					if(txtField==8) {
						panel=panel+1;
						txtField=0;
					}else {
						txtField=txtField+1;
						
					}
					
				}
				else {
					if(val==9) {
						jtxtField.setText("");
						board[row][col]=0;
						if(stack.size()==0) {
							return false;
						}
						p=boardStack.remove(boardStack.size()-1);
						row=p.getKey();
						col=p.getValue();
						jtxtField=stack.remove(stack.size()-1);
						
						while(jtxtField.getText().equals("9")) {
							jtxtField.setText("");
							board[row][col]=0;
							if(stack.size()==0) {
								return false;
							}
							
							p=boardStack.remove(boardStack.size()-1);
							row=p.getKey();
							col=p.getValue();
							jtxtField=stack.remove(stack.size()-1);
							
						}
						
						panel=getPanel(row,col);
						txtField=getTxtField(row,col);
						val=Integer.parseInt(jtxtField.getText())+1;
					}
					else {
						val=val+1;
					}
				}
			}
			else {
				if(txtField==8) {
					panel=panel+1;
					txtField=0;
				}else {
					txtField=txtField+1;
					
				}
			}
			
		}
		return true;
	
	}	
	
	
	
	private static void startGame() {
		Cell[][] solvedBoard=vsm.createGame();
		Component[] container=gwFrame.getContentPane().getComponents();
		JPanel jp=new JPanel();
		JTextField jtxtField=new JTextField();
		Cell c;
		
		int row=0;
		int col=0;
		
		for(int i=0;i<9;i++) {
			jp=(JPanel) container[i];
			for(int j=0;j<9;j++) {
				row=gw.getRow(i, j);
				col=gw.getCol(i, j);
				c=solvedBoard[row][col];
				if(!c.isEditable()) {//We only want it if it is not editable
					jtxtField=(JTextField) jp.getComponent(j);
					jtxtField.setText(c.getVal()+"");
				}

				
			}
		}
	}
	
	private void endGameFlash() {
		Component[] container=gwFrame.getContentPane().getComponents();
		
		JPanel jp=new JPanel();
		JTextField jtxtField=new JTextField();
		
		for(int i=0;i<9;i++) {
			jp=(JPanel) container[i];
			for(int j=0;j<9;j++) {
				jtxtField=(JTextField) jp.getComponent(j);
				flashField(jtxtField,"end");
				
			}
		}
		
	}
	
	private void endGame() {
		endGameFlash();
	
		
	}
	 
		

}
