import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bot extends GameObject{
	
	private Handler handler;
	public static float speed = 2;

	public Bot(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	
	public void tick() {
		x += velX;
		x = Game.clamp(x, 0, Game.WIDTH - 130);
		follow();
		
	}
	
	private void follow(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Ball){
				if (x < tempObject.getX() - 60) velX = speed;
				else if(x > tempObject.getX() - 60) velX = -speed;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, 130, 25);
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 130, 25);
	}

}
