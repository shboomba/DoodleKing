import javafx.scene.image.Image;

public class Monster extends Actor{
	
	// monster speed
	private double dx;
	//monster hit points
	private int HP;
	
	public Monster(double dx) {
		this.dx = dx;
		this.setImage(new Image(getClass().getClassLoader().getResource("IndivProj/Monster.png").toString()));
		HP = 3;
	}
	
	public void setHP(int h) {
		HP = h;
	}
	public int getHP() {
		return HP;
	}
	
	@Override
	public void act() {
		// boundary check
		this.setX(this.getX() + dx);
		if(this.getX() + this.getWidth() > getWorld().getLayoutBounds().getWidth() || this.getX() < 0) {
			dx = -dx;
		}
	}
}
