import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Missile extends Sprite {

	private final int BOARD_WIDTH = 790;
	private final int MISSILE_SPEED = 1;

	public Missile (int x, int y) throws IOException {

		URL url = this.getClass().getResource("/sprites/missile.png");
		image = ImageIO.read(url);
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}

	public void move() {
		x += MISSILE_SPEED;
		if (x > BOARD_WIDTH) {
			visible = false;
		}
	}
}