
package main;

import main.animation.*;

public class Player extends Platformer {
	
	private double moveSpeed = 0;
	private double maxMoveSpeed = 8;
	private double moveAccel = 3;
	private double moveBrake = 3;
	private boolean leftKey = false;
	private boolean rightKey = false;
	private boolean lastKeyRight = false; //false = left
	
	public Player() {
		
		super();
		
	}
	
	@Override
	public void update() {
		
		translate((int)moveSpeed, 0);
		if (leftKey || rightKey) {
			move();
		}else{
			moveSpeed = moveSpeed/moveBrake;
		}
			
		super.update();
		
		//Warp player above stage if below stage
		if (getGY() > 500) {
			translate(0, -520);
		}
		
	}
	
	public void die() {
		setY(110);
		setX(50);
	}
	
	public void move() {
		if (lastKeyRight == false) {
			moveSpeed -= moveAccel;
		}else if (lastKeyRight == true){
			moveSpeed += moveAccel;
		}
		if (moveSpeed < -maxMoveSpeed)
			moveSpeed = -maxMoveSpeed;
		else if (moveSpeed > maxMoveSpeed)
			moveSpeed = maxMoveSpeed;
	}
	
	public void moveLeft() {
		leftKey = true;
		lastKeyRight = false;
	}
	
	public void moveRight() {
		rightKey = true;
		lastKeyRight = true;
	}
	public void stopLeft() {
		leftKey = false;
		if (rightKey)
			lastKeyRight = true;
	}
	public void stopRight() {
		rightKey = false;
		if (leftKey)
			lastKeyRight = false;
	}
	
}