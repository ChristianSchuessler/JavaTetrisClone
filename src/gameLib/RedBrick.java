package gameLib;

import java.awt.Color;
import java.util.ArrayList;

public class RedBrick extends Brick
{
	public RedBrick(Position startPosition)
	{
		ArrayList<Position> positions = new ArrayList<Position>();
	
		Position position0 = new Position(startPosition.x, startPosition.y);
		Position position1 = new Position(startPosition.x + _rectSize, startPosition.y);
		Position position2 = new Position(startPosition.x, startPosition.y + _rectSize);
		Position position3 = new Position(startPosition.x + _rectSize, startPosition.y + _rectSize);
		
		positions.add(position0);
		positions.add(position1);
		positions.add(position2);
		positions.add(position3);
		
		initialize(positions, Color.RED);
	}
}
