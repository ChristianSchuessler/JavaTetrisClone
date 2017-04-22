package gameLib;

import gameLib.GreenBrick;
import gameLib.BlueBrick;
import gameLib.RedBrick;

public class BrickFactory 
{
	private int _numberOfBricks;
	private int _dropAreaWidth;
	private int _xOffset;
	
	// drop area means the width within the bricks can be created 
	// by the factory 
	public BrickFactory(int dropAreaWidth, int xOffset)
	{
		_numberOfBricks = 3;
		_dropAreaWidth = dropAreaWidth;
		_xOffset = xOffset;
	}
	
	public Brick getBrick() throws Exception
	{
		int brickIndex = (int) (Math.random() * _numberOfBricks);
		int dropPosition = (int) (Math.random() * (_dropAreaWidth / Brick.getRectSize()));
		
		// floor to a valid drop position
		dropPosition =  dropPosition * (Brick.getRectSize()) + _xOffset;
		
		Brick brick;
		
		switch(brickIndex)
		{
		case 0:
			brick = new GreenBrick(new Position(dropPosition, 0));
			break;
		case 1:
			brick = new RedBrick(new Position(dropPosition, 0));
			break;
		case 2:
			brick = new BlueBrick(new Position(dropPosition, 0));
			break;
		default:
			throw new Exception("It seems that something went wrong by generating a proper brick index");
		}
		
		return brick;
	}
}
