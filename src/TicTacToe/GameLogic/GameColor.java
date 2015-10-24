package TicTacToe.GameLogic;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public enum GameColor
{
	UNDEFINED,
	BLUE,
	RED;
	public GameColor nextPlayer() {
		return (this==GameColor.RED) ? GameColor.BLUE : GameColor.RED;
	}
}
