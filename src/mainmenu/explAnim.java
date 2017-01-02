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
public class explAnim extends Transition{
    
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
    
    public explAnim(Image im, ImageView imv){

        indim[0]=new Rectangle2D(0,957,125,35);
        
        indim[1]=new Rectangle2D(0,892,125,40);
        
        indim[2]=new Rectangle2D(0,832,125,49);
        
        indim[3]=new Rectangle2D(0,776,125,48);
        
        indim[4]=new Rectangle2D(0,722,125,47);
        
        indim[5]=new Rectangle2D(0,667,125,48);
        
        indim[6]=new Rectangle2D(0,613,125,47);
        
        indim[7]=new Rectangle2D(0,558,125,47);
        
        indim[8]=new Rectangle2D(0,502,125,47);
        
        indim[9]=new Rectangle2D(0,446,125,48);
        
        indim[10]=new Rectangle2D(0,391,125,48);
        
        indim[11]=new Rectangle2D(0,338,125,47);
        
        indim[12]=new Rectangle2D(0,282,125,48);
        
        indim[13]=new Rectangle2D(0,226,125,48);
        
        indim[14]=new Rectangle2D(0,172,125,48);
        
        indim[15]=new Rectangle2D(0,117,125,47);
        
        indim[16]=new Rectangle2D(0,61,125,48);
        
        indim[17]=new Rectangle2D(0,0,125,55);
        
        indim[18]=new Rectangle2D(0,0,5,5);
                
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
