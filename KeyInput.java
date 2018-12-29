import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean [2];
	private boolean[] keyDown2 = new boolean [2];
	
	public KeyInput (Handler handler){
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player){

				if(key == KeyEvent.VK_LEFT) {tempObject.setVelX(-10); keyDown[0] = true;}
                if(key == KeyEvent.VK_RIGHT) {tempObject.setVelX(10); keyDown[1] = true;}
				
			}
			if (tempObject.getId() == ID.Player2){

                if(key == KeyEvent.VK_A) {tempObject.setVelX(-10); keyDown2[0] = true;}
                if(key == KeyEvent.VK_D) {tempObject.setVelX(10); keyDown2[1] = true;}
            }
		}
        if(key == KeyEvent.VK_P){
            if(Game.gameState == Game.STATE.Game){
               Game.gameState = Game.STATE.Paused;
            }else if(Game.gameState == Game.STATE.Paused){
                Game.gameState = Game.STATE.Game;
            }

        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player){

                if(key == KeyEvent.VK_LEFT) keyDown[0] = false;
				if(key == KeyEvent.VK_RIGHT) keyDown[1] = false;

                    if(!(keyDown[0] || keyDown[1])) tempObject.setVelX(0);
					
			}
            if (tempObject.getId() == ID.Player2){

                if(key == KeyEvent.VK_A) keyDown2[0] = false;
                if(key == KeyEvent.VK_D) keyDown2[1] = false;

                if(!(keyDown2[0] || keyDown2[1])) tempObject.setVelX(0);

            }
		}
	}
}
