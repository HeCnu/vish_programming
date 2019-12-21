import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Asteroid extends Sprite {

	private String filename = "C:\\Users\\User\\IdeaProjects\\exam\\src\\main\\java\\ship05.gif";

	private boolean exploded;

	public Asteroid(int x, int y) {
		//URL url = this.getClass().getResource("/sprites/ship05.gif");
		try {
			image = ImageIO.read(new File(filename));
		}
		catch (Exception e){}
		//ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		//image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		this.x = x;
		this.y = y;
	}

	public void move() {
		if (x < 0) {
			x = 800;
		}
		x -= 1;
	}

	public void explode() {
		String filename = "C:\\Users\\User\\IdeaProjects\\exam\\src\\main\\java\\explosion.gif";
		try{
			image = ImageIO.read(new File(filename));
		}
		catch (Exception e){}
		//ImageIcon ii = new ImageIcon(this.getClass().getResource("/sprites/explosion.gif"));
		//image = ii.getImage();

		Timer timer = new Timer();
		timer.schedule(new ExplodeTask(), 500);
		exploded = true;
	}

	public boolean isExploded() {
		return exploded;
	}

	private class ExplodeTask extends TimerTask {
		@Override
		public void run() {
			visible = false;
		}
	}
}