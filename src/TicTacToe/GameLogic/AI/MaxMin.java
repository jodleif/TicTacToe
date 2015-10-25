package TicTacToe.GameLogic.AI;

import TicTacToe.GameLogic.AI.datastruct.Node;
import TicTacToe.GameLogic.Board;
import TicTacToe.GameLogic.GameColor;
import TicTacToe.GameLogic.GameState;
import TicTacToe.GameLogic.Move;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class MaxMin
{
	private GameColor AIColor;
	private int aiDepth;
	boolean firstMove;

	public MaxMin(int depth, GameColor aiColor){
		aiDepth = depth;
		AIColor = aiColor;
		firstMove=false;
	}

	Move minmax(int depth, GameColor maximizingPlayer, Board board){
		ArrayList<Move> possibleMoves = board.getPossibleMoves();

		int bestScore = (maximizingPlayer==AIColor) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		Move bestMove = null;
		if(possibleMoves.isEmpty() || board.checkForWinner()!=GameState.UNDECIDED){
			bestMove=new Move(board.evaluate(AIColor));
		} else {
			if(possibleMoves.get(0).getPlayer()==AIColor) { // Check once each level
				for(Move m : possibleMoves){
					currentScore=minmax(depth-1,maximizingPlayer.nextPlayer(),m.doMove(board)).getScore();
					if(currentScore>bestScore){
						bestScore = currentScore;
						bestMove = new Move(m.getX(), m.getY(),maximizingPlayer);
						bestMove.setScore(currentScore);
					}
				}
			} else {
				for(Move m: possibleMoves) {
					currentScore = minmax(depth-1,maximizingPlayer.nextPlayer(),m.doMove(board)).getScore();
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
		//if(firstMove) {
			return minmax(aiDepth, AIColor, currentBoard);
		/*} else {
			firstMove=true;
			return new Move(1,1,AIColor);
		}*/
	}
}
