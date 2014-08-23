package mechamorph.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	//Will change these later to be dynamic for resolution swapping
	private int width = 300;
	private int height = width / 16*9;
	private int scale = 3;
	
	//Might make graphics environment its own variable
	//Might remove graphics configurations and accelerations altogether later
	GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	private BufferedImage image = gc.createCompatibleImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private JFrame frame;
	private Thread thread;
	
	//Possible wont be static later
	public static boolean running = false;

	private void setupFrame() {
		Dimension size = new Dimension(width * scale, height * scale);
		frame = new JFrame();
		frame.setPreferredSize(size);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Title WIP");
		frame.setVisible(true);
	}

	public Game() {
		image.setAccelerationPriority(1);
		setupFrame();
		start();
	}

	public static void main(String[] args) {
		new Game();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		try {
			running = false;
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update() {

	}

	Random random = new Random(0xffffff);
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = random.nextInt();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
		
	}

	public void run() {
		long last = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1e9 / 60;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - last) / ns;
			last = now;
			while (delta >= 1) {
				updates++;
				update();
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("UPS: " + updates + ", FPS: " + frames);
				frames = updates = 0;
			}
		}
		stop();
	}
}