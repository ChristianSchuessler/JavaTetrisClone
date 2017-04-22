package gameLib;

import java.util.ArrayList;

/**
 * subscriber-pattern: Brick informs interested classes what it does
 * Other class can influence the bricks behavior according to information
 */
public interface IBrickMoveListener 
{
	// first argument: bricks which wants to move
	// second argument: how far it wants to move
	// false -> is not allowed to move(because of collision handling issues)
	// true -> allowed
	boolean moveAttempt(Brick brick, Position moveVector);
	boolean moveAttempt(Brick brick, Position singlePosition, Position moveVector);
	
	public boolean checkPositions(Brick brick, ArrayList<Position> positions) ;
	
	// brick appeared on the game 
	void appeared(Brick brick);
	
	// brick was destroyed complete and is not longer in game
	// -> can be removed from internal data structures
	void disappeared(Brick brick);
	
	// indicates that this brick stops its movement.
	void stopped(Brick brick);
	
	// it is only called once, the brick touches the ground or another brick
	void firstStop(Brick brick);
}
