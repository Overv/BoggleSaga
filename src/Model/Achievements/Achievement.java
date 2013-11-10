package Model.Achievements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 27/10/13
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class Achievement{
    private String name;
    private boolean achieved;
    private BufferedImage img;
    private boolean isEndgameCondition;
    private AchievementCondition condition;
    
    protected Achievement(String name, boolean isEndgameCondition, String imgPath, AchievementCondition condition){
        this.name = name;
        this.achieved = false;
        this.isEndgameCondition = isEndgameCondition;
        this.condition = condition;
        
        if(imgPath == null){
        	imgPath = "";
        }
        
        //load image
        try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			System.err.println("Could not load image: " + imgPath);
			//empty image
			img = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
		}
    }

    //protected methods for calculation
    protected boolean isAchieved(){
    	return achieved;
    }
    
    protected boolean calculate(){
    	achieved = condition.calculate();
    	return achieved;
    }
    
    //public methods to get
    
    public String getName(){
        return name;
    }

    public BufferedImage getImage(){
    	return img;
    }
    
    public boolean isEndgameCondition(){
        return isEndgameCondition;
    }
}
