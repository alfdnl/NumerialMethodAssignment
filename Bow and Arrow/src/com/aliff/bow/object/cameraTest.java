package com.aliff.bow.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.aliff.bow.framework.GameObject;
import com.aliff.bow.framework.ObjectId;
import com.aliff.bow.window.Handler;

public class cameraTest extends GameObject{
//	private float width = 48, height = 96;
	private Handler handler;

	public cameraTest(int x, int y,Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
