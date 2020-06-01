import javafx.scene.image.Image;

public class Bullet extends Actor{
	
	// bullet speed
	private double dy;
	
	public Bullet(double dy) {
		this.dy = dy;
		// bullet image
		String path = getClass().getClassLoader().getResource("IndivProj/Bullet.png").toString();
		Image img = new Image(path);
		this.setImage(img);
	}
	
	public void setDY(double dy) {
		this.dy = dy;
	}

	@Override
	public void act() {
		// bullet collision checks and movement
		if(this.getOneIntersectingObject(Monster.class) != null) {
			Monster m = this.getOneIntersectingObject(Monster.class);
			getWorld().remove(this);
			m.setHP(m.getHP()-1);
		} else if(this.getOneIntersectingObject(Platform.class) != null) {
			getWorld().remove(this);
		}
		this.setY(this.getY()-dy);
	}
	
}
