package mainmenu;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This class controls the sprite animation of the explosion.
 *
 * @author Maksym Kolov
 * 
 */
public class explAnim1 extends Transition{
    
    /**
     * Imageview to be displayed on the screen.
     */
    
    ImageView imv;
    
    /**
     * Sprite image to be used for animation.
     */
    
    Image im;
    
    /**
     * Rectangle that controls individual images that are the part of the animation.
     */
    
    Rectangle2D[] indim=new Rectangle2D[19];
    
    /**
     * Last index in the array
     */
    
    private int lastIndex;
    
    /**
     * An int to control the picture switching in the animation.
     */
    
    int pic=0;
    
    /**
     * This constructor initializes the animation elements.
     * 
     * @param im Image to be user for animation
     * 
     * @param imv Imageview that display the animation.
     */
    
    public explAnim1(Image im, ImageView imv){

        indim[18]=new Rectangle2D(0,950,125,35);
        
        indim[17]=new Rectangle2D(0,885,125,40);
        
        indim[16]=new Rectangle2D(0,825,125,49);
        
        indim[15]=new Rectangle2D(0,769,125,48);
        
        indim[14]=new Rectangle2D(0,715,125,47);
        
        indim[13]=new Rectangle2D(0,660,125,48);
        
        indim[12]=new Rectangle2D(0,606,125,47);
        
        indim[11]=new Rectangle2D(0,551,125,47);
        
        indim[10]=new Rectangle2D(0,486,125,47);
        
        indim[9]=new Rectangle2D(0,439,125,48);
        
        indim[8]=new Rectangle2D(0,384,125,48);
        
        indim[7]=new Rectangle2D(0,331,125,47);
        
        indim[6]=new Rectangle2D(0,275,125,48);
        
        indim[5]=new Rectangle2D(0,219,125,48);
        
        indim[4]=new Rectangle2D(0,165,125,48);
        
        indim[3]=new Rectangle2D(0,110,125,47);
        
        indim[2]=new Rectangle2D(0,53,125,48);
        
        indim[1]=new Rectangle2D(0,0,125,55);
        
        indim[0]=new Rectangle2D(0,0,5,5);
                
        this.im=im;
        
        this.imv=imv;
        
        this.imv.setViewport(indim[18]);
        
        this.setInterpolator(Interpolator.LINEAR);
        
        setCycleDuration(Duration.seconds(3));
    
    }
    
    /**
     * This method controls the individual picture change based on the the speed.
     * 
     * @param frac The number from 0 to 1 that controls the re-painting of the animated element.
     */

    @Override
    protected void interpolate(double frac) {
        
        final int index = Math.min((int) Math.floor(frac * 20), 20 - 1);

        if(index != lastIndex){
        
            imv.setViewport(indim[pic]);
            pic++;
            lastIndex = index;
        
        }  
        
    }
    
}
