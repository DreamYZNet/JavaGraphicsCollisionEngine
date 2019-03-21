
package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import main.animation.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Stage extends GameObject {
	
	private Tile[] tiles;
	
	private ArrayList<CollisionBox> collisionBoxes = new ArrayList<CollisionBox>(0);
	
	private int width;
	private int height;
	private int tileWidth;
	private int tileHeight;
	
	private int[][] layout;
	
	public Stage() {
		tiles = new Tile[9];
		tiles[0] = new TileReader().getTileAt(null);
		tiles[1] = new TileReader().getTileAt("data/tile001.txt");
		tiles[2] = new TileReader().getTileAt("data/tile002.txt");
		tiles[3] = new TileReader().getTileAt("data/tile003.txt");
		tiles[4] = new TileReader().getTileAt("data/tile004.txt");
		tiles[5] = new TileReader().getTileAt("data/tile005.txt");
		tiles[6] = new TileReader().getTileAt("data/tile006.txt");
		tiles[7] = new TileReader().getTileAt("data/tile007.txt");
	}
	
	public void init () {
		
		//Retrieves collision
		for (int i = 0; i < layout.length; i++) {
			for (int j = 0; j < layout[0].length; j++) {
				
				if (tiles[layout[i][j]].isSolid()) {
					SolidCollisionBox box = new SolidCollisionBox(this, 0, 0, tileWidth*j, tileHeight*i, tileWidth, tileHeight, true, false);
					collisionBoxes.add(box);
				}
				
			}
		}
		
	}
	
	
	public void draw (Graphics g) {
		
		super.draw(g);//draws background
		for (int i = 0; i < layout.length; i++) {
			for (int j = 0; j < layout[0].length; j++) {
				
				g.drawImage( tiles[layout[i][j]].getImage()
						, getGX() + tileWidth*j, getGY() + tileHeight*i, null);
				//Collision boxes currently not being drawn due to aesthetic reasons
				/*Graphics2D g2 = (Graphics2D)g;
				for (CollisionBox box : getCollisionBoxes()) {
					Rectangle shape = new Rectangle((Rectangle)box.getGlobalShape());
					g2.setColor(Color.RED);
					g2.draw(shape);
				}*/
			}
		}
		
	}
	
	public void setLayout (int[][] layout) {
		this.layout = layout;
	}
	
	public String toString () {
		String string = "";
		for (int y = 0; y < height; y++) {	
			for (int x = 0; x < width; x++) {
				string += layout[y][x] + " ";
			}
			string += "\n";
		}
		return string;
	}
	
	public ArrayList<CollisionBox> getCollisionBoxes() {
		return collisionBoxes;
	}
	
	public int getWidth ()	{ return width;	}
	public int getHeight ()	{ return height;}
	
	public void setWidth (int width)		{ this.width = width;		}
	public void setHeight (int height)		{ this.height = height;		}
	public void setTileWidth (int width)	{ this.tileWidth = width;	}
	public void setTileHeight (int height)	{ this.tileHeight = height;	}
	
}
