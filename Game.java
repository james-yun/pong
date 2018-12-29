import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;



public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800, HEIGHT = WIDTH / 4 * 3;
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	private Handler handler;
	private HUD hud;
	private Menu menu;

	public enum STATE{
		Menu,
	    Game,
        Paused,
        Upgrades
    }

    public static STATE gameState = STATE.Menu;
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Pong", this);
		if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2 - 65, 530, ID.Player));
            handler.addObject(new Bot(WIDTH/2 - 65, 25, ID.Bot, handler));

            handler.addObject(new Ball(Game.WIDTH/2, Game.HEIGHT/2, ID.Ball, handler));

        }
		//handler.addObject(new Bot(WIDTH/2 - 65, 530, ID.Bot, handler));
		//handler.addObject(new YBot(50, HEIGHT/2, ID.YBot, handler));
		//handler.addObject(new YBot(750, HEIGHT/2, ID.YBot, handler));
        //handler.addObject((new Player2(WIDTH/2 - 65, 25, ID.Player2)));
		
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		final double ns = 1000000000 / 60.0;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			
			render();
			frames++;
				
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		stop();
	}
	
	public void tick(){
	    if(gameState == STATE.Game){
            handler.tick();
            if(gameState == STATE.Menu || gameState == STATE.Paused || gameState == STATE.Upgrades){
                menu.tick();
            }
        }
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
        if(paused){
            g.setColor(Color.WHITE);
            g.drawString("Paused", 100, 100);
        }
		if(gameState == STATE.Game){
            hud.render(g);
        }else if (gameState == STATE.Menu){
		    menu.render(g);
        }else if(gameState == STATE.Paused || gameState == STATE.Upgrades){
        	hud.render(g);
        	menu.render(g);
		}

		g.dispose();
		bs.show();


	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else return var;
	}
	
	public static void main(String[] args) {
		new Game();
	}

	
	

}
