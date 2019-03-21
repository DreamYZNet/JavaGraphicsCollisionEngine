
package main;

import main.animation.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Color;

public class GameObject extends RelativeObject {
	
	private Animation[] animations;
	private Animation animation;
	
	public GameObject() {
		
		animation = new Animation();
		animations = new Animation[4];
		
	}
	
	//UPDATES
	public void update() {
		animation.update();
	}
	
	//DRAWS
	public void draw(Graphics g) {
		g.drawImage(getImage(), getGX(), getGY(), null);
		
		//draw collision boxes
		{	Graphics2D g2 = (Graphics2D)g;
			for (CollisionBox box : getCollisionBoxes()) {
				Rectangle shape = new Rectangle((Rectangle)box.getGlobalShape());
				shape.setSize((int)shape.getWidth() -1, (int)shape.getHeight() -1); //To match tile size visually
				g2.setColor(Color.RED);
				g2.draw(shape);
			}
		}
	}
	
	//GET IMAGE
	public Image getImage() {
		return animation.getImage();
	}
	public Animation getAnimation() {
		return animation;
	}
	
	//COLLIDE box1 = own box; box2 = collided with
	public void collide (CollisionBox box1, CollisionBox box2) {
	}
	public void collide (CollisionBox box1, CollisionBox box2, int angle) {
	}
	
	public ArrayList<CollisionBox> getCollisionBoxes() {
		return animation.getCollisionBoxes();
	}
	
	public void addCollisionBox (int start, int length, int x, int y, int w, int h) {
		animation.addCollisionBox(this, start, length, x, y, w, h);
	}
	public void addSolidCollisionBox (int start, int length, int x, int y, int w, int h, boolean solid, boolean pushable) {
		animation.addSolidCollisionBox(this, start, length, x, y, w, h, solid, pushable);
	}
	public void addCollisionBox (CollisionBox b) {
		animation.addCollisionBox(this, b);
	}
	
	public void drawCollisionBoxes(Graphics g) {
		
	}
		
}
