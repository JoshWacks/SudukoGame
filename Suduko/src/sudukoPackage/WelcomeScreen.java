package sudukoPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.AbstractBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;

public class WelcomeScreen {

	public static JFrame frmSudoko;
	private JLabel lblDifficulty;
	private JButton btnMedium;
	private JButton btnExtreme;
	private JComboBox comboBoxNumMistakes;

	private static VirtSudukoMethods vsm;
	private static SudukoMethods sm;
	
	
	private JCheckBox chckTips;
	private JCheckBox chckMistakes;

	public WelcomeScreen() {//constructor
		
		sm=new SudukoMethods();
		vsm=new VirtSudukoMethods();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmSudoko = new JFrame();
		frmSudoko.setSize(new Dimension(675, 475));

		frmSudoko.setTitle("Sudoko");
		
		frmSudoko.setBounds(100, 100, 800, 196);
		frmSudoko.setSize(new Dimension(675, 475));
		frmSudoko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudoko.setResizable(false);
		frmSudoko.getContentPane().setLayout(null);
		frmSudoko.getContentPane().setBackground(new Color(0, 0, 0));
		
		JLabel lblWelcome = new JLabel("WELCOME TO");
		lblWelcome.setFont(new Font("Ravie", Font.BOLD, 33));
		lblWelcome.setForeground(new Color(0, 102, 204));
		lblWelcome.setBounds(161, 11, 324, 62);
		frmSudoko.getContentPane().add(lblWelcome);
		
		JLabel lblSereneSudoko = new JLabel("SERENE SUDOKO");
		lblSereneSudoko.setForeground(new Color(102, 0, 102));
		lblSereneSudoko.setFont(new Font("Ravie", Font.BOLD, 36));
		lblSereneSudoko.setBounds(100, 70, 456, 62);
		frmSudoko.getContentPane().add(lblSereneSudoko);
		
		lblDifficulty = new JLabel("Select a difficulty");
		lblDifficulty.setForeground(new Color(153, 204, 153));
		lblDifficulty.setFont(new Font("Sitka Display", Font.BOLD, 21));
		lblDifficulty.setBounds(475, 167, 162, 21);
		frmSudoko.getContentPane().add(lblDifficulty);
		
		String[] nums= {"1","2","3","4","5"};
		
		comboBoxNumMistakes = new JComboBox(nums);
		comboBoxNumMistakes.setToolTipText("Customize how many mistakes you are allowed before the game is over");
		comboBoxNumMistakes.setFont(new Font("Sitka Display", Font.BOLD, 18));
		comboBoxNumMistakes.setBounds(221, 334, 45, 25);
		frmSudoko.getContentPane().add(comboBoxNumMistakes);

		JLabel lblMax = new JLabel("Number of Mistakes");
		lblMax.setToolTipText("Set the maximum number of mistakes you can make");
		lblMax.setForeground(new Color(0, 102, 153));
		lblMax.setFont(new Font("Sitka Display", Font.BOLD, 23));
		lblMax.setBounds(10, 334, 201, 21);
		frmSudoko.getContentPane().add(lblMax);
		
		
		makeButtons();
		makeColorButtons();
	}
	
	private void makeButtons() {
		JButton btnEasy = new JButton("Easy");
		btnEasy.setForeground(new Color(0, 0, 0));
		btnEasy.setBackground(new Color(102, 204, 0));
		btnEasy.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnEasy.setBounds(508, 212, 100, 30);
		
		btnEasy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				btnSelected("easy");
				
			}
		});

		AbstractBorder borderE = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnEasy.setBorder(borderE);
		
		frmSudoko.getContentPane().add(btnEasy);
		
		btnMedium = new JButton("Medium");
		btnMedium.setForeground(new Color(0, 0, 0));
		btnMedium.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnMedium.setBackground(new Color(204, 102, 0));
		btnMedium.setBounds(508, 262, 100, 30);
		borderE = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnMedium.setBorder(borderE);
		
		btnMedium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				btnSelected("medium");
				
			}
		});
		frmSudoko.getContentPane().add(btnMedium);
		
		
		
		JButton btnHard = new JButton("Hard");
		btnHard.setForeground(new Color(0, 0, 0));
		btnHard.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnHard.setBackground(new Color(102, 204, 153));
		btnHard.setBounds(508, 312, 100, 30);
		borderE = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnHard.setBorder(borderE);
		
		
		btnHard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				btnSelected("hard");
				
			}
		});
		frmSudoko.getContentPane().add(btnHard);
		
		
		btnExtreme = new JButton("Extreme");
		btnExtreme.setForeground(new Color(0, 0, 0));
		btnExtreme.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnExtreme.setBackground(new Color(255, 0, 0));
		btnExtreme.setBounds(508, 362, 100, 30);
		borderE = new TextBubbleBorder(Color.BLACK,3,16,1);
		btnExtreme.setBorder(borderE);
		
		btnExtreme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				btnSelected("extreme");
				
			}
		});
		frmSudoko.getContentPane().add(btnExtreme);
		
		JLabel lblCustomize = new JLabel("Customize Your Game");
		lblCustomize.setForeground(new Color(153, 204, 153));
		lblCustomize.setFont(new Font("Sitka Display", Font.BOLD, 21));
		lblCustomize.setBounds(35, 167, 209, 21);
		frmSudoko.getContentPane().add(lblCustomize);
		
		JLabel lblTips = new JLabel("Tips On");
		lblTips.setToolTipText("Check here if you want to be told you have placed a number in the wrong place");
		lblTips.setForeground(new Color(0, 102, 153));
		lblTips.setFont(new Font("Sitka Display", Font.BOLD, 23));
		lblTips.setBounds(10, 212, 110, 30);
		frmSudoko.getContentPane().add(lblTips);
		
		chckTips = new JCheckBox("");
		chckTips.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckTips.setBackground(new Color(0, 0, 0));
		chckTips.setBounds(220, 208, 21, 23);
		frmSudoko.getContentPane().add(chckTips);
		
		JLabel lblMistakes = new JLabel("Mistakes On");
		lblMistakes.setToolTipText("Check here to set a limit to your number of mistakes");
		lblMistakes.setForeground(new Color(0, 102, 153));
		lblMistakes.setFont(new Font("Sitka Display", Font.BOLD, 23));
		lblMistakes.setBounds(10, 272, 141, 21);
		frmSudoko.getContentPane().add(lblMistakes);
		
		chckMistakes = new JCheckBox("");
		chckMistakes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckMistakes.setBackground(Color.BLACK);
		chckMistakes.setBounds(220, 267, 21, 23);
		frmSudoko.getContentPane().add(chckMistakes);
		
		JLabel lblColor = new JLabel("Select a colour");
		lblColor.setForeground(new Color(153, 204, 153));
		lblColor.setFont(new Font("Sitka Display", Font.BOLD, 21));
		lblColor.setBounds(296, 167, 141, 21);
		frmSudoko.getContentPane().add(lblColor);
		
		
		
		
	}
	
	private void makeColorButtons() {
		AbstractBorder basicBorder = new TextBubbleBorder(Color.WHITE,2,16,1);
		ArrayList<JButton> buttonArr=new ArrayList<JButton>();
		
		JButton btnBlue = new JButton("Blue");
		btnBlue.setForeground(new Color(255, 255, 255));
		btnBlue.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnBlue.setBackground(new Color(0, 0, 0));
		btnBlue.setBounds(310, 210, 100, 30);
		btnBlue.setBorder(basicBorder);
		btnBlue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				resetBorders(buttonArr);
				sm.setClr(Color.CYAN);
				AbstractBorder border = new TextBubbleBorder(Color.BLUE,4,16,1);
				btnBlue.setBorder(border);
			}
		});
		
		frmSudoko.getContentPane().add(btnBlue);
		
		JButton btnMagenta = new JButton("Magenta");
		btnMagenta.setForeground(Color.WHITE);
		btnMagenta.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnMagenta.setBackground(Color.BLACK);
		btnMagenta.setBounds(310, 260, 100, 30);
		btnMagenta.setBorder(basicBorder);
		btnMagenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				resetBorders(buttonArr);
				sm.setClr(Color.MAGENTA);
				AbstractBorder border = new TextBubbleBorder(Color.BLUE,4,16,1);
				btnMagenta.setBorder(border);
			}
		});
		frmSudoko.getContentPane().add(btnMagenta);
		
		JButton btnGreen = new JButton("Green");
		btnGreen.setForeground(Color.WHITE);
		btnGreen.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnGreen.setBackground(Color.BLACK);
		btnGreen.setBounds(310, 310, 100, 30);
		btnGreen.setBorder(basicBorder);
		btnGreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				resetBorders(buttonArr);
				sm.setClr(Color.GREEN);
				AbstractBorder border = new TextBubbleBorder(Color.BLUE,4,16,1);
				btnGreen.setBorder(border);
			}
		});
		frmSudoko.getContentPane().add(btnGreen);
		
		JButton btnOrange = new JButton("Orange");
		btnOrange.setForeground(Color.WHITE);
		btnOrange.setFont(new Font("Sitka Display", Font.BOLD, 16));
		btnOrange.setBackground(Color.BLACK);
		btnOrange.setBounds(310, 360, 100, 30);
		btnOrange.setBorder(basicBorder);
		btnOrange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				resetBorders(buttonArr);
				sm.setClr(Color.ORANGE);
				AbstractBorder border = new TextBubbleBorder(Color.BLUE,4,16,1);
				btnOrange.setBorder(border);
			}
		});
		frmSudoko.getContentPane().add(btnOrange);
		
		buttonArr.add(btnOrange);
		buttonArr.add(btnBlue);
		buttonArr.add(btnMagenta);
		buttonArr.add(btnGreen);
		
		
		
	}
	
	private void resetBorders(ArrayList<JButton>arr) {
		AbstractBorder basicBorder = new TextBubbleBorder(Color.WHITE,2,16,1);
		for(JButton jb:arr) {
			jb.setBorder(basicBorder);
		}
	}
	
	private void btnSelected(String diff) {
		if(sm.getClr()==null) {
			JOptionPane.showMessageDialog(frmSudoko, "Please select a colour first" );
		}else {
			boolean tips=chckTips.isSelected();
			sm.setHelp(tips);
			
			
			boolean mistakes=chckMistakes.isSelected();
			sm.setMistakes(mistakes);
			
			int numMistakes=Integer.parseInt((String) comboBoxNumMistakes.getSelectedItem());
			sm.setNumMaxMistakes(numMistakes);
			
			vsm.setDifficulty(diff);
			frmSudoko.setVisible(false);
			sm.run();
		}
		
	}
}
