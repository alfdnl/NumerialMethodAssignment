package com.aliff.bow.window;

import com.aliff.bow.framework.GameObject;

public class Camera {
	private float x,y;
	
	public Camera(float x, float y) {
		this.x= x;
		this.y =y;
	}
	
	public void tick(GameObject player) {
//		System.out.println("ID: "+ player.getId());
		x = -player.getX() + Game.WIDTH/2;
//		x--;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
