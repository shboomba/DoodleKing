import javafx.scene.image.Image;

public class Platform extends Actor{
	private Image platform;
	public Platform() {
		platform = new Image(getClass().getClassLoader().getResource("IndivProj/Platform.png").toString());
		this.setImage(platform);
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
}
