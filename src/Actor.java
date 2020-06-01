import javafx.scene.image.ImageView;
import java.util.*;
public abstract class Actor extends ImageView {
	public abstract void act();
	
	public World getWorld() {
		if (this.getParent() instanceof World) {
			return (World)(this.getParent());
		}
		return null;
	}
	
	public double getHeight() {
		return this.getLayoutBounds().getHeight();
	}
	
	public double getWidth() {
		return this.getLayoutBounds().getWidth();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		List<A> a = new ArrayList<A>();
		
		for (A actor : getWorld().getObjects(cls)) {
			if (actor != this && actor.intersects(this.getLayoutBounds())) {
				a.add(actor);
			}
		}
		
		return a;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		if (getIntersectingObjects(cls).size() != 0) {
			return getIntersectingObjects(cls).get(0);
		}
		return null;
	}
	
	
}
