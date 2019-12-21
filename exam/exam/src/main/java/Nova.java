import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Nova extends Sprite {

	private final int BOARD_WIDTH = 790;
	private final int LASER_SPEED = 2;

	public Nova(int x, int y) throws IOException {

		URL url = this.getClass().getResource("/sprites/nova.gif");
		image = ImageIO.read(url);
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
