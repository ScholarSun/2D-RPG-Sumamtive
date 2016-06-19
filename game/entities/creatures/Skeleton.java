package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import game.entities.Entity;
import game.gfx.Animation;
import game.gfx.Assets;

public class Skeleton extends Creature
{
    //Animations
	private Animation animDown, animUp, animLeft, animRight;
	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	public Skeleton(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		// from entity class, change to player model size
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		//Animations
		animDown = new Animation(500, Assets.zombie_down);
		animUp = new Animation(500, Assets.zombie_up);
		animLeft = new Animation(500, Assets.zombie_left);
		animRight = new Animation(500, Assets.zombie_right);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		//Movement
		getInput();
		move();
		// Attack
		checkAttacks();
	}
	
	private void checkAttacks(){
		//update cool downs on attack
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		//if the player cannot attack, stop
		if(attackTimer < attackCooldown)
			return;
		
		//collision bounds rectangle
		Rectangle cb = getCollisionBounds(0, 0);
		//attack rectangle ( range )
		Rectangle ar = new Rectangle();
		int arSize = 60;
		ar.width = arSize;
		ar.height = arSize;
		
		//Attack in a 1 block radius of the skeleton
		ar.x = cb.x - arSize;
		ar.y = cb.y - arSize;
		
		attackTimer = 0;
		
		//check if the player damaged anything
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				e.hurt(1);
				return;
			}
		}
		
	}
	
	//display output if the player dies
	@Override
	public void die(){
	}
	
	//get move input from random number generator
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		int v = (int) (Math.random() * 3);
		int h = (int) (Math.random() * 3);
		
		if( v == 0 )
			yMove = -speed;
		if( v == 1 )
			yMove = speed;
		if( h == 0)
			xMove = -speed;
		if( h == 2 )
			xMove = speed;
	}

	//render graphics
	@Override
	public void render(Graphics g) {
		//center the game camera on the player
		g.drawImage(getCurrentAnimationFrame(), (int) x , (int) y, width, height, null);
	}
	
	//return animation frame
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
	}
}
