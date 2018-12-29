import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}

	public void tick() {
		x += velX;
		x = Game.clamp(x, 0, Game.WIDTH - 130);
	}

	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, 130, 25);
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 130, 25);
	}

}
