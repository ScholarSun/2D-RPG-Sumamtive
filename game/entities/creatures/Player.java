package game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Handler;
import game.entities.Entity;
import game.gfx.Animation;
import game.gfx.Assets;

public class Player extends Creature {
    //Animations
    private Animation animDown, animUp, animLeft, animRight, aRight, aLeft, aUp, aDown;
    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        // from entity class, change to player model size
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;
        
        //Animations
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);
        aRight = new Animation(500, Assets.player_aright);
        aDown = new Animation(500, Assets.player_adown);
        aUp = new Animation(500, Assets.player_aup);
        aLeft = new Animation(500, Assets.player_aleft);
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        aDown.tick();
        aUp.tick();
        aRight.tick();
        aLeft.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
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
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;
        
        //check which direction the player is attacking and move the attack rectangle accordingly
        if(handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else{
            return;
        }
        
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
        System.out.println("You lose");
    }
    
    //get move input from keymanager
    private void getInput(){
        xMove = 0;
        yMove = 0;
        attack = 0;
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
        if(handler.getKeyManager().aUp)
            attack = 1;
        if(handler.getKeyManager().aDown)
            attack = 2;
        if(handler.getKeyManager().aLeft)
            attack = 3;
        if(handler.getKeyManager().aRight)
            attack = 4;
    }

    //render graphics
    @Override
    public void render(Graphics g) {
        //center the game camera on the player
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
    //return animation frame
    private BufferedImage getCurrentAnimationFrame(){
        if(attack == 1)
            return aUp.getCurrentFrame();
        else if ( attack == 2)
            return aDown.getCurrentFrame();
        else if ( attack == 3)
            return aLeft.getCurrentFrame();
        else if( attack == 4)
            return aRight.getCurrentFrame();
        else if(xMove < 0){
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
