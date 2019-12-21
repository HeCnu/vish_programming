import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Craft craft;
	private ArrayList<Asteroid> aliens;
	private boolean ingame;
	private int B_WIDTH;
	private int B_HEIGHT;
	private int score;
	private Image image;
	//private int fps;
	//private long lastFpsTime;

	private int[][] pos = { { 2380, 29 }, { 2500, 59 }, { 1380, 89 },
			{ 780, 109 }, { 580, 139 }, { 680, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
			{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 2380, 329 }, { 2500, 529 }, { 1380, 389 }, { 490, 170 },
			{ 700, 360 }, { 780, 509 }, { 580, 439 }, { 680, 226 },
			{ 790, 459 }, { 1790, 413 }, { 760, 500 }, { 790, 453 },
			{ 980, 309 }, { 560, 465 }, { 510, 475 }, { 930, 322 },
			{ 590, 466 }, { 530, 460 }, { 940, 488 }, { 990, 420 },
			{ 920, 235 }, { 900, 459 }, { 660, 523 }, { 540, 412 },
			{ 810, 372 }, { 860, 320 }, { 740, 430 }, { 820, 458 },
			{ 490, 570 }, { 700, 430 } };

	public Board() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		//URL url = this.getClass().getResource("background.jpg");

		String filename = "C:\\Users\\User\\IdeaProjects\\exam\\src\\main\\java\\background.jpg";

		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ingame = true;

		setSize(800, 600);

		try {
			craft = new Craft();
		} catch (IOException e) {
			e.printStackTrace();
		}

		initAsteroids();

		timer = new Timer(7, this);
		timer.start();
	}

	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();
	}

	public void initAsteroids() {
		aliens = new ArrayList<Asteroid>();

		for (int i = 0; i < pos.length; i++) {
			aliens.add(new Asteroid(pos[i][0], pos[i][1]));
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (ingame) {

			Graphics2D g2d = (Graphics2D) g;

			if (craft.isVisible())
				g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
						this);

			ArrayList<?> ms = craft.getMissiles();

			for (int i = 0; i < ms.size(); i++) {
				Missile m = (Missile) ms.get(i);
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}

			ArrayList<?> ls = craft.getLasers();

			for (int i = 0; i < ls.size(); i++) {
				Laser l = (Laser) ls.get(i);
				g2d.drawImage(l.getImage(), l.getX(), l.getY(), this);
			}

			ArrayList<?> ns = craft.getNova();

			for (int i = 0; i < ns.size(); i++) {
				Nova n = (Nova) ns.get(i);
				g2d.drawImage(n.getImage(), n.getX(), n.getY(), this);
			}
			
			ArrayList<?> as = craft.getArmageddon();

			for (int i = 0; i < as.size(); i++) {
				Armageddon a = (Armageddon) as.get(i);
				g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}

			for (int i = 0; i < aliens.size(); i++) {
				Asteroid a = (Asteroid) aliens.get(i);
				if (a.isVisible())
					g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}

			g2d.setColor(Color.WHITE);
			g2d.drawString("Asteroids left: " + aliens.size(), 5, 15);
			g2d.drawString("Score: " + score, 710, 15);
		} else {
			String msg = "Game Over!";
			Font small = new Font("Helvetica", Font.BOLD, 14);
			FontMetrics metr = this.getFontMetrics(small);

			g.setColor(Color.white);
			g.setFont(small);
			g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
					B_HEIGHT / 2);
			g.drawString("Final score: " + score,
					(B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 + 20);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void actionPerformed(ActionEvent e) {

		if (aliens.size() == 0) {
			ingame = false;
		}

		ArrayList<?> ms = craft.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
		}

		ArrayList<?> ls = craft.getLasers();

		for (int i = 0; i < ls.size(); i++) {
			Laser l = (Laser) ls.get(i);
			if (l.isVisible()) {
				l.move();
			} else {
				ls.remove(i);
			}
		}

		ArrayList<?> ns = craft.getNova();

		for (int i = 0; i < ns.size(); i++) {
			Nova n = (Nova) ns.get(i);
			if (n.isVisible()) {
				n.move();
			} else {
				ns.remove(i);
			}
		}
		
		ArrayList<?> as = craft.getArmageddon();

		for (int i = 0; i < as.size(); i++) {
			Armageddon a = (Armageddon) as.get(i);
			if (a.isVisible()) {
				a.move();
			} else {
				as.remove(i);
			}
		}

		for (int i = 0; i < aliens.size(); i++) {
			Asteroid a = (Asteroid) aliens.get(i);
			if (a.isVisible()) {
				a.move();
			} else {
				aliens.remove(i);
			}
		}

		craft.move();
		checkCollisions();
		repaint();
	}

	public void checkCollisions() {

		Rectangle r3 = craft.getBounds();

		for (int j = 0; j < aliens.size(); j++) {
			Asteroid a = (Asteroid) aliens.get(j);
			Rectangle r2 = a.getBounds();

			if (r3.intersects(r2)) {
				craft.setVisible(false);
				a.setVisible(false);
				ingame = false;
			}
		}

		ArrayList<?> ms = craft.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);

			Rectangle r1 = m.getBounds();

			for (int j = 0; j < aliens.size(); j++) {
				Asteroid a = (Asteroid) aliens.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2) && !a.isExploded()) {
					m.setVisible(false);
					a.explode();
					score++;
				}
			}
		}

		ArrayList<?> ls = craft.getLasers();

		for (int i = 0; i < ls.size(); i++) {
			Laser l = (Laser) ls.get(i);

			Rectangle r1 = l.getBounds();

			for (int j = 0; j < aliens.size(); j++) {
				Asteroid a = (Asteroid) aliens.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2) && !a.isExploded()) {
					l.setVisible(false);
					a.explode();
					score++;
				}
			}
		}

		ArrayList<?> ns = craft.getNova();

		for (int i = 0; i < ns.size(); i++) {
			Nova n = (Nova) ns.get(i);

			Rectangle r1 = n.getBounds();

			for (int j = 0; j < aliens.size(); j++) {
				Asteroid a = (Asteroid) aliens.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2) && !a.isExploded()) {
					//n.setVisible(false);
					a.explode();
					score++;
				}
			}
		}
		ArrayList<?> as = craft.getArmageddon();

		for (int i = 0; i < as.size(); i++) {
			Armageddon ar = (Armageddon) as.get(i);

			Rectangle r1 = ar.getBounds();

			for (int j = 0; j < aliens.size(); j++) {
				Asteroid a = (Asteroid) aliens.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2) && !a.isExploded()) {
					a.explode();
					score++;
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void getFPS() {

	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			craft.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			craft.keyPressed(e);
		}

		public void keyTyped(KeyEvent e) {
			craft.keyTyped(e);
		}
	}
}