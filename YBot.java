import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class YBot extends GameObject{
	
	private Handler handler;

	public YBot(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	
	public void tick() {
		y += velY;
		y = Game.clamp(y, 0, Game.HEIGHT - 50);
		follow();
		
	}
	
	private void follow(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Ball){
				if (y < tempObject.getY() - 60) velY = 4;
				else if(y > tempObject.getY() - 60) velY = -4;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) x, (int) y, 25, 130);
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 25, 130);
	}

}
