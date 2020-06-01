import javafx.scene.image.Image;

public class Girl extends Actor{
	public Girl() {
		//image of girl
		String path = getClass().getClassLoader().getResource("IndivProj/Girl.png").toString();
		Image img = new Image(path);
		this.setImage(img);
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

}
