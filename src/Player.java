import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Actor{
	private double jumpHeight, dx, velocity;
	private boolean isLeft, isRight, toggle, isUp, spaceReleased, falling;
	Image standing, crouching, jumpUpLeft, jumpUpRight, jumpUp, jumpDownLeft, jumpDownRight;
	
	public Player(double dx) {
		jumpHeight = 0;
		isLeft = false;
		isRight = false;
		toggle = false;
		this.dx = dx;
		isUp = false;
		spaceReleased = false;
		standing = new Image(getClass().getClassLoader().getResource("IndivProj/Standing.png").toString());
		crouching = new Image(getClass().getClassLoader().getResource("IndivProj/Crouching.png").toString());
		jumpUpLeft = new Image(getClass().getClassLoader().getResource("IndivProj/JumpUpLeft.png").toString());
		jumpUpRight = new Image(getClass().getClassLoader().getResource("IndivProj/JumpUpRight.png").toString());
		jumpUp = new Image(getClass().getClassLoader().getResource("IndivProj/JumpUp.png").toString());
		jumpDownLeft = new Image(getClass().getClassLoader().getResource("IndivProj/JumpDownLeft.png").toString());
		jumpDownRight = new Image(getClass().getClassLoader().getResource("IndivProj/JumpDownRight.png").toString());
		this.setImage(standing);
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public void setVelocity(double v) {
		velocity = v;
	}
	
	public double getJumpHeight() {
		return jumpHeight;
	}
	
	public void setJumpHeight(double j) {
		jumpHeight = j;
	}
	
	public void setIsUp(boolean b) {
		isUp = b;
	}
	
	public void setToggle(boolean b) {
		toggle = b;
	}
	
	public boolean getToggle() {
		return toggle;
	}
	
	public void setReleased(boolean b) {
		spaceReleased = b;
	}
	
	public double getPlayerX() {
		return this.getX();
	}
	
	@Override
	public void act() {
		// shooting
		if (getWorld().isKeyDown(KeyCode.UP) && !isUp && ((PlayerWorld)getWorld()).getAmmo().getAmmo() != 0) {
			Bullet b = new Bullet(5);
			b.setX(this.getX() + this.getWidth()/2 - b.getWidth()/2);
			b.setY(this.getY() - b.getHeight());
			getWorld().add(b);
			isUp = true;
			Ammo a = ((PlayerWorld)getWorld()).getAmmo();
			a.setAmmo(a.getAmmo() - 1);
		}
		
		// commands on the ground
		if(jumpHeight <= 50 && !spaceReleased && !falling) {
			if (getWorld().isKeyDown(KeyCode.SPACE)) {
				jumpHeight++;
				this.setImage(crouching);
			} else if (getWorld().isKeyDown(KeyCode.LEFT)) {
				this.setX(this.getX()-4);
				if(this.getX() < 0) this.setX(0);
			} else if (getWorld().isKeyDown(KeyCode.RIGHT)) {
				this.setX(this.getX()+4);
				if(this.getX() + this.getWidth() > getWorld().getLayoutBounds().getWidth()) this.setX(getWorld().getLayoutBounds().getWidth() - this.getWidth());
			}
		} else {
			jumpAnimation();
		}	
		boundsCheck();
		
		// win statement
		if(getOneIntersectingObject(Girl.class) != null) {
			getWorld().setHasWon(true);
			getWorld().setGameOver(true);
		}
		
		// lose statement
		if(this.getOneIntersectingObject(Monster.class) != null) {
			getWorld().setHasWon(false);
			getWorld().setGameOver(true);
		}
		
		// ammo grabbing
		if(this.getOneIntersectingObject(AmmoPack.class) != null) {
			AmmoPack ap = this.getOneIntersectingObject(AmmoPack.class);
			getWorld().remove(ap);
			Ammo a = ((PlayerWorld)getWorld()).getAmmo();
			a.setAmmo(a.getAmmo() + 20);
		}
		
	}
	
	// jumping animations
	public void jumpAnimation() {
		if(!toggle) {
			if(getWorld().isKeyDown(KeyCode.LEFT)) {
				isLeft = true;
				this.setImage(jumpUpLeft);
			}  else if (getWorld().isKeyDown(KeyCode.RIGHT)) {
				isRight = true;
				this.setImage(jumpUpRight);
			} else {
				this.setImage(jumpUp);
			}
			velocity = -9 * jumpHeight;
		}
		toggle = true;
		if(isLeft) {
			this.setX(this.getX()-dx);
			if(velocity > 0) {
				this.setImage(jumpDownLeft);
			}
		} else if(isRight) {
			this.setX(this.getX()+dx);
			if(velocity > 0) {
				this.setImage(jumpDownRight);
			}
		}
		velocity += 13;
		this.setY(this.getY() + velocity/25);
	}
	
	// collision checking
	public void boundsCheck() {
		if(this.getX() < 0 || this.getX() + this.getWidth() > getWorld().getLayoutBounds().getWidth()) {
			dx = -dx;
		}
		if(this.getOneIntersectingObject(Platform.class) != null) {
			Platform p = this.getOneIntersectingObject(Platform.class);
			if(this.getY() + this.getHeight() <= p.getY() + p.getHeight()/2 && this.getX() + this.getWidth() >= p.getX() && this.getX() <= p.getX() + p.getWidth() && velocity > 0) {
				dx = Math.abs(dx);
				jumpHeight = 0;
				velocity = 0;
				toggle = false;
				isRight = false;
				isLeft = false;
				spaceReleased = false;
				falling = false;
				this.setImage(standing);
				this.setY(p.getY()-this.getHeight());
			}
		} else {
			if(jumpHeight == 0) {
				setFallingY();
			}
		}
		
	}
	
	// if you walk off the edge of the platform
	public void setFallingY() {
		falling = true;
		velocity += 1;
		this.setY(this.getY() + velocity/25);
	}
}
