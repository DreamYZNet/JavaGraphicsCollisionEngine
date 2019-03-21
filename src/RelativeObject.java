
package main;

// 1. This class is the basis for objects with an x & y location
// 2. Allows parenting

public class RelativeObject {
	
	private RelativeObject parent = null;
	
	private int x;
	private int y;
	
	//TRANSLATE
	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	//PARENT STUFF
	public void setParent (RelativeObject parent) {
		x -= parent.getGX();
		y -= parent.getGY();
		this.parent = parent;
	}
	public void setParentDirectly (RelativeObject parent) {
		this.parent = parent;
	}
	public void unParent() {
		x = getGX();
		y = getGY();
		this.parent = null;
	}
	public RelativeObject getParent() {
		return parent;
	}
	
	//X & Y METHODS
	
	public int getX ()  { return getLX();}
	public int getY ()  { return getLY();}
	public int getLX () { return x;}
	public int getLY () { return y;}
	public int getGX () {
		if (parent != null) {
			return x + parent.getGX();
		}else{
			return x;
		}
	}
	public int getGY () {
		if (parent != null) {
			return y + parent.getGY();
		}else{
			return y;
		}
	}
	
	public void setX(int x)	{ setLX(x);  }
	public void setY(int y) { setLY(y);  }
	public void setLX(int x){ this.x = x;}
	public void setLY(int y){ this.y = y;}
	public void setGX(int x){
		this.x = x - parent.getGX();
	}
	public void setGY(int y) {
		this.y = y - parent.getGY();
	}
	
}
