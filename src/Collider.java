
package main;

import java.util.ArrayList;
import main.animation.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Collider {
	
	
	public Collider() {
		
	}

	public void update (ArrayList<GameObject> objects) {
		
		//Iterate through possible objects
		for (int i = 0; i < objects.size(); i++) {
			for (int j = 0; j < i; j++) {
				
				ArrayList<CollisionBox> collisionBoxes = objects.get(i).getCollisionBoxes();
				ArrayList<CollisionBox> collisionBoxes2 = objects.get(j).getCollisionBoxes();
				
				//Iterate through possible collisions
				for (int n = 0; n < collisionBoxes.size(); n++) {
					for (int m = 0; m < collisionBoxes2.size(); m++) {
						
						CollisionBox box1 = collisionBoxes.get(n);
						CollisionBox box2 = collisionBoxes2.get(m);
						
						//Check if the boxes collide 
						if (checkCollision(box1, box2)) {
							
							//Do solid collision stuff
							if (box1 instanceof SolidCollisionBox && box2 instanceof SolidCollisionBox){
								solidCollision((SolidCollisionBox)box1, (SolidCollisionBox)box2);
							}
							
						}
						
					}
				}//end iterate collisions
			}
		}//end iterate objects
	}
	
	//Returns true if boxes overlap
	private boolean checkCollision(CollisionBox box1, CollisionBox box2) {
		Rectangle shape1 = (Rectangle)box1.getShape();
		Rectangle shape2 = (Rectangle)box2.getShape();
		if (box1.getGX() < box2.getGX()+shape2.getWidth()
		&& box1.getGX()+shape1.getWidth() > box2.getGX()
		&& box1.getGY() < box2.getGY()+shape2.getHeight()
		&& box1.getGY()+shape1.getHeight() > box2.getGY()) {
		 	return true;
		}else{
			return false;
		}
	}
	
	//Do pushing n stuff (to keep objects from going through other objects)
	private void solidCollision(SolidCollisionBox box1, SolidCollisionBox box2) {
		if (box2.isSolid() && box1.isPushable() || box1.isSolid() && box2.isPushable()){
			
			int[] angleAndSide = getAngleAndSide(box1, box2);
			int angle = angleAndSide[0];
			int side = angleAndSide[1];
			
			boolean case1 = box1.isPushable() && box2.isSolid();
			boolean case2 = box2.isPushable() && box1.isSolid();
			
			if (case1 && case2) {
				translateSolid(box1, angle, -side/2);
				translateSolid(box2, angle, side/2);
			}else if (case1) {
				translateSolid(box1, angle, -side);
			}else if (case2) {
				translateSolid(box2, angle, side);
			}
			box1.collide(box2, angle);
			box2.collide(box1, wallAngle(angle+180));
			
		}
	}
	
	//Keeps angle within 0-359
	private int wallAngle(int angle) {
		while (angle < 0) {
			angle += 360;
		}
		while (angle > 359) {
			angle -= 360;
		}
		return angle;
	}
	
	private int[] getAngleAndSide(CollisionBox box1, CollisionBox box2) {
		Rectangle shape1 = (Rectangle)box1.getShape();
		Rectangle shape2 = (Rectangle)box2.getShape();
		int sideL = box2.getGX()+(int)shape2.getWidth() - box1.getGX(); //+
		int sideR = box1.getGX()+(int)shape1.getWidth() - box2.getGX(); //-
		int sideU = box2.getGY()+(int)shape2.getHeight() - box1.getGY(); //+
		int sideD = box1.getGY()+(int)shape1.getHeight() - box2.getGY(); //-=
		
		int angle = 0;
		int side = sideR;
		if (sideU < side) {
			angle = 90;
			side = sideU;
		}
		if (sideL < side) {
			angle = 180;
			side = sideL;
		}
		if (sideD < side) {
			angle = 270;
			side = sideD;
		}
		int[] angleAndSide = new int [] {angle, side};
		return angleAndSide;
	}
	
	private void translateSolid (SolidCollisionBox box, int angle, int distance) {
		switch (angle) {
			case 0:
				box.getParent().translate(distance, 0);
			break;
			case 90:
				box.getParent().translate(0, -distance);
			break;
			case 180:
				box.getParent().translate(-distance, 0);
			break;
			case 270:
				box.getParent().translate(0, distance);
			break;
		}
	}
	
	
}
			