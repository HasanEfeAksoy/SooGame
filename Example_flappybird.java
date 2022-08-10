import java.awt.*;
import java.util.ArrayList;

class Bird extends SooGame.GameObject {

    SooGame.Ellipse ellipse;

    Bird(SooGame.Vector pos, SooGame.Vector sca) {
        this.setPosition(pos);
        this.setScale(sca);
        this.setPhysics(new SooGame.Physics(new SooGame.Vector(0.0f, 0.2f, 0.0f), 1.0f));
        ellipse = new SooGame.Ellipse(this.getPosition(), this.getScale(), Color.YELLOW, true);
    }


    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        ellipse.setPosition(this.getPosition());
        ellipse.setScale(this.getScale());
        ellipse.update(g2d);

        if (this.getPosition().getY() >= SooGame.frame.getHeight() - this.getScale().getY()*Math.PI) {
            this.setPosition(new SooGame.Vector(this.getPosition().getX(), (float)(SooGame.frame.getHeight() - this.getScale().getY()*Math.PI), this.getPosition().getZ()));
            this.getPhysics().setVelocity(new SooGame.Vector(0.0f, 0.0f, 0.0f));
        }
        else if (this.getPosition().getY() <= 0.0f) {
            this.setPosition(new SooGame.Vector(this.getPosition().getX(), 0.0f, this.getPosition().getZ()));
            this.getPhysics().setVelocity(new SooGame.Vector(0.0f, 0.0f, 0.0f));
        }

        if (SooGame.input.isMouseClicked()) {
            this.getPhysics().addForce(new SooGame.Vector(0.0f, -6.0f, 0.0f));
        }
    }
}

class Pipe extends SooGame.GameObject {

    SooGame.Square square1;
    SooGame.Square square2;

    Pipe(SooGame.Vector pos, SooGame.Vector sca) {
        this.setPosition(pos);
        this.setScale(sca);

        square1 = new SooGame.Square(this.getPosition(), this.getScale(), Color.GREEN, true);
        square2 = new SooGame.Square(new SooGame.Vector(this.getPosition().getX(), this.getPosition().getY() + 100.0f + this.getScale().getY(), this.getPosition().getZ()), this.getScale(), Color.GREEN, true);
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

    @Override
    public void start() {
        super.start();

        frame.setSize(500, 500);
        frame.setTitle("Flappy Bird");
        this.setBackground(Color.CYAN);

        bird = new Bird(new Vector((float)frame.getWidth()/4, 50.0f, 0.0f), new Vector(20.0f, 20.0f, 1.0f));

        pipes = new ArrayList<Pipe>();

        int add = 0;
        for (int i = 0; i < 6; i++) {
            pipes.add(new Pipe(new Vector((float)frame.getWidth() + add , (float)random(50 - frame.getHeight(), -150), 0.0f), new Vector(20.0f, (float)frame.getHeight(), 1.0f)));
            add += frame.getWidth() / 6;
        }
    }

    @Override
    public void update(Graphics2D g2d) {
        super.update(g2d);

        bird.update(g2d);

        for (Pipe pipe : pipes) {
            pipe.update(g2d);
            if (pipe.getPosition().getX() <= -pipe.getScale().getX()) {
                pipe.setPosition(new Vector(frame.getWidth(), (float)random(50 - frame.getHeight(), -150), pipe.getPosition().getZ()));
            }
        }
    }

    public static void main(String[] args) {
        new Example_flappybird();
    }
}
