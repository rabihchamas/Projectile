/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class imageloader {
    
    public static BufferedImage loadimage(String path){
        try {
            return ImageIO.read(imageloader.class.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(imageloader.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return null;
    }
    
}
