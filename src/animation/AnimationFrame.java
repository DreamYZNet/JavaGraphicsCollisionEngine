
package main.animation;

import java.awt.Image;

public class AnimationFrame {
	
	private Image image;
	private int length;
	
	public AnimationFrame(Image image, int length) {
		this.image = image;
		this.length = length;
	}	
	
	public int getLength() {
		return length;
	}
	
	public Image getImage() {
		return image;
	}
	
}
