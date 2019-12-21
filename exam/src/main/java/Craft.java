import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Craft extends Sprite {

	private ArrayList<Missile> missiles;
	private ArrayList<Laser> lasers;
	private ArrayList<Nova> nova;
	private ArrayList<Armageddon> armageddon;
	

	public Craft() throws IOException {
		String filename = "C:\\Users\\User\\IdeaProjects\\exam\\src\\main\\java\\ship05.gif";
		//URL url = this.getClass().getResource("/sprites/ship05.gif");
		image = ImageIO.read(new File(filename));
		width = image.getWidth(null);
		height = image.getHeight(null);
		missiles = new ArrayList<Missile>();
		lasers = new ArrayList<Laser>();
		nova = new ArrayList<Nova>();
		armageddon = new ArrayList<Armageddon>();
		visible = true;
		x = 40;
		y = 60;
	}

	public void move () {
		x += dx;
		y += dy;

		if (x < 1) {
			x = 1;
		}

		if (y < 1) {
			y = 1;
		}
	}

	public ArrayList<Missile> getMissiles () {
		return missiles;
	}
	
	public ArrayList<Laser> getLasers () {
		return lasers;
	}
	
	public ArrayList<Nova> getNova () {
		return nova;
	}
	public ArrayList<Armageddon> getArmageddon () {
		return armageddon;
	}
	
	public void setImage (String img) {
		String filename = "C:\\Users\\User\\IdeaProjects\\exam\\src\\main\\java\\" + img;
		//ImageIcon ii = new ImageIcon(this.getClass().getResource(img));
		try{
			this.image = ImageIO.read(new File(filename));
		}catch (Exception e){}
	}

	public void keyPressed (KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			fire();
		}
		
		if (e.getKeyChar() == 'm') {
			fireMissile();
		}
		
		if (e.getKeyChar() == 'x') {
			fireArmageddon();
		}

		if (key == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
			dx = -1;
		}

		if (key == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
			dx = 1;
		}

		if (key == KeyEvent.VK_UP || e.getKeyChar() == 'w') {
			dy = -1;
			setImage("ship-l.gif");
		}

		if (key == KeyEvent.VK_DOWN || e.getKeyChar() == 's') {
			dy = 1;
			setImage("ship-r.gif");
		}
		
		if (e.getKeyChar() == 27) {
			System.exit(0);
		}
	}
	
	public void keyTyped (KeyEvent e) {

		if (e.getKeyChar() == 'n') {
			fireNova();
		}
	}

	public void fireMissile() {
		try {
			missiles.add(new Missile(x + width, y + height / 2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fire() {
		try {
			lasers.add(new Laser(x + width, y + height / 2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fireNova() {
		try {
			nova.add(new Nova(x + width, y + height / 6));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fireArmageddon() {
		try {
			armageddon.add(new Armageddon(x + width, y + height / 6));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void keyReleased (KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP || e.getKeyChar() == 'w') {
			dy = 0;
			setImage("ship05.gif");
		}

		if (key == KeyEvent.VK_DOWN || e.getKeyChar() == 's') {
			dy = 0;
			setImage("ship05.gif");
		}
	}
}