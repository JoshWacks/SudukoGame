package sudukoPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javafx.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GameWindow {

	public static JFrame frame;
	public static JPanel mainPanel;
	private static SudukoMethods sm;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 600, 471);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		JLabel lblHeading = new JLabel("SUDOKO");
		lblHeading.setFont(new Font("Ravie", Font.BOLD, 28));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setForeground(new Color(51, 102, 255));
		lblHeading.setBounds(610, 20, 174, 39);
		frame.getContentPane().add(lblHeading);
		
		
		JButton btnSolve = new JButton("SOLVE");
		btnSolve.setToolTipText("Press here to solve the current sudoko board.\r\n");
		btnSolve.setFont(new Font("Sitka Display", Font.BOLD, 21));
		btnSolve.setVerticalTextPosition(AbstractButton.CENTER);
		btnSolve.setHorizontalTextPosition(AbstractButton.CENTER); 
		btnSolve.setBackground(new Color(255, 102, 0));
		btnSolve.setBounds(630, 290, 140, 40);
		btnSolve.setForeground(Color.BLACK);
		
		AbstractBorder border = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnSolve.setBorder(border);
		
		btnSolve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				sm.showSolution();
				
			}
		});
		
		frame.getContentPane().add(btnSolve);
		
	
		JButton btnNewGame = new JButton("NEW GAME");
		btnNewGame.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGame.setToolTipText("Press here to start a new game");
		btnNewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGame.setForeground(Color.BLACK);
		btnNewGame.setFont(new Font("Sitka Display", Font.BOLD, 21));
		btnNewGame.setBackground(new Color(0, 204, 51));
		btnNewGame.setBounds(630, 340, 140, 40);

		AbstractBorder borderN = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnNewGame.setBorder(borderN);
		
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setToolTipText("Press here to exit the game.\r\n");
		btnExit.setFont(new Font("Sitka Display", Font.BOLD, 21));
		btnExit.setVerticalTextPosition(AbstractButton.CENTER);
		btnExit.setHorizontalTextPosition(AbstractButton.CENTER); 
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(630, 390, 140, 40);
		btnExit.setForeground(Color.BLACK);

		AbstractBorder borderE = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnExit.setBorder(borderE);
		
		frame.getContentPane().add(btnExit);
		

		


		
		
		
		
		sm=new SudukoMethods();
				
		makeBoard();
		
		
	}
	
	private void makeBoard() {
		Border border=BorderFactory.createLineBorder(Color.black);
		Border panelBorder=BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black);
		int row=0;
		int col=0;
		
		for(int i=0;i<9;i++) {
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(3, 3, 0, 0));
			panel.setBorder(panelBorder);
			for(int j=0;j<9;j++) {
				JTextField txtField=new JTextField();
				txtField.setHorizontalAlignment(SwingConstants.CENTER);
				txtField.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
				txtField.setBorder(border);
				
				row=getRow(i,j);
				col=getCol(i,j);
				
				final int r=row;
				final int c=col;
				
				txtField.getDocument().addDocumentListener(new DocumentListener() {
					String str;
					int num=0;
					
					
					
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						str=txtField.getText();
						if(str.equals("")) {
							num=0;
						}
						
						else {
							try {
								num=Integer.parseInt(str);
								
							}
							catch(NumberFormatException nfe) {
								num=-1;
							}
							sm.addToBoard(r, c, num,txtField);
							
						}
					//	System.out.print("row: "+r+" col: "+c+" num: "+num );
						
						
					}
					@Override
					public void removeUpdate(DocumentEvent e) {
						str=txtField.getText();
						if(str.equals("")) {
							num=0;
						}
						
						else {
							try {
								num=Integer.parseInt(str);
								
							}
							catch(NumberFormatException nfe) {
								num=-1;
							}
							sm.addToBoard(r, c, num,txtField);
							
						}
						
					}
					@Override
					public void changedUpdate(DocumentEvent e) {
						str=txtField.getText();
						if(str.equals("")) {
							num=0;
						}
						
						else {
							try {
								num=Integer.parseInt(str);
								
							}
							catch(NumberFormatException nfe) {
								num=-1;
							}
							sm.addToBoard(r, c, num,txtField);
							
						}
						
					}
				});
				
				panel.add(txtField);
			}
			mainPanel.add(panel);
		}
	}
	
	public int getRow(int i,int j) {
		if(i==0||i==1||i==2) {
			if(j==0||j==1||j==2) {
				return 0;
				
			}
			else if(j==3||j==4||j==5) {
				return 1;
				
			}
			else if(j==6||j==7||j==8) {
				return 2;
				
			}
			
		}
		else if(i==3||i==4||i==5) {
			
			if(j==0||j==1||j==2) {
				return 3;
				
			}
			else if(j==3||j==4||j==5) {
				return 4;
				
			}
			else if(j==6||j==7||j==8) {
				return 5;
				
			}
			
		}
		else {
			if(j==0||j==1||j==2) {
				return 6;
				
			}
			else if(j==3||j==4||j==5) {
				return 7;
				
			}
			else if(j==6||j==7||j==8) {
			
			
				return 8;
			}
		}
		return 0;
	}
	
	public int getCol(int i,int j) {
		if(i==0||i==3||i==6) {
			if(j==0||j==3||j==6) {
				return 0;
			}
			else if(j==1||j==4||j==7) {
				return 1;
			}
			else if(j==2|j==5||j==8) {
				return 2;
			}
			
		}
		else if(i==1||i==4||i==7) {
			if(j==0||j==3||j==6) {
				return 3;
			}
			else if(j==1||j==4||j==7) {
				return 4;
			}
			else if(j==2|j==5||j==8) {
				return 5;
			}
			
		}
		else if(i==2||i==5||i==8) {
			if(j==0||j==3||j==6) {
				return 6;
			}
			else if(j==1||j==4||j==7) {
				return 7;
			}
			else if(j==2|j==5||j==8) {
				return 8;
			}
			
		}
		return 0;
	}
}

		