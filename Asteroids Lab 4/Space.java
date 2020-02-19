import greenfoot.*;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Michael Kölling
 * @version 1.10
 */
public class Space extends World
{
    private GreenfootImage background = getBackground();
    private Counter scoreCounter;
    private int startAsteroids = 3;
    private final int NUM_OF_STARS = 700;

    /**
     * Create the space and all objects within it.
     */
    public Space() 
    {
        super(600, 500, 1);
        background.setColor(Color.BLACK);
        background.fill();
        
        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        
        addAsteroids(startAsteroids);
        paintStars(NUM_OF_STARS);
        
        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 480);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
    }
    
    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }
    
    private void paintStars(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            int brightness = 120 - Greenfoot.getRandomNumber(100);
            int colorRed = brightness + Greenfoot.getRandomNumber(10);
            int colorGreen = brightness + Greenfoot.getRandomNumber(10);
            int colorBlue = brightness + Greenfoot.getRandomNumber(10);
            int size = (int) (3 * Math.random() + 1.1);
            Color color = new Color(colorRed, colorGreen, colorBlue);
            background.setColor(color);
            background.fillOval(x, y, size, size);
        }
    }
    
    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int currentScore = scoreCounter.getValue();
        addObject(new ScoreBoard(currentScore),x ,y);
    }
    
    public void updateScore(int addToScore)
    {
        scoreCounter.add(addToScore);
    }

}
