package TicTacToe.GameLogic.AI.datastruct;

import TicTacToe.GameLogic.Board;
import TicTacToe.GameLogic.GameColor;
import TicTacToe.GameLogic.GameState;
import TicTacToe.GameLogic.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jo Øivind Gjernes on 25.10.2015.
 *
 * REF: http://stackoverflow.com/questions/27815233/what-type-of-tree-should-i-use-for-minimax
 */
public class Node
{
	private Board boardState;
	private Map<Move, Node> children;
	private int depth;
	private int bestValue;

	public Node(Board board, int depth){
		children = new HashMap<>();
		boardState = board.getBoardState();
		ArrayList<Move> moves = board.getPossibleMoves();
		this.depth = depth;
		int tDepth = depth-1;
		if((moves!=null || moves.size()!=0)&&this.depth != 0) {
			moves.forEach(move -> {
				Board boardNext = boardState.getBoardState();
				boardNext.putPiece(move.getX(), move.getY());
				children.put(move, new Node(boardNext,tDepth));
			});
		}
	}

	public int evaluate(GameColor maximizingPlayer)
	{
		switch(boardState.checkForWinner()){
			case UNDECIDED:
				return 0;
			case REDWIN:
				if(maximizingPlayer==GameColor.RED) {
					return 1+depth;
				} else {
					return -1-depth;
				}
			case BLUEWIN:
				if(maximizingPlayer==GameColor.BLUE) {
					return 1+depth;
				} else {
					return -1-depth;
				}
			case DRAW:
				return 0+depth;
		}
		return 0;
	}

	public Board getBoardState() {
		return boardState;
	}

	public Map<Move, Node> getChildren()
	{
		return children;
	}

	public void setChildren(Map<Move, Node> children)
	{
		this.children = children;
	}

	public int getDepth()
	{
		return depth;
	}

	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	public int getBestValue()
	{
		return bestValue;
	}

	public void setBestValue(int bestValue)
	{
		this.bestValue = bestValue;
	}
}
