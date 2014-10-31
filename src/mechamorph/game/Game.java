package mechamorph.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import mechamorph.game.entity.Player;
import mechamorph.game.render.Screen;
import mechamorph.game.render.gui.Text;
import mechamorph.game.render.sprite.Sprite;
import mechamorph.game.util.input.Keyboard;
import mechamorph.game.world.level.Level;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	//Will change these later to be dynamic for resolution swapping
	private int width = 400;
	private int height = width / 16*9;
	private int scale = 3;

	//Might make graphics environment its own variable
	//Might remove graphics configurations and accelerations altogether later
	GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	private BufferedImage image = gc.createCompatibleImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	private JFrame frame;
	private Thread thread;

	private Level level;
	private Screen screen;
	private Text text;
	private Keyboard keyboard;
	private Player player;

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
		level = new Level(32, 32, "res/map.png");
		//level = new Level(256, 256, Tile.getTile((byte)1));
		screen = new Screen(width, height);
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		text = new Text(screen);
		player = new Player(level.spawnPoint, Sprite.player, level);
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
		player.update(keyboard);
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		screen.clear();

		int xScroll = player.getX() - screen.width / 2 + 16;
		int yScroll = player.getY() - screen.height / 2 + 16;

		int xBorder = level.width * 3 + 16;
		int yBorder = level.height * 9;

		if (xScroll < 0) xScroll = 0;
		if (yScroll < 0) yScroll = 0;
		if (xScroll >= xBorder) xScroll = xBorder;
		if (yScroll >= yBorder) yScroll = yBorder; 
		level.render(xScroll, yScroll, screen);

		player.render(screen);
		//text.draw("Hello world", width / 2 - 32, height / 2, 0x00FF00);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = screen.pixels[y][x];
			}
		}

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