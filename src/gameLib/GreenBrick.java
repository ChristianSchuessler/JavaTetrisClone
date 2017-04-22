package gameLib;

import java.awt.Color;
import java.util.ArrayList;

public class GreenBrick extends Brick
{
	public GreenBrick(Position startPosition)
	{
		ArrayList<Position> positions = new ArrayList<Position>();
	
		Position position0 = new Position(startPosition.x, startPosition.y);
		Position position1 = new Position(startPosition.x, startPosition.y + 1 * _rectSize);
		Position position2 = new Position(startPosition.x, startPosition.y + 2 * _rectSize);
		Position position3 = new Position(startPosition.x, startPosition.y + 3 * _rectSize);
		
		positions.add(position0);
		positions.add(position1);
		positions.add(position2);
		positions.add(position3);
		
		initialize(positions, Color.GREEN);
	}
	
	public void twiddle()
	{
		super.twiddle();
		
		switch(_twiddleState)
		{
			case 2: // case 2 and 0 looks the same(no break in case 2)
			{
			}
			case 0:
			{
				ArrayList<Position> newPositions = new ArrayList<Position>();
				Position startPosition = _positions.get(0);
				newPositions.add(new Position(startPosition.x, startPosition.y));
				newPositions.add(new Position(startPosition.x, startPosition.y + 1 * _rectSize));
				newPositions.add(new Position(startPosition.x, startPosition.y + 2 * _rectSize));
				newPositions.add(new Position(startPosition.x, startPosition.y + 3 * _rectSize));
				
				setNewPositionsSafe(newPositions);
				break;
			}
			
			case 3: // case 3 and 1 looks the same(no break in case 2)
			{
			}
			
			case 1:
			{
				ArrayList<Position> newPositions = new ArrayList<Position>();
				Position startPosition = _positions.get(0);
				newPositions.add(new Position(startPosition.x, startPosition.y));
				newPositions.add(new Position(startPosition.x + 1 * _rectSize, startPosition.y));
				newPositions.add(new Position(startPosition.x + 2 * _rectSize, startPosition.y));
				newPositions.add(new Position(startPosition.x + 3 * _rectSize, startPosition.y));

				setNewPositionsSafe(newPositions);
				break;
			}
		}
	}
}
