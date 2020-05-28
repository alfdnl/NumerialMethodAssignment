package com.aliff.bow.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import com.aliff.bow.framework.KeyInput;
import com.aliff.bow.framework.ObjectId;
import com.aliff.bow.framework.Texture;
import com.aliff.bow.object.Block;
import com.aliff.bow.object.Player;
import com.aliff.bow.object.Player2;
import com.aliff.bow.object.cameraTest;
//import com.aliff.bow.object.cameraTest;


public class Game extends Canvas implements Runnable 
{
	
	private static final long serialVersionUID = -7076831927274369622L;
	
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH,HEIGHT;
	
	private BufferedImage level = null;
	private BufferedImage mountain = null;

	
	
	//Object
	Handler handler;
	Camera cam;
	static Texture tex;
	
	private void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		File imageFile = new File("C:\\Users\\Aliff\\eclipse-workspace\\Bow and Arrow\\res\\level.png");
		File imageFile2 = new File("C:\\Users\\Aliff\\eclipse-workspace\\Bow and Arrow\\res\\rsz_mountain.png");
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage(imageFile);
		mountain = loader.loadImage(imageFile2);
		
		handler = new Handler();
		
		cam = new Camera(0,0);
		
		LoadImageLevel(level);
		
//		handler.addObject(new Block(100,100, ObjectId.Block));
//		handler.addObject(new Block(59,100, ObjectId.Block));
//		handler.addObject(new Player(100,Game.HEIGHT-100,handler, ObjectId.Player));
		handler.addObject(new cameraTest(100,Game.HEIGHT-100,handler, ObjectId.cameraTest));
		
//		handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
				
	}

	public void run()
	{
		init();
		this.requestFocus();
		System.out.println("Thread has begun");
		long lastTime = System.nanoTime();
		
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		long timer = System.currentTimeMillis();
		
		int updates = 0;
		int frames = 0;
		
		while(running){
			 long now = System.nanoTime();
			 delta += (now - lastTime) / ns;
			 lastTime = now;
			 while(delta >= 1){
				  tick();
				  updates++;
				  delta--;
			 }
		 render();
		 frames++;
		   
		 if(System.currentTimeMillis() - timer > 1000){
			  timer += 1000;
			  System.out.println("FPS: " + frames + " TICKS: " + updates);
			  frames = 0;
			  updates = 0;
		 	}
		}
	}
	
	

	private void tick() {
		handler.tick();
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId()==ObjectId.Player) {
//				System.out.println("Player detected");
				cam.tick(handler.object.get(i));
			}
			if(handler.object.get(i).getId()==ObjectId.cameraTest) {
//				System.out.println("Player detected");
				cam.tick(handler.object.get(i));
			}
		}
		
		
	}

	private void render() {
		// Render Graphical Stuff
		BufferStrategy bs = this.getBufferStrategy(); // 
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g; 
		
		/////////////////////////////////////
		
		
		// Draw here
		g.setColor(new Color(25, 191, 224));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		g2d.translate(cam.getX(), -cam.getY());// begin of cam
			
		for (int i = 0; i < mountain.getWidth()*2; i += mountain.getWidth()) {
			g.drawImage(mountain, i, 130,this);
		}
			handler.render(g);
			
		
		g2d.translate(cam.getX(), -cam.getY());// end of cam
		/////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	private void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("Width: "+ w + "Height: "+ h);
		
		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx,yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 16) & 0xff; 
				int blue = (pixel) & 0xff;
				
				if (red == 255 && green == 255 && blue == 255) {
//					System.out.println("white");
					handler.addObject(new Block(xx*32,yy*32,0, ObjectId.Block));
				}
				if (red == 195 && green == 195 && blue == 195) {
//					System.out.println("grey");
					handler.addObject(new Block(xx*32,yy*32,1, ObjectId.Block));
				}
				
				if (red == 0 && green == 0 && blue == 255) {
					System.out.println("Bluee");
					handler.addObject(new Player(xx*32,yy*32,handler, ObjectId.Player));
				}
				
				if (red == 127 && green == 127 && blue == 127) {
					System.out.println("red");
					handler.addObject(new Player2(xx*32,yy*32,handler, ObjectId.Player2));
				}
				
			}			
		}
	}
	

	public static void main(String args[]) {
		new Window(800,600,"Bow and Arrow", new Game());
	}

	public static Texture getInstance() {
		// TODO Auto-generated method stub
		return tex;
	}
}
