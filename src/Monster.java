
package main;

//Monster is any kind of game creature, including the player and other npcs

public class Monster extends GameObject {
	
	private int hp;
	
	public Monster() {
		hp = 5;
	}	
	
	public void die() {
		hp = 0;
	}
	
	public void takeDamage(int damage) {
		hp -= damage;
		if (hp <= 0) {
			die();
		}
	}
	
}
