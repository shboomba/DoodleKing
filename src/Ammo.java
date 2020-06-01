import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Ammo extends Text{
	
	// amount of ammo
	private int ammoCount;
	
	public void updateDisplay() {
		this.setText("Ammo: " + ammoCount);
	}
	
	public Ammo() {
		ammoCount = 0;
		this.setFont(new Font(20));
		updateDisplay();
	}
	public int getAmmo() {
		return ammoCount;
	}
	
	public void setAmmo(int a) {
		ammoCount = a;
		updateDisplay();
	}
	
}
