import java.awt.*;

// YOU CAN CREATE YOUR CLASSES EXTENDS BY GAMEOBJECT
class Player extends SooGame.GameObject {
    Player(SooGame.Vector pos, SooGame.Vector sca) {
        this.setPosition(pos);
        this.setScale(sca);
        this.addPhysics(new SooGame.Physics());
    }

    // GAMEOBJECTS HAVE UPDATE FUNCION
    // YOU SHOULD WRITE IT IN THE YOUR MAIN UPDATE FUNCTION
    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        g2d.fillOval((int)this.getPosition().getX(), (int)this.getPosition().getY(), (int)this.getScale().getX(), (int)this.getScale().getY());
        this.setPosition(new SooGame.Vector(this.getPosition().getX() + 3.0f, this.getPosition().getY(), this.getPosition().getZ()));
    }
}

// EXTENDS SOOGAME
public class Main extends SooGame {

    Player player;
    Text text;

    // START METHOD RUN ONES
    @Override
    public void start() {
        super.start();

        // FIRST YOU MUST WRITE HERE FOR DEFINE SOOGAME VARAIBLES
        player = new Player(new SooGame.Vector(50.0f, 50.0f, 0.0f), new SooGame.Vector(30.0f, 30.0f, 1.0f));
        text = new Text("HELLO WORLD", new Vector(50.0f, 50.0f, 0.0f));
        text.addPhysics(new Physics());
    }

    // UPDATE METHOD RUN EVERYTIME FOR DRAWING ANYTHINGS
    // IT USING AWT DRAW TOOLS
    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        player.update(g2d);
        text.update(g2d);
    }

    public static void main(String[] args) {
        // YOU JUST NEED WRITE NEW MAIN() FOR START SOOGAME CONSTRUCTOR
        new Main();
    }
}


