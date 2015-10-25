package TicTacToe;

import TicTacToe.GUI.GUIBoard;
import TicTacToe.GUI.ImageLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class TicTacToeApp extends Application
{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 620;
	Group root;
	VBox vBox;
	GUIBoard guiBoard;

	public static void main(String[] args)
	{
		launch(args);
		System.exit(0);
	}

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
		addStatusField();

		// show scene.
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("TicTacToe");


	}

	private void addStatusField()
	{
		HBox hBox = new HBox();
		Button reset = new Button("New game");
		reset.setOnAction(e -> guiBoard.reset());
		hBox.setPrefHeight(20);
		hBox.getChildren().add(reset);
		hBox.getChildren().add(guiBoard.getStatusField());
		vBox.getChildren().add(hBox);
	}

	public void addBackgroundImage()
	{
		ImageView iv = ImageLoader.loadImageViewFromFile("img/board.png");
		if(iv!=null){
			root.getChildren().add(iv);
		}
	}
}
