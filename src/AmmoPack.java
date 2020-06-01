import javafx.scene.image.Image;

public class AmmoPack extends Actor{
	
	// image of ammo pack
	private Image pack;
	
	public AmmoPack() {
		pack = new Image(getClass().getClassLoader().getResource("IndivProj/AmmoPack.png").toString());
		this.setImage(pack);
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
}
