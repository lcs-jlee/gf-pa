
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyTank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyTank extends Actor
{
    /**
     * Act - do whatever the MyTank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int reload = 60;
    private  int life = 15;
    
    
    public void act() 
    {
        movement();
        reload++;
        fire();
        checkIfDamaged();
        String life1 = Integer.toString(life);
        getWorld().showText("Life:",25,20);
        getWorld().showText(life1, 70, 20);
        
    } 

    private void movement()
    {
        
        if (Greenfoot.isKeyDown("left")) 
        {
            setRotation(getRotation() - 3);
        }

        if (Greenfoot.isKeyDown("right")) 
        {
            setRotation(getRotation() + 3);
        }

        if (Greenfoot.isKeyDown("up")) 
        {
            move(3);
        }

        if (Greenfoot.isKeyDown("down")) 
        {
            move(-3);
        }
    }

    public void changeLifeLevel(int by)
    {
        this.life = this.life + by;

    } 

    
    public int returnLife()
    {
        return life;
    }

    private void fire()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            if (reload > 1) 
            {
                Bullet newBullet = new Bullet(getRotation());
                double x = Math.cos(getRotation()*Math.PI/180) * 34;
                double y = Math.sin(getRotation()*Math.PI/180) * 34;
                
                getWorld().addObject(newBullet, getX()+(int)Math.round(x), getY()+(int)Math.round(y));
                reload = 0;
            }     
        }
    }

    private void checkIfDamaged()
    {
        if (isTouching(EnemyBullet.class))
        {
            life --;
        }
        if (isTouching(Mine.class))
        {
            life = life - 2;
        }
        if (life == 0)
        {
            Explosion newExplosion = new Explosion();
            getWorld().addObject(newExplosion, getX(), getY());
            getWorld().showText("Game Over!",500,400);
            Greenfoot.stop();
        }

    }
    public void started()
    {
        life = 15;
    }
}
