import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Armageddon extends Sprite {

	private final int BOARD_HEIGHT = 790;
	private final int MISSILE_SPEED = 2;

	public Armageddon(int x, int y) throws IOException {

		URL url = this.getClass().getResource("/sprites/armageddon.gif");
		image = ImageIO.read(url);
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}

	public void move() {
		x += MISSILE_SPEED;
		if (x > BOARD_HEIGHT) {
			visible = false;
		}
	}
}
