package TicTacToe.GUI;

import TicTacToe.GameLogic.Board;
import TicTacToe.GameLogic.GameColor;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;



/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class GUIBoard
{
	private GridPane gridPane;
	private Label statusField;
	private Board gameBoard;
	private Square[][] squares;
	private Image redCircle;
	private Image blueCross;

	public GUIBoard(){
		gameBoard = new Board();
		gridPane = new GridPane();
		squares = new Square[Board.FIELDS][Board.FIELDS];
		statusField = new Label();
		redCircle = ImageLoader.loadImageFromFile("img/circle.png");
		blueCross = ImageLoader.loadImageFromFile("img/cross.png");
		initGUI();
		refreshStatus();
		gridPane.setOnMouseClicked(e -> markFromMouse(e.getX(), e.getY()));

	}

	private void initGUI()
	{
		for (int y = 0; y < Board.FIELDS; y++) {
			for (int x = 0; x < Board.FIELDS; x++) {
				squares[y][x] = new Square(200d, x, y);
				gridPane.add(squares[y][x],x,y);
			}
		}
	}


	public void refresh()
	{
		for (int i = 0; i < Board.FIELDS; i++) {
			for (int j = 0; j < Board.FIELDS; j++) {
				setSquareImage(gameBoard.getPiece(i,j),i,j);
			}
		}
	}

	public void refreshOne(int x, int y)
	{
		setSquareImage(gameBoard.getPiece(x,y),x,y);
	}

	private void setSquareImage(GameColor gc, int x, int y)
	{
		squares[y][x].setImage(fromColor(gc));
	}

	private void markFromMouse(double x, double y){
		int xCoord = (int)(x*(3d/600d));
		int yCoord = (int)(y*(3d/600d));
		System.out.println(xCoord+","+yCoord);
		if(gameBoard.putPiece(xCoord,yCoord)){
			checkState();
			refreshOne(xCoord,yCoord);
			refreshStatus();
		}
	}

	private Image fromColor(GameColor gc)
	{
		switch(gc){
			case BLUE:
				return blueCross;
			case RED:
				return redCircle;
			case UNDEFINED:
				return null;
		}

		//??
		return null;
	}


	private void refreshStatus() {
		setStatusField("Player: " + gameBoard.getPlayerTurn() + "s turn. Game: " +gameBoard.getGameState().toString());
	}
	public GridPane getGridPane()
	{
		return gridPane;
	}

	private void setStatusField(String text){
		statusField.setText(text);
	}

	public Label getStatusField()
	{
		return statusField;
	}

	private void checkState()
	{
		System.out.println(gameBoard.getGameState());
	}

	public void reset()
	{
		gameBoard = new Board();
		refresh();
		refreshStatus();
	}

}
