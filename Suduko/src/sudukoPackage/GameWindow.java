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

public class GameWindow {

	private JFrame frame;


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
		
		makeBoard();
		
		
	}
	
	private void makeBoard() {
		Border border=BorderFactory.createLineBorder(Color.black);
		Border panelBorder=BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black);
		for(int i=0;i<9;i++) {
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(3, 3, 0, 0));
			panel.setBorder(panelBorder);
			for(int j=0;j<9;j++) {
				JTextField txtField=new JTextField();
				txtField.setHorizontalAlignment(SwingConstants.CENTER);
				txtField.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
				txtField.setBorder(border);
			
				
				panel.add(txtField);
			}
			frame.getContentPane().add(panel);
		}
	}

}
