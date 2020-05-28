package com.aliff.bow.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.aliff.bow.framework.GameObject;
import com.aliff.bow.framework.ObjectId;
import com.aliff.bow.framework.Texture;
import com.aliff.bow.window.Game;
import com.aliff.bow.window.Handler;

public class Player extends GameObject {
	
	private float width = 48, height = 96;
	
	private float gravity = 0.1f;
	private final float MAX_SPEED = 10;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		if (falling || jumping)
		{
//			System.out.println("Falling is true");
			velY+= gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 40;
					velY=0;
					}
				
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY=0;
					falling=false;
					jumping=false;
				}else 
					falling = true;	

				//Right
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;					
					}
				
				//Left
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;					
					}
			}
		}
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.drawImage(tex.player[0],(int)x, (int)y,48,96,null);
		
//		Graphics2D g2d = (Graphics2D) g;
		
//		g.setColor(Color.red);
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsTop());
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),(int) ((int)y+(height/2)),(int)(width/2),(int)(height/2));
	}
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),(int)y,(int)(width/2),(int)(height/2));
	}
	
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int) ((int)x+width-5),(int)y+5,(int)(5),(int)(height-10));
	}
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y+5,(int)(5),(int)(height-10));
	}
	

}
