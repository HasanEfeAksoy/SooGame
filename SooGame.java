/*
 *
 *
 *       CREATOR :
 *           Hasan Efe Aksoy
 *
 *
 * */


import javax.swing.*;
import java.awt.*;


public class SooGame extends JPanel {

    JFrame frame;
    long gameLoopDelayWithMilliSeconds = 10;
    private boolean isQuited = false;

    /*
     *
     * start
     *
     * */

    public static void print(String string) {
        System.out.print(string);
    }
    public static void println(String string) {
        System.out.println(string);
    }
    public static void quit() {
        System.exit(0);
    }




    public static class GameObject {
        private Vector position = new Vector(0.0f, 0.0f, 0.0f);
        private Vector scale = new Vector(1.0f, 1.0f, 1.0f);
        private Physics physics = null;

        public Vector getPosition() {
            return position;
        }
        public void setPosition(Vector position) {
            this.position = position;
        }

        public Vector getScale() {
            return scale;
        }
        public void setScale(Vector scale) {
            this.scale = scale;
        }

        public Physics getPhysics() {
            return physics;
        }

        public void addPhysics(Physics physics) {
            this.physics = physics;
        }
        public void removePhysics() {
            this.physics = null;
        }

        public void update(Graphics2D g2d) {
            if (this.physics != null) {
                this.physics.enablePhysics(this);
            }
        }
    }

    public static class Text extends GameObject {
        private String text = "";
        private Font font = new Font("Arial", Font.PLAIN, 10);
        private Color color = Color.BLACK;
        Text(String text, Font font, Color color, Vector position) {
            this.text = text;
            this.font = font;
            this.color = color;
            this.setPosition(position);
        }
        Text(String text, Vector position) {
            this.text = text;
            this.setPosition(position);
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        public Font getFont() {
            return font;
        }
        public void setFont(Font font) {
            this.font = font;
        }
        public Color getColor() {
            return color;
        }
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void update(Graphics2D g2d) {
            super.update(g2d);
            g2d.setFont(this.font);
            g2d.setColor(this.color);
            g2d.drawString(this.text, this.getPosition().getX(), this.getPosition().getY());
        }
    }
    public static class Vector {
        private float x = 0.0f;
        private float y = 0.0f;
        private float z = 0.0f;

        Vector(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public float getX() {
            return x;
        }
        public void setX(float x) {
            this.x = x;
        }
        public float getY() {
            return y;
        }
        public void setY(float y) {
            this.y = y;
        }
        public float getZ() {
            return z;
        }
        public void setZ(float z) {
            this.z = z;
        }

        public void add(Vector vector) {
            this.x += vector.x;
            this.y += vector.y;
            this.z += vector.z;
        }
        public void sub(Vector vector) {
            this.x -= vector.x;
            this.y -= vector.y;
            this.z -= vector.z;
        }
        public void mult(Vector vector) {
            this.x *= vector.x;
            this.y *= vector.y;
            this.z *= vector.z;
        }
        public void div(Vector vector) {
            this.x /= vector.x;
            this.y /= vector.y;
            this.z /= vector.z;
        }
    }
    public static class Physics {
        Vector gravity = new Vector(0.0f, 0.1f, 0.0f);
        Vector velocity = new Vector(0.0f, 0.0f, 0.0f);
        float mass = 1f;

        Physics(Vector gravity, float mass) {
            this.gravity = gravity;
            this.mass = mass;
        }
        Physics() {
        }

        public void enablePhysics(GameObject gameObject) {
            velocity.add(gravity);
            velocity.mult(new Vector(mass, mass, mass));
            velocity.mult(new Vector(0.99f, 0.99f, 0.99f));
            gameObject.position.add(velocity);
        }
    }


    /*
     *
     * end
     *
     * */

    public void start() {
        //overrided
    }
    public void update(Graphics2D g2d) {
        //overrided
    }
    private void display() {
        this.repaint();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //draw in here (draw()) + (update)
        update(g2d);
    }

    SooGame() {
        println("\n********** SooGame **********\n*\thttps://github.com/HasanEfeAksoy/SooGame\n*\n*\n*Your Project is starting...\n*..\n*.\n********** SooGame **********");
        frame = new JFrame("Window");
        frame.add(this);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // first (start())
        start();
        frame.setVisible(true);

        while (true) {
            // draw in here (update())
            display(); // (repaint)
            try {
                Thread.sleep(gameLoopDelayWithMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}