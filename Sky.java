/*
 * Chris Jacobs (cjacobs2)
 * RandomSkyline
 * Feb 28, 2011
 * CS111B - TTh 11 AM - 1 PM
 * Assignment: K3 and K4
 */

import java.awt.*;
import java.util.Random;

public class Sky {
  // sky object
  private int width; // app width
  private int height; // app height
  private int numStars; // number of stars to render
  private Random gene = new Random(); // random obj
  
  public Sky(int wide, int tall, int stars) {
    // Sky constructor
    width = wide;
    height = tall;
    numStars = stars;
  }
  
  public void draw(Graphics page) {
    // paint sunset
    for(int i = 0; i<height; i++) {
      page.setColor(new Color(Math.min(i/2, 192), Math.min(i/3, 127),
                              Math.min(i, 255)));
      page.drawLine(0, i, width, i);
    }
    // paint stars
    // this is split to give more stars to higher locations
    page.setColor(Color.white);
    for(int i = 0; i<(int)(numStars/2); i++) {
      page.fillRect(gene.nextInt(width), (int)(gene.nextInt(height/5)), 1, 1);
    }
    for(int i = 0; i<(int)(numStars/3); i++) {
      page.fillRect(gene.nextInt(width),
                    (int)(gene.nextInt(height/5)+height/5), 1, 1);
    }
    for(int i = 0; i<(int)(numStars/6); i++) {
      page.fillRect(gene.nextInt(width),
                    (int)(gene.nextInt(height/5)+height*2/5), 1, 1);
    }
    // paint moon
    page.setColor(new Color(240, 240, 240));
    page.fillOval(gene.nextInt(width-55)+5, gene.nextInt(height/5)+5, 50, 50);
  }
  
  public void setHeight(int newHeight) {
    // height setter
    height = newHeight;
  }
  
  public void setWidth(int newWidth) {
    // width setter
    width = newWidth;
  }
  
  public void setNumStars(int newNumStars) {
    // numStars setter
    numStars = newNumStars;
  }
  
  public int getHeight() {
    // height getter
    return height;
  }
  
  public int getWidth() {
    // width getter
    return width;
  }
  
  public int getNumStars() {
    // numStars getter
    return numStars;
  }
}
