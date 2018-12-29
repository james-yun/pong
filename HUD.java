import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int score1 = 0;
	public static int score2 = 0;
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.drawString("Player score: " + score1, 15, 80);
		g.drawString("Bot score: " + score2, 15, 64);
	}

}
