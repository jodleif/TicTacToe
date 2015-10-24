package TicTacToe.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class ImageLoader
{

	public static ImageView loadImageViewFromFile(String filepath)
	{

		ImageView imageNode = new ImageView();
		Image image = loadImageFromFile(filepath);
		if (image != null) {
			imageNode.setImage(image);
			return imageNode;
		}
		System.err.println("ERROR: Error loading image: " + filepath);
		return null;
	}

	public static Image loadImageFromFile(String filepath)
	{
		Image image;
		try {
			image = new Image(filepath);
		} catch (Exception e) {
			System.out.println("[loadImageViewFromFile] ERROR: " + e.getMessage() + "\nFIL: " + filepath);
			return null;
		}
		return image;
	}
}
