package sudukoPackage;

import java.awt.*;

import javax.swing.*;


public class DrawMistakes extends JPanel {
	 private int[]points={5,23,45,65,85,105,125,145,165,185};
	 private int p;

	
	 public DrawMistakes() {
	      
	    }
	 
	 public void setPoints(int p) {
		 this.p=p;

	 }


	    public void paintComponent(Graphics g) {
	    	Graphics2D g2 = (Graphics2D)g;
	        super.paintComponent(g2);  
	        g2.setColor(Color.RED);

	        g2.setStroke(new BasicStroke(3));
	        
	        switch(p) {
	        case 1:
	        	g2.drawLine(points[0], points[0], points[1],points[1]);
	 	        g2.drawLine(points[1], points[0], points[0], points[1]);
	 	        break;
	        case 2:
	        	g2.drawLine(points[0], points[0], points[1],points[1]);
	 	        g2.drawLine(points[1], points[0], points[0], points[1]);
	 	        g2.drawLine(points[2], points[0], points[3],points[1]);
	 	        g2.drawLine(points[3], points[0], points[2], points[1]);
	 	        break;
	        case 3:
	        	g2.drawLine(points[0], points[0], points[1],points[1]);
	 	        g2.drawLine(points[1], points[0], points[0], points[1]);
	 	        g2.drawLine(points[2], points[0], points[3],points[1]);
	 	        g2.drawLine(points[3], points[0], points[2], points[1]);
	 	        g2.drawLine(points[4], points[0], points[5],points[1]);
	 	        g2.drawLine(points[5], points[0], points[4], points[1]);
	 	        break;
	        case 4:
	        	g2.drawLine(points[0], points[0], points[1],points[1]);
	 	        g2.drawLine(points[1], points[0], points[0], points[1]);
	 	        g2.drawLine(points[2], points[0], points[3],points[1]);
	 	        g2.drawLine(points[3], points[0], points[2], points[1]);
	 	        g2.drawLine(points[4], points[0], points[5],points[1]);
	 	        g2.drawLine(points[5], points[0], points[4], points[1]);
	 	        g2.drawLine(points[6], points[0], points[7],points[1]);
	 	        g2.drawLine(points[7], points[0], points[6], points[1]);
	 	        break;
	        case 5:
	        	g2.drawLine(points[0], points[0], points[1],points[1]);
	 	        g2.drawLine(points[1], points[0], points[0], points[1]);
	 	        g2.drawLine(points[2], points[0], points[3],points[1]);
	 	        g2.drawLine(points[3], points[0], points[2], points[1]);
	 	        g2.drawLine(points[4], points[0], points[5],points[1]);
	 	        g2.drawLine(points[5], points[0], points[4], points[1]);
	 	        g2.drawLine(points[6], points[0], points[7],points[1]);
	 	        g2.drawLine(points[7], points[0], points[6], points[1]);
	 	        g2.drawLine(points[8], points[0], points[9],points[1]);
	 	        g2.drawLine(points[9], points[0], points[8], points[1]);
	 	        break;
	        }
	       
	        
	    } 
	    
	 

}
