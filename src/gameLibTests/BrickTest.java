package gameLibTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import gameLib.Brick;
import gameLib.IBrickMoveListener;
import gameLib.Position;

public class BrickTest
{
	public class BrickListerMockUp implements IBrickMoveListener
	{
		@Override
		public boolean moveAttempt(Brick brick, Position moveVector)
		{
			currentBrick = brick;
			currentDistance = moveVector.y;
			
			return returnValue;
		}
		
		@Override
		public void appeared(Brick brick) 
		{
		}
		
		public int currentDistance = -1;
		Brick currentBrick;
		boolean returnValue = false;
		
		@Override
		public void stopped(Brick brick)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void firstStop(Brick brick) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void disappeared(Brick brick) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean checkPositions(Brick brick, ArrayList<Position> positions) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean moveAttempt(Brick brick, Position singlePosition, Position moveVector) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	@Test
	public void testDefaultConstructor() 
	{
		Brick brick = new Brick();
		assertEquals(0, brick.getPositions().size());
	}
	
	@Test
	public void testOtherConstructor()
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(10, 10));
		
		Brick brick = new Brick(positions, Color.BLUE);
		assertEquals(1, brick.getPositions().size());
		
		Position pos = brick.getPositions().get(0);
		assertEquals(10, pos.x);
		assertEquals(10, pos.y);
	}

	@Test
	public void testRegisterMoveListenerMovingAllowed()
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(10, 10));
		Brick brick = new Brick(positions, Color.BLUE);
		
		BrickListerMockUp brickListener = new BrickListerMockUp();
		brick.registerMoveListener(brickListener);
		brick.moveAttempt();
		
		assertTrue(brickListener.currentBrick == brick);
		assertTrue(brickListener.currentDistance != -1);
		
		assertTrue(brick.getPositions().get(0).y == 10);
	}
	
	@Test
	public void testRegisterMoveListenerMovingNotAllowed()
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(10, 10));
		Brick brick = new Brick(positions, Color.BLUE);
		
		BrickListerMockUp brickListener = new BrickListerMockUp();
		brickListener.returnValue = true;
		brick.registerMoveListener(brickListener);
		brick.moveAttempt();
		
		assertTrue(brickListener.currentBrick == brick);
		assertTrue(brickListener.currentDistance != -1);
		
		assertTrue(brick.getPositions().get(0).y == 10);
	}
}
