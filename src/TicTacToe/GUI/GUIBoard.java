package TicTacToe.GUI;

import TicTacToe.GameLogic.AI.MaxMin;
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
	private MaxMin aiPlayer;
	private GameColor playerColor;

	public GUIBoard(){
		gameBoard = new Board();
		gridPane = new GridPane();
		squares = new Square[Board.FIELDS][Board.FIELDS];
		statusField = new Label();
		redCircle = ImageLoader.loadImageFromFile("img/circle.png");
		blueCross = ImageLoader.loadImageFromFile("img/cross.png");
		playerColor = GameColor.RED;
		aiPlayer = new MaxMin(7,playerColor.nextPlayer());
		initGUI();
		refreshStatus();
		gridPane.setOnMouseClicked(e -> markFromMouse(e.getX(), e.getY()));
		aiTrekk();
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
				GameColor gc = gameBoard.getPiece(j,i);
				setSquareImage(gc,j,i);
			}
		}
	}

	public void refreshOne(int x, int y)
	{
		setSquareImage(gameBoard.getPiece(x,y),x,y);
	}

	private void setSquareImage(GameColor gc, int x, int y)
	{
		if(gc!=GameColor.UNDEFINED) {
			squares[y][x].setImage(fromColor(gc));
		} else {
			squares[y][x].removeImage();
		}
	}

	private void markFromMouse(double x, double y){
		if(gameBoard.getPlayerTurn()==playerColor) {
			int xCoord = (int) (x * (3d / 600d));
			int yCoord = (int) (y * (3d / 600d));
			System.out.println(xCoord + "," + yCoord);
			if (gameBoard.putPiece(xCoord, yCoord)) {
				checkState();
				refreshOne(xCoord, yCoord);
				refreshStatus();
				aiTrekk();
			}
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
		aiTrekk();
		aiPlayer=new MaxMin(15, playerColor.nextPlayer());
	}

	private void aiTrekk()
	{
		if(gameBoard.getPlayerTurn()!=playerColor){
			setStatusField("AI TENKER");
			gameBoard = aiPlayer.aiMove(gameBoard).doMove(gameBoard);
			checkState();
			refresh();
			refreshStatus();
		}
	}
}
