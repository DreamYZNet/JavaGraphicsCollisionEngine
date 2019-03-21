
package main;

import main.animation.*;

public class Platformer extends Monster {
	
	private double speedX;
	private double speedY;
	private double gravity = 1;
	private double jumpSpeed = 12;
	private boolean falling = true;
	
	public Platformer() {
	}
	
	public void update() {
		
		super.update();
		
		speedY += gravity;
		translate((int)speedX, (int)speedY);
		
		falling = true;
		
	}
	
	public void jump() {
		if (falling == false) {
			speedY = -jumpSpeed;
			falling = true;
		}
	}
	
	@Override
	public void collide (CollisionBox box1, CollisionBox box2) {
		
	}
	public void collide (CollisionBox box1, CollisionBox box2, int angle) {
		SolidCollisionBox sbox1 = (SolidCollisionBox)box1;
		SolidCollisionBox sbox2 = (SolidCollisionBox)box2;
		if (sbox1.isPushable() && sbox2.isSolid() && angle == 270) {
			falling = false;
			speedY = 0;
		}
	}
	
	public void setSpeedY (int speed) {
		speedY = speed;
	}
	
	public void setFalling (boolean bool) {
		falling = bool;
	}
	
}
