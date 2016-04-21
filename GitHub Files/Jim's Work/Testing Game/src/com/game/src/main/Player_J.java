package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player
{
	private double x; //x coordinate of player
	private double y; //y coordinate of player
	
	boolean punch = false;
	boolean second = false;
	
	private double velX;
	private double velY;
	
	private SpriteSheet ss;
	
	// Images for each animation
	private BufferedImage[] walkingRight = new BufferedImage[6];
	private BufferedImage[] walkingLeft = new BufferedImage[6];
	private BufferedImage[] standing = new BufferedImage[2];
	private BufferedImage[] standing2 = new BufferedImage[2];
	private BufferedImage[] punching = new BufferedImage[4];
	private BufferedImage[] punching2 = new BufferedImage[4];
	private BufferedImage[] jump = new BufferedImage[2];
	private BufferedImage[] jump2 = new BufferedImage[2];
	
	// These are animation states
	private Animator walkRight;
	private Animator walkLeft;
	private Animator idleRight;
	private Animator idleLeft;
	private Animator punchRight;
	private Animator punchLeft;
	private Animator jumpLeft;
	private Animator jumpRight;
	
	// This is the actual animation
	private Animator animation;
	
	public Player(int x, int y, Game game)
	{
		this.x=x;
		this.y=y;
		
		
		ss = new SpriteSheet(game.getSpriteSheet());
		
		standing[0] = ss.grabImage(1, 1, 117, 109);
		standing[1] = ss.grabImage(2, 1, 117, 109);
		
		standing2[0] = ss.grabImage(8, 3, 117, 109);
		standing2[1] = ss.grabImage(7, 3, 117, 109);
		
		walkingRight[0] = ss.grabImage(3, 1, 117, 109);
		walkingRight[1] = ss.grabImage(4, 1, 117, 109);
		walkingRight[2] = ss.grabImage(5, 1, 117, 109);
		walkingRight[3] = ss.grabImage(6, 1, 117, 109);
		walkingRight[4] = ss.grabImage(7, 1, 117, 109);
		walkingRight[5] = ss.grabImage(8, 1, 117, 109);
		
		walkingLeft[0] = ss.grabImage(6, 3, 117, 109);
		walkingLeft[1] = ss.grabImage(5, 3, 117, 109);
		walkingLeft[2] = ss.grabImage(4, 3, 117, 109);
		walkingLeft[3] = ss.grabImage(3, 3, 117, 109);
		walkingLeft[4] = ss.grabImage(2, 3, 117, 109);
		walkingLeft[5] = ss.grabImage(1, 3, 117, 109);
		
		punching[0] = ss.grabImage(2, 2, 117, 109);
		punching[1] = ss.grabImage(3, 2, 117, 109);
		punching[2] = ss.grabImage(4, 2, 117, 109);
		punching[3] = ss.grabImage(5, 2, 117, 109);
		
		punching2[0] = ss.grabImage(7, 4, 117, 109);
		punching2[1] = ss.grabImage(6, 4, 117, 109);
		punching2[2] = ss.grabImage(5, 4, 117, 109);
		punching2[3] = ss.grabImage(4, 4, 117, 109);
		
		jump[0] = ss.grabImage(7, 1, 117, 109);
		jump[1] = ss.grabImage(8, 1, 117, 109);
		
		jump2[0] = ss.grabImage(2, 3, 117, 109);
		jump2[1] = ss.grabImage(1, 3, 117, 109);
		
		
		idleRight = new Animator(standing, 20); // creates new idle animation - higher number means longer delay
		idleLeft = new Animator(standing2, 20);
		walkRight = new Animator(walkingRight, 8); //creates new walking right animation
		walkLeft = new Animator(walkingLeft, 8);
		punchRight = new Animator(punching, 8);
		punchLeft = new Animator(punching2, 8);
		jumpRight = new Animator(jump, 20);
		jumpLeft = new Animator(jump2, 20);
		animation = idleLeft;
	}
	
	public void tick()
	{
		if(x>x+velX)
		{
			animation = walkLeft;
		}
		if(x<x+velX)
		{
			animation = walkRight;
		}
		if(y>y+velY)
		{
			if(animation == idleLeft)
			{
				animation = jumpLeft;
			}
			else if(animation == idleRight)
			{
				animation = jumpRight;
			}
		}
		if(x==x+velX)
		{
			if(punch == true)
			{
				if(animation == idleLeft)
				{
					animation = punchLeft;
				}
				else if(animation == idleRight)
				{
				animation = punchRight;
				}
			}
			else if(animation==walkRight)
			{
				animation = idleRight;
				
			}
			else if(animation==walkLeft)
			{
				animation = idleLeft;
			}
		}
		x=x+velX;
		y=y+velY;
		animation.update();
	}
	public void render(Graphics g)
	{
		//g.drawImage(player, (int)(x), (int)y, null);
		g.drawImage(animation.getSprite(), (int)x, (int)y, null);
		animation.start();
		//g.drawImage(animation2.getSprite(), (int)x+117, (int)y, null);
		//animation2.start();
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public void setX(double x)
	{
		this.x=x;
	}
	public void setY(double y)
	{
		this.y=y;
	}
	public void setVelX(double velX)
	{
		this.velX=velX;
	}
	public void setVelY(double velY)
	{
		this.velY=velY;
	}
	public void setPunch(boolean punch)
	{
		this.punch=punch;
	}
	public void setSecond(boolean second)
	{
		this.second=second;
	}
}