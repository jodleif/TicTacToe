package TicTacToe;

import TicTacToe.GUI.GUIBoard;
import TicTacToe.GUI.ImageLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class TicTacToeApp extends Application
{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	Group root;
	VBox vBox;
	GUIBoard guiBoard;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// Init gui elements
		root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		vBox = new VBox();
		guiBoard = new GUIBoard();
		GridPane gridPane = guiBoard.getGridPane();

		addBackgroundImage();
		// Add gui elements
		root.getChildren().add(vBox);
		vBox.getChildren().add(gridPane);

		// show scene.
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	public void addBackgroundImage()
	{
		ImageView iv = ImageLoader.loadImageViewFromFile("img/board.png");
		if(iv!=null){
			root.getChildren().add(iv);
		}
	}
	public static void main(String[] args)
	{
		launch(args);
		System.exit(0);
	}
}
