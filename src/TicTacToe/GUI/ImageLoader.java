package TicTacToe.GUI;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class ImageLoader
{
	public static ImageView lastImageViewFraFil(String filbane)
	{

		ImageView bildenode = new ImageView();
		Image bilde = lastBildeFraFil(filbane);
		if (bilde != null) {
			bildenode.setImage(bilde);
			return bildenode;
		}
		System.err.println("ERROR: Kunne ikke laste bildet.");
		return null;
	}

	public static Image lastBildeFraFil(String filbane)
	{
		Image bilde;
		try {
			bilde = new Image(filbane);
		} catch (Exception e) {
			System.out.println("[lastImageViewFraFil] ERROR: " + e.getMessage() + "\nFIL: " + filbane);
			return null;
		}
		return bilde;
	}
}
