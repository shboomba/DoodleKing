import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class PlayerWorld extends World{

	private double x, y;
	private ArrayList<Platform> plats;
	private int spawn, hp;
	private Ammo ammo;
	
	public PlayerWorld() {
		x = 0;
		y = 0;
		ammo = new Ammo();
		spawn = -200;
		hp = 3;
		plats = new ArrayList<>();
		
		ammo.setX(2);
		ammo.setY(20);
		this.getChildren().add(ammo);
	}
	
	public Ammo getAmmo() {
		return ammo;
	}
	
	@Override
	public void act() {
		
		// camera follows player
		for(Node n: this.getChildren()) {
			if(!(n instanceof Text)) {
				n.setTranslateY(-this.getObjects(Player.class).get(0).getY() + 578/1.2);
			}
		}
		
		//deleting monster when dead
		for(int i = 0; i < this.getObjects(Monster.class).size(); i++) {
			Monster monster = this.getObjects(Monster.class).get(i);
			if(monster.getHP() == 0) {
				this.remove(monster);
			}
		}
	}
	
	public void createNewPlatform(int sceneWidth, int sceneHeight, double playerWidth, boolean b) {
		// creating next platform
		Platform p = new Platform();
		if(plats.size() > 0) {
			Platform last = plats.get(0);
			if(Math.random() >= 0.5 || last.getX() + last.getWidth() == playerWidth + 10) {
				x = last.getX() + last.getWidth() + Math.random()*300;
				if(x > sceneWidth - playerWidth - 10) {
					x = sceneWidth - playerWidth - 10;
				}
			} else {
				x = last.getX() - p.getWidth() - Math.random()*300;
				if(x < playerWidth - p.getWidth() + 10) {
					x = playerWidth - p.getWidth() + 10;
				}
			}
			y = last.getY() - Math.random()*260 - 50;
			if(b) {
				x = sceneWidth/2 - p.getWidth()/2;
			}
		} else {
			x = sceneWidth/2 - p.getWidth()/2;
			y = sceneHeight - p.getHeight()*2 - 170;
		}
		//creating monster and ammo packs
		if(y < spawn) {
			Monster m = new Monster(hp);
			m.setX(0);			
			m.setY(spawn);
			spawn -= 1500;
			this.add(m);
			m.setHP(hp);
			hp += 2;
			
			AmmoPack ap = new AmmoPack();			
			ap.setX(x + p.getWidth()/2 - ap.getWidth()/2);
			ap.setY(y + ap.getHeight() + 100);
//			ap.setX(0);
//			ap.setY(spawn+1500);
			this.add(ap);
		}
		p.setX(x);
		p.setY(y);
		plats.add(0, p);
		this.add(p);
		
		//creating girl
		if(b) {
			Girl girl = new Girl();
			Platform last = p;
			girl.setX(last.getX() + last.getWidth()/2);
			girl.setY(last.getY() - girl.getHeight());
			this.add(girl);	
		}
		
	}
	
	public void restart() {
		setGameOver(false);
		ammo.setAmmo(0);
		for (Player p: this.getObjects(Player.class)) {
			p.setX(400 - p.getWidth()/2);
			p.setY(478 - p.getHeight());
			p.setToggle(false);
			p.setJumpHeight(0);
			p.setVelocity(0);
			p.setReleased(false);
		}
		
		for (Platform p: this.getObjects(Platform.class)) {
			this.remove(p);
		}
		this.remove(this.getObjects(Girl.class).get(0));
		plats.clear();
		x = 0;
		y = 0;
		for(int i = 0; i < 60; i++) {
			if(i != 60 - 1) {
				this.createNewPlatform(800, 578, 200, false);
			} else {
				this.createNewPlatform(800, 578, 200, true);
			}
			
		}
		
		Platform ground = new Platform();
		ground.setFitWidth(800);
		ground.setFitHeight(100);
		ground.setX(400 - ground.getWidth()/2);
		ground.setY(578 - ground.getHeight());
		this.add(ground);
	
		this.start();
	}
}
