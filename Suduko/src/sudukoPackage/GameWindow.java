package sudukoPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.sun.glass.events.WindowEvent;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class GameWindow {

	public static JFrame frame;
	private static SudukoMethods sm;
	private static VirtSudukoMethods vsm;
	
	private static boolean mistakesOn=true;
	
	public static JPanel mainPanel;
	DrawMistakes dm;
	
	private JButton btnNewGame;
	private JButton btnExit;

	private JToggleButton tglbtnPencil;
	private boolean pencilOn=false;
	
	public GameWindow() {
		
		sm=new SudukoMethods();
		
		vsm=new VirtSudukoMethods();
		frame = new JFrame();
		mistakesOn=sm.getMistakes();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 800, 500);
		Dimension dim=new Dimension(800,500);
		frame.setPreferredSize(dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(600, 471));
		mainPanel.setBounds(0, 0, 600, 471);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		
		JLabel lblHeading = new JLabel("SERENE");
		lblHeading.setFont(new Font("Ravie", Font.BOLD, 31));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setForeground(new Color(51, 102, 255));
		lblHeading.setBounds(605, 0, 184,50);
		frame.getContentPane().add(lblHeading);
		
		JLabel lblHeading2 = new JLabel("SUDOKO");
		lblHeading2.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading2.setForeground(new Color(51, 102, 255));
		lblHeading2.setFont(new Font("Ravie", Font.BOLD, 27));
		lblHeading2.setBounds(620, 48, 154, 50);
		frame.getContentPane().add(lblHeading2);
		
		JLabel lblDifficulty = new JLabel("");
		lblDifficulty.setText(vsm.getDifficulty().toUpperCase());
		lblDifficulty.setForeground(new Color(153, 0, 51));
		lblDifficulty.setFont(new Font("Ravie", Font.BOLD, 21));
		lblDifficulty.setBounds(630, 106, 150,32);
		frame.getContentPane().add(lblDifficulty);
		
		tglbtnPencil = new JToggleButton("PENCIL ON");
		tglbtnPencil.setBackground(new Color(0, 102, 204));
		tglbtnPencil.setFont(new Font("Sitka Displa8", Font.BOLD, 26));
		tglbtnPencil.setBounds(605, 267, 179, 32);
		frame.getContentPane().add(tglbtnPencil);
		
		
		if(mistakesOn) {
			JLabel lblMistakes = new JLabel("Mistakes");
			lblMistakes.setForeground(new Color(204, 0, 0));
			lblMistakes.setFont(new Font("Sitka Displa8", Font.BOLD, 26));
			lblMistakes.setBounds(640, 150, 110, 20);
			frame.getContentPane().add(lblMistakes);
			dm=new DrawMistakes();
			
			dm.setBackground(Color.BLACK);
			dm.setLocation(600, 185);
			dm.setSize(190, 40);
			frame.getContentPane().add(dm);
			frame.pack();
		}
		
		
		
		
		
				
		makeButtons();
	
		makeBoard();
		
		
		
	}
	
	public void gameOver() {
		JPanel pnlGameOver = new JPanel();
		pnlGameOver.setBackground(Color.BLACK);
		pnlGameOver.setBounds(0, 100, 600, 300);
		frame.getContentPane().add(pnlGameOver);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setBackground(new Color(0, 0, 0));
		lblGameOver.setForeground(new Color(255, 0, 0));
		lblGameOver.setBounds(230, 10, 200, 50);
		lblGameOver.setFont(new Font("Ravie", Font.BOLD, 28));
		pnlGameOver.add(lblGameOver);
		
		JLabel labelMaxMistakes = new JLabel("You reached the maximum number of mistakes");
		labelMaxMistakes.setForeground(Color.CYAN);
		labelMaxMistakes.setFont(new Font("Ravie", Font.BOLD, 16));
		labelMaxMistakes.setBounds(150,130,200,50);
		pnlGameOver.add(labelMaxMistakes);
		
		btnExit.setBounds(100,250,100,30);
		btnNewGame.setBounds(200,250,100,30);
		pnlGameOver.add(btnExit);
		pnlGameOver.add(btnNewGame);
		
		
		
		mainPanel.setVisible(false);
	}
	
	
	public void setMistakeNum(int i) {


		dm.setPoints(i);
		
		dm.repaint();
		
	}
	
	private void makeButtons() {
		JButton btnSolve = new JButton("SOLVE");
		btnSolve.setToolTipText("Press here to solve the current sudoko board.\r\n");
		btnSolve.setFont(new Font("Sitka Display", Font.BOLD, 21));
		btnSolve.setVerticalTextPosition(AbstractButton.CENTER);
		btnSolve.setHorizontalTextPosition(AbstractButton.CENTER); 
		btnSolve.setBackground(new Color(255, 102, 0));
		btnSolve.setBounds(630, 310, 140, 40);
		btnSolve.setForeground(Color.BLACK);
		
		AbstractBorder border = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnSolve.setBorder(border);
		
		btnSolve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				mistakesOn=false;
				tglbtnPencil.setSelected(false);
				pencilOn=false;
				sm.setMistakes(false);
				sm.showSolution();
				
			}
		});
		
		frame.getContentPane().add(btnSolve);
		
	
		btnNewGame = new JButton("NEW GAME");
		btnNewGame.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGame.setToolTipText("Press here to start a new game");
		btnNewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGame.setForeground(Color.BLACK);
		btnNewGame.setFont(new Font("Sitka Display", Font.BOLD, 21));
		btnNewGame.setBackground(new Color(0, 204, 51));
		btnNewGame.setBounds(630, 360, 140, 40);

		AbstractBorder borderN = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnNewGame.setBorder(borderN);
		btnNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				sm.clearBoards();
				vsm.clearBoard();
				sm.setClr(null);
				
				JPanel jp;
				JTextField txtField;
				for(int i=0;i<9;i++) {
					jp=(JPanel) mainPanel.getComponent(i);
					for(int j=0;j<9;j++) {
						txtField=(JTextField) jp.getComponent(j);
						txtField.setText("");
						txtField.setEditable(true);
					}
				}
				frame.setVisible(false);
				WelcomeScreen ws=new WelcomeScreen();
				ws.frmSudoko.setVisible(true);
				
			}
				
			
		});
		
		
		frame.getContentPane().add(btnNewGame);
		
		btnExit = new JButton("EXIT");
		btnExit.setToolTipText("Press here to exit the game.\r\n");
		btnExit.setFont(new Font("Sitka Display", Font.BOLD, 21));
		btnExit.setVerticalTextPosition(AbstractButton.CENTER);
		btnExit.setHorizontalTextPosition(AbstractButton.CENTER); 
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(630, 410, 140, 40);
		btnExit.setForeground(Color.BLACK);

		AbstractBorder borderE = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnExit.setBorder(borderE);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				frame.setVisible(false);
				Platform.exit();
				System.exit(0);
			
				
			}
				
			
		});
		
		
		frame.getContentPane().add(btnExit);
		
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
				txtField.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
				txtField.setBorder(border);
				txtField.setForeground(Color.BLACK);
				
				row=getRow(i,j);
				col=getCol(i,j);
				
				final int r=row;
				final int c=col;
				
				addToTextField(r,c,txtField);

				panel.add(txtField);
			}
			mainPanel.add(panel);
		}
	}
	
	private void addToTextField(int r, int c,JTextField txtField) {
		txtField.addKeyListener(new KeyAdapter() {
			
			int num=0;

			@Override
			public void keyReleased(KeyEvent e) {
				String str=txtField.getText();
				
				if(tglbtnPencil.isSelected()) {
					 Thread thRemoveText=new Thread(new Runnable()
						{
						    @Override
						    public void run()
						    {
						    	txtField.setText("");
						    }
						    	
						    
						});	 
					try {
						if(!sm.checkBasicValid(Integer.parseInt(str))&&!str.equals(" ")) {
							JOptionPane.showMessageDialog(GameWindow.frame, "Your pencil inputs must still be a valid number");
							thRemoveText.start();
							return;
						}
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(GameWindow.frame, "Your pencil inputs must still be a valid number");
						thRemoveText.start();
						return;
					}
					
					if(txtField.getComponentCount()>0&&!str.equals("")) {
						drawPencil dp=(drawPencil) txtField.getComponent(0);
						dp.setNum(str);
						txtField.repaint();
					}else {
						
						drawPencil dp1=new drawPencil(str);
						dp1.setBackground(Color.WHITE);
						dp1.setLocation(0, 0);
						dp1.setSize(100, 100);
						txtField.add(dp1);
						txtField.repaint();
					}
					thRemoveText.start();
					
				}
				else {

				
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
			}
		});
		
			
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

		