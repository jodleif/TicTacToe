package TicTacToe.GameLogic.AI;

import TicTacToe.GameLogic.Board;
import TicTacToe.GameLogic.GameColor;
import TicTacToe.GameLogic.GameState;
import TicTacToe.GameLogic.Move;

import java.util.ArrayList;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 *
 * MiniMax - trial
 */
public class MiniMaxAI
{
	boolean firstMove;
	private GameColor AIColor;
	private int aiDepth;

	public MiniMaxAI(int depth, GameColor aiColor)
	{
		aiDepth = depth;
		AIColor = aiColor;
		firstMove=false;
	}

	Move minimax(int depth, GameColor maximizingPlayer, Board board)
	{
		ArrayList<Move> possibleMoves = board.getPossibleMoves();
		int bestScore = (maximizingPlayer==AIColor) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		Move bestMove = null;
		if(possibleMoves.isEmpty() || board.checkForWinner()!=GameState.UNDECIDED){
			bestMove=new Move(board.evaluate(AIColor));
		} else {
			if(possibleMoves.get(0).getPlayer()==AIColor) { // Check once each level
				for(Move m : possibleMoves){
					currentScore = minimax(depth - 1, maximizingPlayer.nextPlayer(), m.doMove(board)).getScore();
					if(currentScore>bestScore){
						bestScore = currentScore;
						bestMove = new Move(m.getX(), m.getY(),maximizingPlayer);
						bestMove.setScore(currentScore);
					}
				}
			} else {
				for(Move m: possibleMoves) {
					currentScore = minimax(depth - 1, maximizingPlayer.nextPlayer(), m.doMove(board)).getScore();
					if(currentScore<bestScore){
						bestScore = currentScore;
						bestMove = new Move(m.getX(), m.getY(),maximizingPlayer);
						bestMove.setScore(currentScore);
					}
				}
			}
		}
		return bestMove;
	}

	public Move aiMove(Board currentBoard){
		Move res = minimax(aiDepth, AIColor, currentBoard);
		return res;
	}
}
