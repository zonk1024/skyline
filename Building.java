/*
 * Chris Jacobs (cjacobs2)
 * RandomSkyline
 * Feb 28, 2011
 * CS111B - TTh 11 AM - 1 PM
 * Assignment: K3 and K4
 */

import java.awt.*;

public class Building {
  // class building for object building, made of Brick and Windows
  private int x; // x coord
  private int y; // y coord
  private int width; // width of building
  private int height; // height of building
  public Brick bricks; // brick component of building
  public Windows windows; // window component of building
  
  public Building(int theX, int theY, int wide, int tall, int multi) {
    // building constructor
    x = theX;
    y = theY;
    width = wide;
    height = tall;
    // create the building's bricks
    bricks = new Brick(x, y, width, height, multi);
    // create the buildings windows
    windows = new Windows(x, y, width, height);
  }
  
  public void draw(Graphics page) {
    // draw sub-parts
    bricks.draw(page);
    windows.draw(page);
  }
  
  public void setX(int newX) {
    // x setter
    bricks.setX(newX);
    windows.setX(newX);
    x = newX;
  }
  
  public void setY(int newY) {
    // y setter
    bricks.setY(newY);
    windows.setY(newY);
    y = newY;
  }
  
  public void setWidth(int newWidth) {
    // width setter
    bricks.setWidth(newWidth);
    windows.setWidth(newWidth);
    width = newWidth;
  }
  
  public void setHeight(int newHeight) {
    // height setter
    bricks.setHeight(newHeight);
    windows.setHeight(newHeight);
    height = newHeight;
  }
  
  public int getX() {
    // x getter
    return x;
  }
  
  public int getY() {
    // y getter
    return y;
  }
  
  public int getWidth() {
    // width getter
    return width;
  }
  
  public int getHeight() {
    // height getter
    return height;
  }
}