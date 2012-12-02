/*
 * Chris Jacobs (cjacobs2)
 * RandomSkyline
 * Feb 28, 2011
 * CS111B - TTh 11 AM - 1 PM
 * Assignment: K3 and K4
 */

import java.awt.*;
import java.util.Random;

public class Windows {
  private int x; // x coord
  private int y; // y coord
  private int xBuf = 4; // x buffer between windows
  private int yBuf = 4; // y buffer between windows
  private int winWid = 5; // window width
  private int winHei = 8; // window height
  private int height; // height of object
  private int width; // width of object
  private int numWide = 1; // number of windows across
  private int numTall = 1; // number of windows along
  private int xOff = 1; // x offset for first window in row
  private int yOff = 1; // y offset for first window in col
  private boolean[][] winArray; // array of t/f to for window color
  private Color colorT = new Color(255, 255, 255); // light on color
  private Color colorF = new Color(0, 0, 0); // light off color
  private Random gene = new Random(); // random object
  public int rendMode = 1; // used to account for special situation
  
  public Windows(int theX, int theY, int wide, int tall) {
    // Windows constructor
    x = theX;
    y = theY;
    height = tall;
    width = wide;
  }
  
  public void draw(Graphics page) {
    // draw method
    // build array first
    buildArray();
    for(int i = 0; i<numTall; i++) { // start height loop
      for(int j = 0; j<numWide; j++) { // nest width loop
        page.setColor(colorF);
        if(winArray[i][j]) { // figure on or off
          page.setColor(colorT);
        }
        // left edge window and special case
        if(width-xOff-j*xBuf-(j+1)*winWid<0&&rendMode==2) {
          page
              .fillRect(0, (y+yOff+i*(yBuf+winHei)), winWid
                                                     -Math.abs(width-xOff-j
                                                               *xBuf-(j+1)
                                                               *winWid), winHei);
          // special case
        } else if(width-xOff-j*xBuf-(j+1)*winWid>0&&rendMode==2) {
          page.fillRect(Math.max(0, width-xOff-j*xBuf-(j+1)*winWid),
                        (y+yOff+i*(yBuf+winHei)), winWid, winHei);
          // normal case
        } else {
          
          page.fillRect(Math.max(x+xOff+j*(xBuf+winWid), 0), y+yOff+i
                                                             *(yBuf+winHei),
                        winWid, winHei);
        }
      }
    }
  }
  
  private void buildArray() {
    // creates usable array
    // figure out how many windows you can fit across
    for(int i = 0; i<width; i++) {
      if(i*xBuf+(i+1)*winWid+2>width) {
        numWide = i+(rendMode-1); // an extra one if special case
        break;
      }
    }
    // figure out how many windows you can fit along
    for(int i = 0; i<height; i++) {
      if(i*yBuf+(i+1)*winHei+2>height) {
        numTall = i;
        break;
      }
    }
    // if it isn't a special case set xOff to:
    if(rendMode==1) {
      xOff = (width-(numWide*winWid+(numWide-1)*xBuf))/2;
    } else { // if it is use this:
      xOff = (xBuf+1)/2;
    }
    // all cases use this yOff
    yOff = (height-(numTall*winHei+(numTall-1)*yBuf))/2;
    // give the array it's dimensions
    winArray = new boolean[numTall][numWide];
    for(int i = 0; i<numTall; i++) { // create along loop
      for(int j = 0; j<numWide; j++) { // nest across loop
        int togInt = gene.nextInt(3); // one in 3 chance light is on
        boolean togBool = togInt==0 ? true : false;
        winArray[i][j] = togBool; // assign it to the array
      }
    }
  }
  
  public void setX(int newX) {
    // x setter
    x = newX;
  }
  
  public void setY(int newY) {
    // x setter
    y = newY;
  }
  
  public void setXBuf(int newBuf) {
    // xBuf setter
    xBuf = newBuf;
  }
  
  public void setYBuf(int newBuf) {
    // yBuf setter
    yBuf = newBuf;
  }
  
  public void setWinHei(int newHeight) {
    // winHei setter
    winHei = newHeight;
  }
  
  public void setWinWid(int newWidth) {
    // winWid setter
    winWid = newWidth;
  }
  
  public void setHeight(int newHeight) {
    // height setter
    height = newHeight;
  }
  
  public void setWidth(int newWidth) {
    // width setter
    width = newWidth;
  }
  
  public void setColorT(Color shade) {
    // colorT setter
    colorT = shade;
  }
  
  public void setColorF(Color shade) {
    // colorF setter
    colorF = shade;
  }
  
  public int getX() {
    // x getter
    return x;
  }
  
  public int getY() {
    // y getter
    return y;
  }
  
  public int getXBuf() {
    // xBuf getter
    return xBuf;
  }
  
  public int getYBuf() {
    // yBuf getter
    return yBuf;
  }
  
  public int getWinHei() {
    // winHei getter
    return winHei;
  }
  
  public int getWinWid() {
    // winWid getter
    return winWid;
  }
  
  public int getHeight() {
    // height getter
    return height;
  }
  
  public int getWidth() {
    // width getter
    return width;
  }
  
  public Color getColorT() {
    // colorT getter
    return colorT;
  }
  
  public Color getColorF() {
    // colorF getter
    return colorF;
  }
}