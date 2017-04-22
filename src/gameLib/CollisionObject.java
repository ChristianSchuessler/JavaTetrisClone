package gameLib;

import java.util.ArrayList;

import gameLib.BoundingBox;

public class CollisionObject 
{
	private ArrayList<BoundingBox> _boxes;
	
	public CollisionObject()
	{
		_boxes = new ArrayList<BoundingBox>();
	}
	
	public void addBoundingBox(BoundingBox box)
	{
		_boxes.add(box);
	}
	
	public void clear()
	{
		_boxes.clear();
	}
	
	public ArrayList<BoundingBox> getBoxes()
	{
		return _boxes;
	}	
	
	public boolean checkCollsion(CollisionObject other)
	{
		for(BoundingBox firstBox : _boxes)
		{
			for(BoundingBox secondBox : other.getBoxes())
			{
				if(firstBox.checkCollision(secondBox))
				{
					return true;
				}
			}
		}
		return false;
	}
}
