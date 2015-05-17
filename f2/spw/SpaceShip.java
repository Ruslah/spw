import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Toolkit;
import java.awt.Image;

public class SpaceShip extends Sprite{

	int step = 8;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.YELLOW);
		//g.fillRect(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().getImage("car.gif");
        g.drawImage(img, x, y, width, height, null);
	}

	public void move(int directionX, int directionY){
		if(directionX!=0&&directionY==0){
			x += (step * directionX);
			if(x < 0)
				x = 0;
			if(x > 400 - width)
				x = 400 - width;
		}else if(directionY!=0&&directionX==0){
			y += (step * directionY);
			if(y < 400 )
				y = 400;
			if(y > 550)
				y = 550;
		}
	}
	public void positionSet(){
		x = 180;
		y = 500;

	}
}
