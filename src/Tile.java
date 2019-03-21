
package main;

import java.awt.Image;

public class Tile {
	
	private Image image;
	
	private int id;
	private String imagePath = "";
	private boolean solid;
	
	//CONSTRUCTS
	
	public Tile() {}
	public Tile(int id) {
		this.id = id;
	}
	
	public Tile(int id, boolean solid) {
		this.id = id;
		this.solid = solid;
	}
	
	//GETTERS & SETTERS
	
	public int getId() 			{	return id;			}
	public boolean isSolid()	{	return solid;		}
	public String getImagePath(){	return imagePath;	}
	public Image getImage()		{	return image;		}
	
	public void setId(int id) 				{ this.id = id;			}
	public void setSolid (boolean b) 		{ solid = b; 			}
	public void setImagePath(String path) 	{ this.imagePath = path;}
	public void setImage(Image image) 		{ this.image = image;	}
	
}
