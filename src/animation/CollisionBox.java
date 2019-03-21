
package main.animation;

import main.*;

import java.awt.Rectangle;
import java.awt.Shape;

public class CollisionBox extends RelativeObject {
	
	private Shape box;
	
	private int start;
	private int length;
	
	//CONSTRUCTS
	public CollisionBox(int x, int y, int w, int h) {
		setX(x); setY(y);
		box = new Rectangle (0, 0, w, h);
	}
	public CollisionBox(RelativeObject parent, int start, int length, int x, int y, int w, int h) {
		this (x, y, w, h);
		setParentDirectly(parent);
		this.length = length;
	}
	
	//METHODS
	
	public void collide (CollisionBox box) {
		GameObject parent = (GameObject)getParent();
		parent.collide(this, box);
	}
	public void collide (CollisionBox box, int angle) {
		GameObject parent = (GameObject)getParent();
		parent.collide(this, box, angle);
	}
	
	//GETS & SETS
	
	public Shape getShape() {
		return box;
	}
	
	public Shape getGlobalShape() {
		Rectangle globalBox = new Rectangle((Rectangle)box);//Clone box
		globalBox.setLocation(getGX(), getGY());
		return globalBox;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getStart() {
		return start;
	}
	
}


