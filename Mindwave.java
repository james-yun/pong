import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mindwave extends GameObject{

    private Handler handler;
    public static float speed = 5;
    int attention = 0;
    String filename = "/Users/jamesyun/Desktop/logFile.csv";
    String lastLine = "";
    File file = new File(filename);

    public Mindwave(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }


    public void tick() {
        try {
            Scanner inputStream = new Scanner(file);
            inputStream.nextLine();
            while (inputStream.hasNext()) {
                lastLine = inputStream.nextLine();
            }
            if (lastLine.split(",")[10] != "NA"){
                attention = Integer.parseInt(lastLine.split(",")[10].trim());
                System.out.println(attention);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (attention < 50) {
            speed = 0;
        }
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
