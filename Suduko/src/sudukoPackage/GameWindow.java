package sudukoPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GameWindow {

	public static JFrame frame;
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
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new GridLayout(3, 3, 0, 0));
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
				
				row=checkRow(i,j);
				col=checkCol(i,j);
				
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
			frame.getContentPane().add(panel);
		}
	}
	
	private int checkRow(int i,int j) {
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
	
	private int checkCol(int i,int j) {
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

		