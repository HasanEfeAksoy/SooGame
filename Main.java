import java.awt.*;

// YOU CAN CREATE YOUR CLASSES EXTENDS BY GAMEOBJECT
class Player extends SooGame.GameObject {
    Player(SooGame.Vector pos, SooGame.Vector sca) {
        this.setPosition(pos);
        this.setScale(sca);
        this.addPhysics(new SooGame.Physics());
    }
    void show(Graphics2D g2d) {
        g2d.fillOval((int)this.getPosition().getX(), (int)this.getPosition().getY(), (int)this.getScale().getX(), (int)this.getScale().getY());
    }
    @Override
    public void writeInUpdate() {
        super.writeInUpdate();
        this.setPosition(new SooGame.Vector(this.getPosition().getX() + 3.0f, this.getPosition().getY(), this.getPosition().getZ()));
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

    // UPDATE METHOD RUN EVERYTIME FOR DRAWING ANYTHINGS
    // IT USING AWT DRAW TOOLS
    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        player.show(g2d);
        player.writeInUpdate();
    }

    public static void main(String[] args) {
        // YOU JUST NEED WRITE NEW MAIN() FOR START SOOGAME CONSTRUCTOR
        new Main();
    }
}


