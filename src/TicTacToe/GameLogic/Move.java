package TicTacToe.GameLogic;

/**
 * Created by Jo Øivind Gjernes on 25.10.2015.
 */
public class Move
{
	int x, y;
	int score = 0;
	GameColor player;
	public Move(int x, int y, GameColor player)
	{
		this.x = x;
		this.y = y;
		this.player = player;
	}

	public Move(int score)
	{
		this.score = score;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public GameColor getPlayer() {
		return player;
	}

	public Board doMove(Board b)
	{
		Board tmp = b.getBoardState();
		tmp.putPiece(x,y);
		return tmp;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
}
