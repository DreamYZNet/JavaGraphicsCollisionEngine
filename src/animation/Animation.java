
package main.animation;

import main.*;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private int currentFrame = 0; //Current frame in the entire animation
	private int totalFrames = 0; //Total frames in the entire animation
	
	private ArrayList<AnimationFrame> frames = new ArrayList<AnimationFrame>(1);
	private ArrayList<CollisionBox> collisionBoxes = new ArrayList<CollisionBox>(0); //current frame
	private ArrayList<CollisionBox> allCollisionBoxes = new ArrayList<CollisionBox>(0);//entire animation
	
	//ANIMATION
	public Animation() {}	
	
	public void update() {
		currentFrame++;
		if(currentFrame > totalFrames -1) { //Loop back to 0 when done
			currentFrame = 0;
		}
		updateCollisionBoxes();
	}
	
	public Image getImage() { //IMPROVE TOO SLOW (i think)
		int atFrame = 0; //which AnimationFrame. (NOT similar to currentframe)
		int frameTotal = 0; //how many frames into animation
		while(true) {
			frameTotal += frames.get(atFrame).getLength(); //add current AnimFrames total
			if (currentFrame < frameTotal) {
				return frames.get(atFrame).getImage();
			}
			atFrame++;
		}
	}
	
	public void addAnimationFrame(Image image, int length) {
		frames.add(new AnimationFrame(image, length));
		totalFrames += length;
	}
	
	//COLLISION
	public ArrayList<CollisionBox> getCollisionBoxes() {
		return collisionBoxes;
	}
	
	public void updateCollisionBoxes() {
		collisionBoxes.clear();
		for (CollisionBox b : allCollisionBoxes) {
			if (b.getStart() <= currentFrame && currentFrame <= (b.getStart() + b.getLength()) ) {
				collisionBoxes.add(b);
			}
		}
	}
	
	public void addCollisionBox (RelativeObject parent, int start, int length
			,int x, int y, int w, int h) {
		allCollisionBoxes.add(new CollisionBox(parent, start, length, x, y, w, h));
	}
	public void addSolidCollisionBox (RelativeObject parent, int start, int length
			,int x, int y, int w, int h, boolean solid, boolean pushable) {
		allCollisionBoxes.add(new SolidCollisionBox(parent, start, length, x, y, w, h, solid, pushable));
	}
	public void addCollisionBox (RelativeObject parent, CollisionBox box) {
		box.setParentDirectly(parent);
		allCollisionBoxes.add(box);
	}
	
}
