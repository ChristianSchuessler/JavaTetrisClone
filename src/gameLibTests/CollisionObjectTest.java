package gameLibTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import gameLib.BoundingBox;
import gameLib.CollisionObject;

public class CollisionObjectTest 
{
	@Test
	public void testAddBoundingBox() 
	{
		CollisionObject collisionObject = new CollisionObject();
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		
		collisionObject.addBoundingBox(box);
		
		ArrayList<BoundingBox> boxes = collisionObject.getBoxes();
		assertEquals(1, boxes.size());
		BoundingBox addedBox = boxes.get(0);
		assertTrue(addedBox == box);
	}
	
	@Test
	public void testClear() 
	{
		CollisionObject collisionObject = new CollisionObject();
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		
		collisionObject.addBoundingBox(box);
		collisionObject.clear();
		
		ArrayList<BoundingBox> boxes = collisionObject.getBoxes();
		assertEquals(0, boxes.size());
	}
	
	@Test
	public void testCollisionSimpleCaseNoCollision() 
	{
		BoundingBox firstBox = new BoundingBox(0, 0, 2, 2);
		CollisionObject firstCollisionObject = new CollisionObject();
		firstCollisionObject.addBoundingBox(firstBox);
		
		BoundingBox secondBox = new BoundingBox(3, 3, 2, 2);
		CollisionObject secondCollisionObject = new CollisionObject();
		secondCollisionObject.addBoundingBox(secondBox);
		
		boolean collision = 
				firstCollisionObject.checkCollsion(secondCollisionObject);
		
		assertFalse(collision);
	}
	
	@Test
	public void testCollisionSimpleCaseCollision() 
	{
		BoundingBox firstBox = new BoundingBox(0, 0, 2, 2);
		CollisionObject firstCollisionObject = new CollisionObject();
		firstCollisionObject.addBoundingBox(firstBox);
		
		BoundingBox secondBox = new BoundingBox(1, 1, 2, 2);
		CollisionObject secondCollisionObject = new CollisionObject();
		secondCollisionObject.addBoundingBox(secondBox);
		
		boolean collision = 
				firstCollisionObject.checkCollsion(secondCollisionObject);
		
		assertTrue(collision);
	}
	
	@Test
	public void testCollisionComplexCaseCollision() 
	{
		CollisionObject firstCollisionObject = new CollisionObject();
		{
			BoundingBox firstBox = new BoundingBox(1, 1, 2, 4);
			BoundingBox secondBox = new BoundingBox(3, 3, 2, 2);
			firstCollisionObject.addBoundingBox(firstBox);
			firstCollisionObject.addBoundingBox(secondBox);
		}
		
		CollisionObject secondCollisionObject = new CollisionObject();
		{
			BoundingBox firstBox = new BoundingBox(4, 3, 2, 2);
			BoundingBox secondBox = new BoundingBox(4, 5, 2, 2);
			secondCollisionObject.addBoundingBox(firstBox);
			secondCollisionObject.addBoundingBox(secondBox);
		}

		boolean collision = firstCollisionObject.checkCollsion(secondCollisionObject);
		
		assertTrue(collision);
	}
}
