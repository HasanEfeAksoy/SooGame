/*
 *
 *
 *       CREATOR :
 *           Hasan Efe Aksoy
 *
 *
 * */


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class SooGame extends JPanel {

    static JFrame frame;
    long gameLoopDelayWithMilliSeconds = 10;

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
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static void quit() {
        System.exit(0);
    }

    /*
    * fun start
    * */

    public static double square(double number) {
        return (double) (number * number);
    }
    public static double toThePowerOf(double down, int up) {
        double result = 1.0;
        if (up < 0) {
            System.out.println("*up value can not less than 0");
        }
        else {
            for (int i = 1; i <= up; i++)
            {
                result = result * down;
            }
        }
        return result;
    }
    public static double areaRect(double length, double height) {
        return (double)(length * height);
    }
    public static double areaTri(double length, double height) {
        return (double)((length * height) / 2);
    }
    public static double distance(double x1, double y1, double x2, double y2) {
        double bound1 = Math.abs((double)x1 - (double)x2);
        double bound2 = Math.abs((double)y1 - (double)y2);
        double dist = Math.sqrt(((double)bound1 * (double)bound1) + ((double)bound2 * (double)bound2));
        return dist;
    }
    public static Vector follow(Vector follower, Vector target, float delay) {
        follower.setX(follower.getX() + (target.getX() - follower.getX()) / delay);
        follower.setY(follower.getY() + (target.getY() - follower.getY()) / delay);
        follower.setZ(follower.getZ() + (target.getZ() - follower.getZ()) / delay);
        return follower;
    }

    public static File file(String path) {
        return new File(path);
    }
    public static File createFile(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) System.out.println();
            else System.out.println("*Already Exist " + file.getName());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return file;
    }
    public static File createFileAgain(String path) {
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return file;
    }
    public static void writeFileContinue(File file, String text) {
        File myFile = new File(file.getPath());
        if (myFile.exists()) {
            Scanner scanner = null;
            String data = "";
            try {
                scanner = new Scanner(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
                data += "\n";
            }
            scanner.close();

            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(data + text);
                fileWriter.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        else {
            System.out.println("*File is not exist");
        }
    }
    public static void writeFileAgain(File file, String text) {
        File myFile = new File(file.getPath());
        if (myFile.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(myFile);
                fileWriter.write(text);
                fileWriter.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        else {
            System.out.println("*File is not exist");
        }
    }
    public static String readFileAll(File file) {
        File myFile = new File(file.getPath());
        String data = "";
        if (myFile.exists()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
                data += "\n";
            }
            scanner.close();
        }
        else {
            data = "";
            System.out.println("*File is not exist");
        }

        return data;
    }
    public static String readFileAllWithoutEnter(File file) {
        File myFile = new File(file.getPath());
        String data = "";
        if (myFile.exists()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
            }
            scanner.close();
        }
        else {
            data = "";
            System.out.println("*File is not exist");
        }

        return data;
    }

    /*
    * fun end
    * */


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
        private boolean isFill = true;
        Text(String text, Font font, Color color, Vector position, boolean isFill) {
            this.text = text;
            this.font = font;
            this.color = color;
            this.isFill = isFill;
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
    public static class Photo extends GameObject {
        private ImageIcon imageIcon;
        Photo(Vector position, Vector scale, String imagePath) {
            this.setPosition(position);
            this.setScale(scale);
            ImageIcon oldImageIcon = new ImageIcon(imagePath);
            Image oldImage = oldImageIcon.getImage();
            Image image = oldImage.getScaledInstance((int)this.getScale().getX(), (int)this.getScale().getY(),  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
        }
        @Override
        public void update(Graphics2D g2d) {
            super.update(g2d);
            g2d.drawImage(imageIcon.getImage(), (int)this.getPosition().getX(), (int)this.getPosition().getY(), null);
        }
    }
    public static class Square extends GameObject {
        private Color color = Color.BLACK;
        private boolean isFill = true;
        Square(Vector position, Vector scale, Color color, boolean isFill) {
            this.setPosition(position);
            this.setScale(scale);
            this.color = color;
            this.isFill = isFill;
        }
        Square(Vector position, Vector scale) {
        }
        public boolean getIsFill() {
            return isFill;
        }
        public void setIsFill(boolean fill) {
            isFill = fill;
        }

        @Override
        public void update(Graphics2D g2d) {
            super.update(g2d);

            g2d.setColor(this.color);
            Rectangle rect2 = new Rectangle((int)this.getPosition().getX(), (int)this.getPosition().y, (int)this.getScale().x, (int)this.getScale().y);
            if (isFill) {
                g2d.fill(rect2);
            }
            g2d.draw(rect2);
        }
    }
    public static class Ellipse extends GameObject {
        private Color color = Color.BLACK;
        private boolean isFill = true;
        Ellipse(Vector position, Vector scale, Color color, boolean isFill) {
            this.setPosition(position);
            this.setScale(scale);
            this.color = color;
            this.isFill = isFill;
        }
        Ellipse(Vector position, Vector scale) {
            this.setPosition(position);
            this.setScale(scale);
        }

        @Override
        public void update(Graphics2D g2d) {
            super.update(g2d);

            g2d.setColor(this.color);
            Ellipse2D ellipse2D = new Ellipse2D.Float();
            ellipse2D.setFrame(new Rectangle((int)this.getPosition().getX(), (int)this.getPosition().y, (int)this.getScale().x, (int)this.getScale().y));
            if (isFill) {
                g2d.fill(ellipse2D);
            }
            g2d.draw(ellipse2D);
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
    public static class Sound {
        Clip clip = null;
        AudioInputStream ais = null;
        File f = null;
        public Sound (String soundUrl) {
            try {
                f = new File(soundUrl);
                ais = AudioSystem.getAudioInputStream(f.toURI().toURL());
                this.clip = AudioSystem.getClip();
                this.clip.open(this.ais);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void play() {
            this.clip.start();
        }
        public void stop() {
            this.clip.stop();
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
        frame.setLocationRelativeTo(null);
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