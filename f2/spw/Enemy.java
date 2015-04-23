import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;



public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	
	private int step = 12;
	private boolean alive = true;
	
	public Enemy(int x, int y) {
		super(x, y, 20, 25);
		
	}
	public Enemy(int x, int y,int width, int height) {
		super(x, y, width, height);
		
	}
	



	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		//g.setColor(Color.RED);
		//g.fillRect(x, y, width, height);
		if(width ==20){
			Image img = Toolkit.getDefaultToolkit().getImage("bomb.png");
			g.drawImage(img, x, y, width, height, null);
		}
		else{
			Image img = Toolkit.getDefaultToolkit().getImage("bang.png");
			g.drawImage(img, x, y, width, height, null);
		}

	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	public void pickup(){
		alive = false;
	}
}