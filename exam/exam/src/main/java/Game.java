import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;

	public Game() {
		add(new Board());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Asteroids");
		setResizable(false);

		Image Immagine = null;
		String filename = "C:\\Users\\User\\IdeaProjects\\exam\\src\\main\\java\\logo.png";
		//URL url = this.getClass().getResource("/sprites/logo.png");
		try{
			Immagine = ImageIO.read(new File(filename));
		}
		catch (Exception e){}
		this.setIconImage(Immagine);

		setVisible(true);
	}

	public static void main(String[] args) {
		new Game();
	}
}
