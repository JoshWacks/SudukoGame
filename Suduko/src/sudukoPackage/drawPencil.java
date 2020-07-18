package sudukoPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class drawPencil extends JLabel{
	final int X=5;
	final int Y=15;

	private String num;
	
	
	
	public drawPencil( String num) {
		super();

		this.num = num;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);  
        g2.setColor(Color.GRAY);
        
        

        g2.drawString(num, X, Y);

       
        
    } 
	
}
