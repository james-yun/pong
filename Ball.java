import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Ball extends GameObject{

	private Handler handler;

	Ball(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 1;
		velY = 4;
	}

	public void tick() {
		x += velX;
		y -= velY;
		collision();
		
	}
	private void collision(){
		if(x > Game.WIDTH - 20) velX *= -1;
		if(x < 0) velX *= -1;
		if(y < 0) {HUD.score1++;
		handler.removeObject(this);
		handler.addObject(new Ball(Game.WIDTH/2, Game.HEIGHT/2, ID.Ball, handler));
		}
		if(y > 600) {HUD.score2++;
		handler.removeObject(this);
		handler.addObject(new Ball(Game.WIDTH/2, Game.HEIGHT/2, ID.Ball, handler));
		}
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2 || tempObject.getId() == ID.Bot
                    || tempObject.getId() == ID.Mindwave){
				if (getBounds().intersects(tempObject.getBounds())){
					
					velY *= -1.05;
					velX = (x - tempObject.getX() - 50) / 10;
					
				}
			}
			if (tempObject.getId() == ID.YBot){
				if (getBounds().intersects(tempObject.getBounds())){
					
					velX *= -1;
					//velX = (x - tempObject.getX() - 50) / 10;
					
				}
			}
		}
	}

	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int) x, (int) y, 20, 20);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 20, 20);
	}

}
