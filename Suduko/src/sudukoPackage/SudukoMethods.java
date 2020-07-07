package sudukoPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudukoMethods {
	
	static private int[][] board=new int[9][9];
	static private boolean helpOn=true;
	
	static private JFrame gwFrame;

	public static void main(String[] args) {

		displayBoard();
		GameWindow gw=new GameWindow();
		gwFrame=gw.frame;
		makePresetBoard();
		gwFrame.setVisible(true);
		
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
	 
		

}
