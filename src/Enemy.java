
package main;

public class Enemy extends Platformer {
	
	private int count = 0;
	private int maxCount = 50;
	private int jumpCount = maxCount;
	
	public Enemy() {
		
		super();
		
	}
	
	@Override
	public void update() {
			
		count++;
		
		if (count > maxCount) {
			count = 0;
		}
		if (count == jumpCount) {
			jump();
		}
		
		super.update();
		
		if (getGY() > 500) {
			translate(0, -520);
		}
		
	}
	
}
