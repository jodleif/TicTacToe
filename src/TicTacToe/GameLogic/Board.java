package TicTacToe.GameLogic;


/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class Board
{
	GameColor[] pieces;
	public static final int FIELDS = 3;
	private GameColor playerTurn;
	private GameState gameState;

	public Board(){
		pieces = new GameColor[FIELDS*FIELDS];
		playerTurn = GameColor.RED;
		gameState = GameState.UNDECIDED;
		buildBoard();
	}

	public boolean putPiece(int x, int y){
		if(checkValidCoord(x,y)&&gameState==GameState.UNDECIDED) {
			pieces[y*FIELDS+x] = playerTurn;
			playerTurn = playerTurn.nextPlayer();
			gameState = checkForWinner();
			return true;
		}
		return false;
	}

	private void buildBoard()
	{
		for (int y = 0; y < FIELDS ; ++y) {
			for (int x = 0; x <FIELDS ; x++) {
				pieces[y*FIELDS+x] = GameColor.UNDEFINED;
			}
		}
	}

	private boolean checkValidCoord(int x, int y)
	{
		if(x<0||y<0||x>=FIELDS||y>=FIELDS)
			return false;
		if(getPiece(x,y)!=GameColor.UNDEFINED)
			return false;
		return true;
	}

	public GameState checkForWinner()
	{
		GameState winner = GameState.UNDECIDED;
		for(int i=0;i<FIELDS;++i){
			// Horizontal
			if(getPiece(i,0)!=GameColor.UNDEFINED && (getPiece(i,0)==getPiece(i,1))&&getPiece(i,1)==getPiece(i,2)){
				winner = pieceColorToGame(getPiece(i,0));
			}
			// Vertical
			if(getPiece(0,i)!=GameColor.UNDEFINED && (getPiece(0,i)==getPiece(1,i))&&getPiece(1,i)==getPiece(2,i)) {
				winner = pieceColorToGame(getPiece(0,i));
			}
		}
		if(getPiece(0,0)!=GameColor.UNDEFINED  && getPiece(0,0)==getPiece(1,1)&&getPiece(1,1)==getPiece(2,2)){
			winner = pieceColorToGame(getPiece(0,0));
		}
		if(getPiece(2,0)!=GameColor.UNDEFINED && getPiece(2,0)==getPiece(1,1)&&getPiece(1,1)==getPiece(0,2)){
			winner = pieceColorToGame(getPiece(2,0));
		}
		if(winner!=GameState.UNDECIDED) return winner;
		if(piecesOnBoard()==9) return GameState.DRAW;
		return winner;
	}

	private int piecesOnBoard()
	{
		int nof = 0;
		for(int y=0;y<FIELDS;++y){
			for (int x = 0; x < FIELDS; x++) {
				if(getPiece(x,y)!=GameColor.UNDEFINED)
					++nof;
			}
		}
		return nof;
	}

	private GameState pieceColorToGame(GameColor color)
	{
		return (color==GameColor.RED) ? GameState.REDWIN : GameState.BLUEWIN;
	}

	public GameColor getPiece(int x, int y)
	{
		return pieces[y*FIELDS+x];
	}

	public GameState getGameState(){
		return gameState;
	}

	public GameColor getPlayerTurn() {
		return playerTurn;
	}


}
