
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class Menu extends MouseAdapter{

    private Game game;
    private Handler handler;
    private HUD hud;
    public boolean hadoooken;

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    Font fnt = new Font("b", 1, 50);
    Font fnt2 = new Font("m", 1, 30);
    Font fnt3 = new Font("s", 1, 20);

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.Menu){
            //single player (now mindwave)
            if(mouseOver(mx, my, 250, 150, 300, 64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2 - 65, 530, ID.Player));
                //handler.addObject(new Mindwave(Game.WIDTH/2 - 65, 530, ID.Mindwave, handler));
                handler.addObject(new Bot(Game.WIDTH/2 - 65, 25, ID.Bot, handler));
                handler.addObject(new Ball(Game.WIDTH/2, Game.HEIGHT/2, ID.Ball, handler));
            }
            //2 player
            else if(mouseOver(mx, my, 250, 250, 300, 64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2 - 65, 530, ID.Player));
                handler.addObject(new Ball(Game.WIDTH/2, Game.HEIGHT/2, ID.Ball, handler));
                handler.addObject((new Player2(Game.WIDTH/2 - 65, 25, ID.Player2)));
            }

        }
        else if(game.gameState == Game.STATE.Paused){
            //play button
            if(mouseOver(mx, my, 250, 150, 300, 64)) {
                game.gameState = Game.STATE.Game;
            }
            if(mouseOver(mx, my, 250, 250, 300, 64)) {
                game.gameState = Game.STATE.Upgrades;
            }

        }
        if(game.gameState == Game.STATE.Menu || game.gameState == Game.STATE.Paused){
            //Quit button
            if(mouseOver(mx, my, 250, 350, 300, 64)){
                System.exit(1);
            }
        }
        if(game.gameState == Game.STATE.Upgrades)
            //Hadooken
            if(mouseOver(mx, my, 100, 150, 130, 100)){
                hadoooken = true;
            }
            //Back button
            if(mouseOver(mx, my, 250, 350, 300, 64)){
                game.gameState = Game.STATE.Paused;
            }
    }

    public void tick(){

    }

    public void render(Graphics g){
        if (game.gameState == Game.STATE.Menu){

            g.setFont(fnt);

            g.setColor(Color.WHITE);
            g.drawString("Pong", 335, 70);
            //single player
            g.setFont(fnt2);
            g.drawRect(250, 150, 300, 64);
            g.drawString("Single Player", 270, 190);
            if(mouseOver((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(),
                    570, 300, 300, 64)){
                g.setColor(Color.BLUE);
                g.fillRect(250, 150, 300, 64);
                g.setColor(Color.WHITE);
                g.drawString("Single Player", 270, 190);
            }
            //2 player
            g.drawRect(250, 250, 300, 64);
            g.drawString("2 Player", 270, 290);
            if(mouseOver((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(),
                    570, 400, 300, 64)){
                g.setColor(Color.PINK);
                g.fillRect(250, 250, 300, 64);
                g.setColor(Color.WHITE);
                g.drawString("2 Player", 270, 290);
            }

        }
        else if(game.gameState == Game.STATE.Paused){
            //return to game
            g.setFont(fnt2);
            g.drawRect(250, 150, 300, 64);
            g.drawString("Return to Game", 270, 190);
            if(mouseOver((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(),
                    570, 300, 300, 64)){
                g.setColor(Color.BLUE);
                g.fillRect(250, 150, 300, 64);
                g.setColor(Color.WHITE);
                g.drawString("Return to Game", 270, 190);
            }
            //upgrades
            g.drawRect(250, 250, 300, 64);
            g.drawString("Upgrades", 270, 290);
            if(mouseOver((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(),
                    570, 400, 300, 64)){
                g.setColor(Color.PINK);
                g.fillRect(250, 250, 300, 64);
                g.setColor(Color.WHITE);
                g.drawString("Upgrades", 270, 290);
            }

        }
        else if (game.gameState == Game.STATE.Upgrades){
            //back button
            g.setFont(fnt2);
            g.drawRect(250, 350, 300, 64);
            g.drawString("Back", 270, 390);
            if(mouseOver((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(),
                    570, 500, 300, 64)){
                g.setColor(Color.DARK_GRAY);
                g.fillRect(250, 350, 300, 64);
                g.setColor(Color.WHITE);
                g.drawString("Back", 270, 390);
            }
            //upgrade1
            g.drawRect(100, 150, 130, 100);
            g.setFont(fnt3);
            g.drawString("Hadoooken", 110, 200);
            if(mouseOver((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(),
                    400, 300, 300, 100)){
                g.setColor(Color.DARK_GRAY);
                g.fillRect(100, 150, 130, 100);
                g.setColor(Color.WHITE);
                g.drawString("Hadoooken", 110, 200);
            }

        }
        if(game.gameState == Game.STATE.Menu || game.gameState == Game.STATE.Paused){
            //quit button
            g.drawRect(250, 350, 300, 64);
            g.drawString("Quit", 270, 390);
            if(mouseOver((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(),
                    570, 500, 300, 64)){
                g.setColor(Color.DARK_GRAY);
                g.fillRect(250, 350, 300, 64);
                g.setColor(Color.WHITE);
                g.drawString("Quit", 270, 390);
            }
        }

    }



}
