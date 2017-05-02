package gameLib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BrickRowDeleter implements IBrickMoveListener
{
	public class Tile
	{
		Position position;
		Brick brick;		
	}
	
	private int _brickTilesInRow;
	private ArrayList<Brick> _bricks;
	private int _numberOfDeletedRows;

	public BrickRowDeleter(int fieldWidth)
	{
		_brickTilesInRow = fieldWidth / Brick.getRectSize();
		_bricks = new ArrayList<Brick>();
		_numberOfDeletedRows = 0;
	}
	
	@Override
	public boolean moveAttempt(Brick brick, Position moveVector) 
	{
		return true;
	}

	@Override
	public void appeared(Brick brick) 
	{
		_bricks.add(brick);
	}

	@Override
	public void stopped(Brick brick) 
	{
		Map<Integer, ArrayList<Tile>> tileMap = new HashMap<Integer, ArrayList<Tile>>();
		
		for(Brick currentBrick : _bricks)
		{
			// only iterate over stopped bricks
			if(currentBrick.isStopped() == false)
			{
				continue;
			}
			
			for(Position position : currentBrick.getPositions())
			{
				Integer key = position.y;
				ArrayList<Tile> tileRow = tileMap.get(position.y);
				
				if(tileRow == null)
				{
					tileRow = new ArrayList<Tile>();
					tileMap.put(key, tileRow);
				}
				
				Tile newTile = new Tile();
				newTile.brick = currentBrick;
				newTile.position = position;
				tileRow.add(newTile);		
			}
		}
		
		cleanTileMap(tileMap);
	}
	
	/*
	 * this methods checks the complete tile map and clean rows up if necessary
	 */
	private void cleanTileMap(Map<Integer, ArrayList<Tile>> tileMap)
	{
		Iterator<ArrayList<Tile>> tileRowsIt = tileMap.values().iterator();
		
		while(tileRowsIt.hasNext())
		{
			ArrayList<Tile> currentRow = tileRowsIt.next();
			
			// row is full
			if(currentRow.size() >=_brickTilesInRow)
			{
				deleteTilesInRow(currentRow);
				_numberOfDeletedRows++;
			}
		}
		
	}
	
	private void deleteTilesInRow(ArrayList<Tile> tileRow)
	{
		for(Tile tile : tileRow)
		{
			tile.brick.removePosition(tile.position);	
		}
	}
	
	public int getNumberOfDeletedRows()
	{
		return _numberOfDeletedRows;
	}

	@Override
	public void firstStop(Brick brick) 
	{
	}

	@Override
	public void disappeared(Brick brick)
	{
		_bricks.remove(brick);
	}

	@Override
	public boolean checkPositions(Brick brick, ArrayList<Position> positions) 
	{
		return true;
	}

	@Override
	public boolean moveAttempt(Brick brick, Position singlePosition, Position moveVector) 
	{
		return true;
	}

}
