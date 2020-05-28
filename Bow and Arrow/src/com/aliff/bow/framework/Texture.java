package com.aliff.bow.framework;

import java.awt.image.BufferedImage;
import java.io.File;

import com.aliff.bow.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps, ps2;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage player_sheet2 = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[1];
	public BufferedImage[] player2 = new BufferedImage[1];
	
	public Texture () {
		
		File block_img = new File("C:\\Users\\Aliff\\eclipse-workspace\\Bow and Arrow\\res\\spriteSheet.png");
		File player_img = new File("C:\\Users\\Aliff\\eclipse-workspace\\Bow and Arrow\\res\\player.png");
		File player2_img = new File("C:\\Users\\Aliff\\eclipse-workspace\\Bow and Arrow\\res\\player2.png");
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
		block_sheet = loader.loadImage(block_img);
		player_sheet = loader.loadImage(player_img);
		player_sheet2 = loader.loadImage(player2_img);
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		ps2 = new SpriteSheet(player_sheet2);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0]= bs.grabImage(2, 3, 34, 34); // dirt
		block[1]= bs.grabImage(2, 1, 32, 32); //grass
		
		player[0]= ps.grabImage(1, 1, 32, 32); //grass
		player2[0]= ps2.grabImage(1, 1, 55, 64); //grass
	}
}
