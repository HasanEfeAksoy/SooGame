import java.awt.*;

// YOU CAN CREATE YOUR CLASSES EXTENDS BY GAMEOBJECT
class Player extends SooGame.GameObject {
    Player(SooGame.Vector pos, SooGame.Vector sca) {
        this.setPosition(pos);
        this.setScale(sca);
        this.addPhysics(new SooGame.Physics());
    }
}

// EXTENDS SOOGAME
public class Main extends SooGame {

    Player player;

    // START METHOD RUN ONES
    @Override
    public void start() {
        super.start();

        // YOU MUST WRITE HERE
        player = new Player(new SooGame.Vector(50.0f, 50.0f, 0.0f), new SooGame.Vector(30.0f, 30.0f, 1.0f));
    }

    // UPDATE METHOD RUN EVERYTIME (DEFAULT 10 MILISECOND DELAY)
    @Override
    public void update() {
        super.update();

        // YOU MUST WRITE HERE
        player.setPosition(new Vector(player.getPosition().getX() + 3.0f, player.getPosition().getY(), player.getPosition().getZ()));
        player.writeInUpdate();
    }

    // DRAW METHOD RUN EVERYTIME FOR DRAWING ANYTHINGS
    // IT USING AWT DRAW TOOLS
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        g2d.fillOval((int)player.getPosition().getX(), (int)player.getPosition().getY(), (int)player.getScale().getX(), (int)player.getScale().getY());
    }

    public static void main(String[] args) {
        // YOU JUST NEED WRITE NEW MAIN() FOR START SOOGAME CONSTRUCTOR
        new Main();
    }
}


