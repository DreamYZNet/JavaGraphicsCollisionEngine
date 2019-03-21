
package main;

import main.*;
import main.animation.*;

import java.awt.Rectangle;

public class SolidCollisionBox extends CollisionBox {
	
	
	private boolean solid = false;
	private boolean pushable = false; //Solids move Pushables
	
	public SolidCollisionBox(RelativeObject parent, int start, int length 
			,int x, int y, int w, int h, boolean solid, boolean pushable) {
		super (parent, start, length, x, y, w, h);
		this.solid = solid;
		this.pushable = pushable;
	}
	
	public SolidCollisionBox(int x, int y, int w, int h, boolean solid, boolean pushable) {
		super (x, y, w, h);
		this.solid = solid;
		this.pushable = pushable;
	}
	
	public void collide (SolidCollisionBox box2) {
	}
	
	public void setSolid(boolean b)		{ solid = b;	}
	public void setPushable(boolean b)	{ pushable = b;	}
	
	public boolean isSolid()	{ return solid;		}
	public boolean isPushable() { return pushable;	}
	
}
