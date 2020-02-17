import greenfoot.*;

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key. 'z' releases a proton wave.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 1.1
 */
public class Rocket extends SmoothMover
{
    private static final int gunReloadTime = 5;         // The minimum delay between firing the gun.

    private int reloadDelayCount;               // How long ago we fired the gun the last time.
    private boolean hasPickup;
    private double speed;
    private final double THRUST = .3;
    
    private GreenfootImage rocket = new GreenfootImage("rocket.png");    
    private GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust.png");
    private GreenfootImage rocketWithShield = new GreenfootImage("rocketWithShield.png");
    private GreenfootImage baseImage = rocket;

    /**
     * Initialise this rocket.
     */
    public Rocket()
    {
        reloadDelayCount = 5;
        addToVelocity(new Vector(180, 0.1));
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        checkKeys();
        reloadDelayCount++;
        move();
        ignite(Greenfoot.isKeyDown("up"), THRUST);
        ignite(Greenfoot.isKeyDown("down"), -THRUST);
        setShield(isTouching(Pickup.class));
    }
    
    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        if (Greenfoot.isKeyDown("space")) 
        {
            fire();
        }
        
        if (Greenfoot.isKeyDown("left")) 
        {
            turn(-5);
        }
        
        if (Greenfoot.isKeyDown("right")) 
        {
            turn(5);
        }
        
        
      
    }
    
    /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {
            Bullet bullet = new Bullet (getVelocity(), getRotation());
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
    
    private void ignite(boolean boosterOn, double thrust)
    {
        if(thrust < 0)
        {
            thrust /= 3;
            speed += thrust;
            if(speed < .1)
            {
                thrust = 0;
            }
        }
        
        if(boosterOn)
        {
            setImage(rocketWithThrust);
            addToVelocity(new Vector(getRotation(), thrust));
            speed += thrust;
        }
        else
        {
            setImage(baseImage);
        }
    }
    
    private void setShield(boolean shieldOn)
    {
        if(shieldOn)
        {
            setImage(rocketWithShield);
            baseImage = rocketWithShield;
        }
        else
        {
            setImage(baseImage);
        }
    }

    
}