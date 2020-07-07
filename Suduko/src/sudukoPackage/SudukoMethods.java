package sudukoPackage;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudukoMethods {
	
	static private int[][] board=new int[9][9];
	static private boolean helpOn=true;
	
	
	private boolean checkBasicValid(int num) {
		if(num==-1) {
			return false;
		}
		else if(num==1||num==2||num==3||num==4||num==5||num==6||num==7||num==8||num==9) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkValid(int row,int col,int num) {
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
	
	private void flashField(JTextField txtField) {
		long use=400;
		
	    Thread th=new Thread(new Runnable()
		{
		    @Override
		    public void run()
		    {
					try {
						
							txtField.setBackground(Color.RED);
							Thread.sleep(use);
							txtField.setBackground(Color.WHITE);
							Thread.sleep(use);
							txtField.setBackground(Color.RED);
							Thread.sleep(use);
							txtField.setBackground(Color.WHITE);

							

						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    }
		    	
		    
		});
	    th.start();
	}
	
	public void addToBoard(int row,int col, int val,JTextField txtField) {
		boolean numeric=checkBasicValid(val);
		boolean valid=checkValid(row,col,val);

		if(numeric) {
			if(valid&&helpOn) {
				board[row][col]=val;
				displayBoard();
			}else if(!helpOn) {
				board[row][col]=val;
				displayBoard();
			}
		}
		else if(!numeric) {
			flashField(txtField);
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
				flashField(txtField);
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
	 
		public static void main(String[] args) {

			displayBoard();
			GameWindow gw=new GameWindow();
			gw.frame.setVisible(true);
		}

}
