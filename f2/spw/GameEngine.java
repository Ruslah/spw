import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;


import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Item> item = new ArrayList<Item>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private int t = 0;
	private int count = 10 ;
	private int hp = 3;
	private int chk = 10;
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
				Item();
		
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){

		if((int)(Math.random()*10)%2 ==0  ){
			Enemy e = new Enemy((int)(Math.random()*390), 30);
			gp.sprites.add(e);
			enemies.add(e);
		}
		else{
			Enemy e = new Enemy((int)(Math.random()*390), 50 , 40, 35);
			gp.sprites.add(e);
			enemies.add(e);
		}
		
	}

	private void generateItem(){

		if((int)(Math.random()*10)%2 ==0  ){
			Item it = new Item((int)(Math.random()*390), 30);
			gp.sprites.add(it);
			item.add(it);
		}
			
	}



	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
			
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
				}		
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				decHp();
				e.pickup();
				return;
			}
		}
	}


	
	private void Item(){
		if(Math.random() < difficulty/10){
			generateItem();
		}
		
			
		
		Iterator<Item> it_iter = item.iterator();
		while(it_iter.hasNext()){
			Item it = it_iter.next();
			it.proceed();
			if(!it.isAlive()){
				it_iter.remove();
				gp.sprites.remove(it);
				t--;
				}		
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double tr;
		for(Item it : item){
			tr = it.getRectangle();
			if(tr.intersects(vr)){
				t=10;
				it.pickup();
				Inflate(true);
				return;
			}
			if(score%2000==0){
				Inflate(false);
			}
		}
	}


	
	public void die(){
		timer.stop();
	}

		
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,0);
			break;		
        case KeyEvent.VK_UP:
            v.move(0,-1);
			break;

        case KeyEvent.VK_DOWN:
             v.move(0,1);
			break;
		
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}

	public long getScore(){
		return score;
	}

	public int getHp(){
		return hp;
	}

	public int getTime(){
		return t;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}

	public void decHp(){
		hp--;
		if(hp <= 0){
			die();
		}
	}

	public void Inflate(boolean inf){
		
		if(inf==true){
			v.width = 15;
			v.height = 35;
		}else if(inf==false){
			v.width = 30;
			v.height = 70;
		}
	}

	
}
