package gameLib;

import java.awt.Color;
import java.util.ArrayList;

public class YellowBrick extends Brick
{
	public YellowBrick(Position startPosition)
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		
		Position position0 = new Position(startPosition.x + _rectSize, startPosition.y + _rectSize);
		Position position1 = new Position(startPosition.x, startPosition.y + _rectSize);
		Position position2 = new Position(startPosition.x + 2*_rectSize, startPosition.y + _rectSize);
		Position position3 = new Position(startPosition.x + _rectSize, startPosition.y);
		
		positions.add(position0);
		positions.add(position1);
		positions.add(position2);
		positions.add(position3);
		
		initialize(positions, Color.YELLOW);
	}
	
	@Override
	public void twiddle()
	{
		super.twiddle();
		
		switch(_twiddleState)
		{
			case 0:
			{
				ArrayList<Position> newPositions = new ArrayList<Position>();
				Position startPosition = _positions.get(0);
				newPositions.add(new Position(startPosition.x, startPosition.y));
				newPositions.add(new Position(startPosition.x - _rectSize, startPosition.y));
				newPositions.add(new Position(startPosition.x + _rectSize, startPosition.y));
				newPositions.add(new Position(startPosition.x, startPosition.y - _rectSize));
				
				setNewPositionsSafe(newPositions);
				break;
			}
				
			case 1:
			{
				ArrayList<Position> newPositions = new ArrayList<Position>();
				Position startPosition = _positions.get(0);
				newPositions.add(new Position(startPosition.x, startPosition.y));
				newPositions.add(new Position(startPosition.x - _rectSize, startPosition.y));
				newPositions.add(new Position(startPosition.x +_rectSize, startPosition.y));
				newPositions.add(new Position(startPosition.x, startPosition.y + _rectSize));
				
				setNewPositionsSafe(newPositions);
				break;
			}
				
			case 2:
			{
				ArrayList<Position> newPositions = new ArrayList<Position>();
				Position startPosition = _positions.get(0);
				newPositions.add(new Position(startPosition.x, startPosition.y));
				newPositions.add(new Position(startPosition.x, startPosition.y - _rectSize));
				newPositions.add(new Position(startPosition.x, startPosition.y + _rectSize));
				newPositions.add(new Position(startPosition.x - _rectSize, startPosition.y));

				setNewPositionsSafe(newPositions);
				break;
			}
			
			case 3:
			{
				ArrayList<Position> newPositions = new ArrayList<Position>();
				Position startPosition = _positions.get(0);
				
				newPositions.add(new Position(startPosition.x, startPosition.y));
				newPositions.add(new Position(startPosition.x, startPosition.y - _rectSize));
				newPositions.add(new Position(startPosition.x, startPosition.y + _rectSize));
				newPositions.add(new Position(startPosition.x + _rectSize, startPosition.y));
				
				setNewPositionsSafe(newPositions);
				break;
			}
		}
	}
}
