import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Laser extends Sprite {

	private final int BOARD_WIDTH = 790;
	private final int LASER_SPEED = 2;

	public Laser(int x, int y) throws IOException {

		String filename = "C:\\Users\\User\\IdeaProjects\\exam\\src\\main\\java\\shot.png";
		image = ImageIO.read(new File(filename));
		//URL url = this.getClass().getResource("/sprites/shot.png");
		//image = ImageIO.read(url);
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}

	public void move() {
		x += LASER_SPEED;
		if (x > BOARD_WIDTH) {
			visible = false;
		}
	}
}
