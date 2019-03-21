
/* 
   Date Last Modified: 2016 05 23

   Problem Statement:
   Create a flexible game engine with support for animations/collision/files
   Make a playable platformer demo
*/

package main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import java.util.ArrayList;

public class Main extends Applet implements Runnable, KeyListener {

	private Image image;
	private Image pImage;
	private Graphics second;
	private URL base;
	private Player player;
	private Stage stage;
	private Enemy enemy;
	private Collider collider;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>(10);

    @Override
    public void init() {
		
		//Setup window
        Frame frame = (Frame) this.getParent().getParent();
        setFocusable(true);
        setSize(640, 480);
        setBackground(Color.WHITE);
        frame.setTitle("Final Project - GameThing");
        
        //Add input
        addKeyListener(this);
        
        //Get class folder
        try { base = getDocumentBase();
		} catch (Exception e) { }
		
		//Set collider
		collider = new Collider();
		
		//Setup stage
		stage = new StageReader().getStageAt("data/level03.txt");
		gameObjects.add(stage);
		pImage = getImage(base, "data\\background01.png");
		stage.getAnimation().addAnimationFrame(pImage, 70);
		pImage = getImage(base, "data\\background02.png");
		stage.getAnimation().addAnimationFrame(pImage, 20);
		
		stage.init();
		
		//Setup player
		player = new Player();
		gameObjects.add(player);
		pImage = getImage(base, "data\\box\\01idle01.png");
		player.getAnimation().addAnimationFrame(pImage, 2);
		pImage = getImage(base, "data\\box\\01idle02.png");
		player.getAnimation().addAnimationFrame(pImage, 4);
		pImage = getImage(base, "data\\box\\01idle03.png");
		player.getAnimation().addAnimationFrame(pImage, 8);
		pImage = getImage(base, "data\\box\\01idle04.png");
		player.getAnimation().addAnimationFrame(pImage, 16);
		pImage = getImage(base, "data\\box\\01idle05.png");
		player.getAnimation().addAnimationFrame(pImage, 32);
		player.addSolidCollisionBox(0, 100, 0,0,64,64, true, true);
		
		player.setParent(stage);
		player.setY(110);
		player.setX(50);
		
		//Setup enemy
		enemy = new Enemy();
		gameObjects.add(enemy);
		pImage = getImage(base, "data\\enemy.png");
		enemy.getAnimation().addAnimationFrame(pImage, 2);
		enemy.addSolidCollisionBox(0, 100, 0,0,64,64, true, true);
		enemy.setY(30);
		enemy.setX(74);
		
    }
	
	//Starts main thread
    @Override
    public void start() {
    	
        Thread thread = new Thread(this);
        thread.start();
        
    }

	//GAME LOOP
    @Override
    public void run() {
        while (true) {
        	
        	//Update all objects
        	for (GameObject e : gameObjects) {
        		e.update();
        	}
        	//Calculate collision
        	collider.update(gameObjects);
        	
            repaint();
            try { Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    //Paint all objects
    public void paint(Graphics g) {
    	
    	for (GameObject o : gameObjects) {
    		o.draw(g);
    	}
    	
    }
    
    //Keep painting image to screen
    public void update(Graphics g) {
    	
    	if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		g.drawImage(image, 0, 0, this);
		
    }
    
    //EVENT LISTENERS
    
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
        	player.jump();
        break;
        case KeyEvent.VK_LEFT:
        	player.moveLeft();
        break;
        case KeyEvent.VK_RIGHT:
        	player.moveRight();
        break;
		}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
        	player.stopLeft();
        break;
        case KeyEvent.VK_RIGHT:
        	player.stopRight();
        break;
		}

    }
	
	@Override
	public void keyTyped(KeyEvent e) {
	    
	}
    
} 
