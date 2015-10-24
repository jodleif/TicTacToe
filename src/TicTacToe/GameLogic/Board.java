package TicTacToe.GameLogic;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class Board
{
	GameColor[][] pieces;
	public static final int FIELDS = 3;
	private GameColor playerTurn;

	public Board(){
		pieces = new GameColor[FIELDS][FIELDS];
		playerTurn = GameColor.RED;
	}

	private boolean putPiece(int x, int y){
		if(checkValidCoord(x,y)) {
			pieces[y][x] = playerTurn;
			playerTurn = playerTurn.nextPlayer();
			return true;
		}
		return false;
	}

	private boolean checkValidCoord(int x, int y)
	{
		if(x<0||y<0||x>=FIELDS||y>=FIELDS)
			return false;
		if(pieces[y][x]!=null)
			return false;
		return true;
	}

	public GameState checkForWinner()
	{
		GameState winner = GameState.UNDECIDED;
		for(int i=0;i<FIELDS;++i){
			// Horizontal
			if(pieces[i][0]!=null && (pieces[i][0]==pieces[i][1])&&pieces[i][1]==pieces[i][2]){
				winner = playerWin(pieces[i][0]);
			}
			// Vertical
			if(pieces[0][i]!=null && (pieces[0][i]==pieces[1][i])&&pieces[1][i]==pieces[2][i]) {
				winner = playerWin(pieces[0][i]);
			}
		}
		if(pieces[0][0]!=null && pieces[0][0]==pieces[1][1]&&pieces[1][1]==pieces[2][2]){
			winner = playerWin(pieces[0][0]);
		}
		if(pieces[2][0]!=null && pieces[2][0]==pieces[1][1]&&pieces[1][1]==pieces[0][2]){
			winner = playerWin(pieces[2][0]);
		}
		if(winner!=GameState.UNDECIDED) return winner;
		if(piecesOnBoard()==9) return GameState.DRAW;
		return winner;

	}

	private int piecesOnBoard()
	{
		int nof = 0;
		for(int i=0;i<FIELDS;++i){
			for (int j = 0; j < FIELDS; j++) {
				if(pieces[i][j]!=null)
					++nof;
			}
		}
		return nof;
	}

	private GameState playerWin(GameColor color)
	{
		return (color==GameColor.RED) ? GameState.REDWIN : GameState.BLUEWIN;
	}


}
