import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Image;

public class GamePanel extends JPanel {
	private Image imgBackground;
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		//big.setBackground(Color.WHITE);
		imgBackground = Toolkit.getDefaultToolkit().getImage("bg.png");
		big.drawImage(imgBackground, 0, 0, 400, 600,null);
	}


	public void updateGameUI(GameReporter reporter){
		//big.clearRect(0, 0, 400, 600);
		big.drawImage(imgBackground, 0, 0, 400, 600,null);

		big.setColor(Color.WHITE);		
		big.drawString(String.format("Score"), 260, 20);
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		big.drawString(String.format("Life"), 30, 20);
		big.drawString(String.format("%02d", reporter.getLife()), 70, 20);
		big.drawString(String.format("Bullets"), 30, 40);
		big.drawString(String.format("%02d", reporter.getChk()), 70, 40);

		if(reporter.getTime()>0){
			big.drawString(String.format("Coutdown"), 100, 20);
			big.drawString(String.format("%02d", reporter.getTime()), 170, 20);
		}

		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
