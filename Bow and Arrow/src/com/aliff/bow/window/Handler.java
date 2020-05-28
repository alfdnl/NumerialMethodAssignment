package com.aliff.bow.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.aliff.bow.framework.GameObject;
import com.aliff.bow.framework.ObjectId;
import com.aliff.bow.object.Block;

public class Handler 
{

	public LinkedList<GameObject> object =  new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick() 
	{
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.tick(object);
			
		}
	}
	
	public void render(Graphics g) 
	{
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.render(g);
			
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
//	public void createLevel() {
//		
//		//Left Wall
//		for (int yy = 0; yy < Game.WIDTH+32; yy+=32) {					
//			addObject(new Block(0,yy,ObjectId.Block));
//		}
//		
//		//Floor
//		for (int xx = 0; xx < Game.WIDTH*2; xx+=32) {		
//			addObject(new Block(xx,Game.HEIGHT-32,ObjectId.Block));
//		}
//				
//		// Platform
//		for (int xx = 150; xx < Game.WIDTH-150; xx+=32) {					
//			addObject(new Block(xx,425,ObjectId.Block));
//		}
//	}
	
}
