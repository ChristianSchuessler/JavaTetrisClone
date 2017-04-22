package gameLibTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameLib.Brick;
import gameLib.BrickField;
import gameLib.Position;
import gameLib.RedBrick;

public class BrickFieldTest 
{
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidConstructor()
	{
		// too small
		BrickField field = new BrickField(new Position(0, 0), 400,Brick.getRectSize());
	}
	
	@Test
	public void testCollsionWithBottomLine()
	{
		BrickField field = new BrickField(new Position(0, 0), 400, 4 * Brick.getRectSize());
		
		RedBrick brick = new RedBrick(new Position(10, 0));
		brick.registerMoveListener(field);
		
		int lastY = brick.getPositions().get(3).y;
		
		// in 10 000 iterations we should reach the line for sure
		int iterationCount = 10000;
		int counter = 0;
		for(; counter < iterationCount; ++counter) 
		{
			brick.moveAttempt();
			int currentY = brick.getPositions().get(3).y;
			
			if(currentY == lastY) // somewhere we reached we line..
			{
				break;
			}
			lastY = currentY;	
		}
		
		if(counter == iterationCount)
		{
			assertFalse(true); // collision was not detected
		}
		
		assertTrue(lastY == 5 * Brick.getRectSize() - 1);
	}
	
	@Test
	public void testCollsionWithOtherBrick()
	{
		BrickField field = new BrickField(new Position(0, 0), 400, 5 * Brick.getRectSize());
		
		RedBrick firstBrick = new RedBrick(new Position(10, 0));
		RedBrick secondBrick = new RedBrick(new Position(100, 0));
		firstBrick.registerMoveListener(field);
		secondBrick.registerMoveListener(field);
		
		int lastY = firstBrick.getPositions().get(3).y;
		
		// in 10 000 iterations we should reach the line for sure
		int iterationCount = 10000;
		int counter = 0;
		for(; counter < iterationCount; ++counter) 
		{
			firstBrick.moveAttempt();
			int currentY = firstBrick.getPositions().get(3).y;
			
			if(currentY == lastY) 
			{
				break;
			}
			lastY = currentY;	
		}
		
		if(counter == iterationCount)
		{
			assertFalse(true); // collision was not detected
		}
		
		assertTrue(lastY == 99);
	}
	
	@Test
	public void testCollsionWithOtherBrickShouldBeNoCollision()
	{
		BrickField field = new BrickField(new Position(0, 0), 400, 10 * Brick.getRectSize());
		
		RedBrick firstBrick = new RedBrick(new Position(20, 0));
		RedBrick secondBrick = new RedBrick(new Position(60, 100));
		firstBrick.registerMoveListener(field);
		secondBrick.registerMoveListener(field);
		
		int lastY = firstBrick.getPositions().get(3).y;
		
		// in 10 000 iterations we should reach the line for sure
		int iterationCount = 10000;
		int counter = 0;
		for(; counter < iterationCount; ++counter) 
		{
			firstBrick.moveAttempt();
			int currentY = firstBrick.getPositions().get(3).y;
			
			if(currentY == lastY) 
			{
				break;
			}
			lastY = currentY;	
		}
	
		assertTrue(lastY == 10 * Brick.getRectSize() - 1);
	}
	
	
}
