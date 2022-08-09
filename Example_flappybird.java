import java.awt.*;
import java.util.ArrayList;

class Bird extends SooGame.GameObject {

    SooGame.Ellipse ellipse;

    Bird(SooGame.Vector pos, SooGame.Vector sca) {
        this.setPosition(pos);
        this.setScale(sca);
        this.addPhysics(new SooGame.Physics());
        ellipse = new SooGame.Ellipse(this.getPosition(), this.getScale());
    }

    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        ellipse.setPosition(this.getPosition());
        ellipse.setScale(this.getScale());
        ellipse.update(g2d);

        if (SooGame.input.isMouseClicked()) {
            this.getPhysics().addForce(new SooGame.Vector(0.0f, -5.0f, 0.0f));
        }
    }
}

class Pipe extends SooGame.GameObject {

    SooGame.Square square1;
    SooGame.Square square2;

    Pipe(SooGame.Vector pos, SooGame.Vector sca) {
        this.setPosition(pos);
        this.setScale(sca);

        square1 = new SooGame.Square(this.getPosition(), this.getScale());
        square2 = new SooGame.Square(new SooGame.Vector(this.getPosition().getX(), this.getPosition().getY() + 100.0f + this.getScale().getY(), this.getPosition().getZ()), this.getScale());
    }

    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        this.setPosition(new SooGame.Vector(this.getPosition().getX() - 1.0f, this.getPosition().getY(), this.getPosition().getZ()));

        square1.setPosition(this.getPosition());
        square2.setPosition(new SooGame.Vector(this.getPosition().getX(), this.getPosition().getY() + 100.0f + this.getScale().getY(), this.getPosition().getZ()));

        square1.update(g2d);
        square2.update(g2d);
    }
}

public class Example_flappybird extends SooGame {

    Bird bird;
    ArrayList<Pipe> pipes;

    int frameCount = 1;

    @Override
    public void start() {
        super.start();

        frame.setSize(500, 500);
        frame.setTitle("Flappy Bird");

        bird = new Bird(new Vector((float)frame.getWidth()/4, 50.0f, 0.0f), new Vector(20.0f, 20.0f, 1.0f));

        pipes = new ArrayList<Pipe>();
    }

    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        bird.update(g2d);
        for (Pipe elem : pipes) {
            elem.update(g2d);
        }

        // 50.0f - (float)frame.getHeight()
        // -150.0f

        frameCount += 1;
        if (frameCount % (gameLoopDelayWithMilliSeconds*12) == 0) {
            pipes.add(new Pipe(new Vector((float)frame.getWidth(), (float)random(50 - frame.getHeight(), -150), 0.0f), new Vector(20.0f, (float)frame.getHeight(), 1.0f)));
            frameCount = 1;
        }
    }

    public static void main(String[] args) {
        new Example_flappybird();
    }
}
