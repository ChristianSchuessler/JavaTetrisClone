package gameLib;

import java.util.ArrayList;
import java.util.LinkedList;

import gameLib.IBrickMoveListener;

/**
 * this class handles all collision detection.
 * inform listener that brick collided and if a row is full
 */
public class BrickField implements IBrickMoveListener
{
	private LinkedList<Brick> _brickList;
	
	Position _fieldStartPosition;
	int _fieldWidth;
	int _fieldHeight;
	
	/**
	 * bottomLineYPosition, position where bricks have to stop and cannot go further
	 */
	public BrickField(Position fieldStartPosition, int fieldWidth, int fieldHeight)
	{
		if(fieldHeight <= Brick.getRectSize())
		{
			throw new IllegalArgumentException("bottomLineYPosition must be at leat greater than " + Brick.getRectSize());
		}
		
		_fieldStartPosition = fieldStartPosition;
		_fieldWidth = fieldWidth;
		_fieldHeight = fieldHeight;
		_brickList = new LinkedList<Brick>();
	}
	
	
	/**
	 * this method returns given brick does collide if it would move
	 * about the given distance.
	 * if so -> false is returned
	 * if not so -> brick.move() is called and true is returned 
	 */
	public boolean moveAttempt(Brick brick, Position moveVector) 
	{		
		for(Position position : brick.getPositions())
		{
			Position movedPosition = new Position(position);
			movedPosition.y += moveVector.y;
			movedPosition.x += moveVector.x;
			
			BoundingBox movedBox = 
					new BoundingBox(movedPosition.x, movedPosition.y, Brick.getRectSize(), Brick.getRectSize());
			
			for(Brick otherBrick : _brickList)
			{
				for(Position otherPosition : otherBrick.getPositions())
				{
					if(brick != otherBrick)
					{
						BoundingBox otherBox = 
								new BoundingBox(otherPosition.x, otherPosition.y, Brick.getRectSize(), Brick.getRectSize());
							
						boolean collision = movedBox.checkCollision(otherBox);
						if(collision)
						{
							return false;
						}
					}
					
				}
				
			}
			
			// out of map/field?
			if(isBrickPositionInField(movedPosition) == false)
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean moveAttempt(Brick brick, Position singlePosition, Position moveVector)
	{
		Position movedPosition = new Position(singlePosition);
		movedPosition.y += moveVector.y;
		movedPosition.x += moveVector.x;
		
		BoundingBox movedBox = 
				new BoundingBox(movedPosition.x, movedPosition.y, Brick.getRectSize(), Brick.getRectSize());
		
		for(Brick otherBrick : _brickList)
		{
			for(Position otherPosition : otherBrick.getPositions())
			{
				//if(brick != otherBrick)
				//{
					// collision tests with same tile does not make sense
					if(otherPosition.x != singlePosition.x || otherPosition.y != singlePosition.y) 
					{
						BoundingBox otherBox = 
								new BoundingBox(otherPosition.x, otherPosition.y, Brick.getRectSize(), Brick.getRectSize());
						
						boolean collision = movedBox.checkCollision(otherBox);
						if(collision)
						{
							return false;
						}
					}
				//}
			}
			
		}
		
		// out of map/field?
		if(isBrickPositionInField(movedPosition) == false)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private boolean isBrickPositionInField(Position position)
	{
		if(position.x < _fieldStartPosition.x || (position.x + Brick.getRectSize() > _fieldStartPosition.x + _fieldWidth))
		{
			return false;
		}
		
		if(position.y + Brick.getRectSize() > _fieldStartPosition.y + _fieldHeight)
		{
			return false;
		}
		
		return true;
	}
	 
	
	/**
	 * if brick appeared we make an entry in our map
	 **/
	public void appeared(Brick brick)
	{
		_brickList.add(brick);
	}

	@Override
	public void stopped(Brick brick) 
	{
	}

	@Override
	public void firstStop(Brick brick)
	{
	}

	@Override
	public void disappeared(Brick brick)
	{
		_brickList.remove(brick);
	}

	@Override
	public boolean checkPositions(Brick brick, ArrayList<Position> positions) 
	{
		for(Position position : positions)
		{
			BoundingBox movedBox = 
					new BoundingBox(position.x, position.y, Brick.getRectSize(), Brick.getRectSize());
			
			for(Brick otherBrick : _brickList)
			{
				if(otherBrick != brick) // check each brick beside currently selected brick
				{
					for(Position otherPosition : otherBrick.getPositions())
					{
						BoundingBox otherBox = 
								new BoundingBox(otherPosition.x, otherPosition.y, Brick.getRectSize(), Brick.getRectSize());
						
						boolean collision = movedBox.checkCollision(otherBox);
						if(collision)
						{
							return false;
						}
					}
				}	
			}
			
			// out of map/field?
			if(isBrickPositionInField(position) == false)
			{
				return false;
			}
		}
		return true;
	}
}
