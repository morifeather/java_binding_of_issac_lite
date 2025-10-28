package ResourceObj.Enemies.Boss;

import ResourceObj.ResourceLoader;
import ResourceObj.obstacle.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SubSubSubPride extends Pride{
    // Pride Boss subclass constructor, passes parameters to parent class Pride
    public SubSubSubPride( int x,int y,int i,int toward){
        super(x,y,i,toward);
    }
    
    // Returns the fourth image (index 3) from PrideHeadImage as the head image
    @Override
    public BufferedImage getHeadImage(){
        return ResourceLoader.getInstance().PrideHeadImage.get(3);
    }
    
    // Returns null for body image, indicating no body image is used
    @Override
    public BufferedImage getBodyImage(){
        return null;
    }
    
    // Updates the location with bounce logic at screen boundaries
    @Override
    public void UpdateLocation(int targetX, int targetY, ArrayList<Obstacle> obstacles) {
        // Bounce logic on x-axis boundaries
        if(this.x+length>=419 || this.x<=50){
            this.NowXSpeed=-this.NowXSpeed;
        }
        // Bounce logic on y-axis boundaries
        if(this.y+width>=262 || this.y<=50){
            this.NowYSpeed=-this.NowYSpeed;
        }
        // Update position based on speed
        this.x += NowXSpeed;
        this.y += NowYSpeed;
        // Update head image position to match current coordinates
        this.ImageHeadx = x;
        this.ImageHeady = y;
    }
}
