/*
 * Chris Jacobs (cjacobs2)
 * RandomSkyline
 * Feb 28, 2011
 * CS111B - TTh 11 AM - 1 PM
 * Assignment: K3 and K4
 */

import java.awt.Color;
import java.lang.Math;
import java.awt.Graphics;
import java.util.Random;

public class Brick {
  private int x; // x coord
  private int y; // y coord
  private int xMax; // x boundary of object brick
  private int yMax; // y boundary of object brick
  private int nWid; // number of windows across to render
  private int nHei; // number of windows along to render
  private int width; // width of object brick
  private int height; // height of object brick
  private int mult; // mult of object brick
  private int rule = 30; // cellular automation rule used to fill array
  private boolean[][] pixelArray; // array to contain the brick pattern
  private Color colorT; // color corresponding with a true value in the array
  private Color colorF; // color corresponding with a false value in the array
  private Random gene = new Random(); // random object for fetching random vals
  
  public Brick(int theX, int theY, int wide, int tall, int scale) {
    // brick constructor
    x = theX;
    xMax = theX+wide;
    y = theY;
    yMax = theY+tall;
    height = tall;
    width = wide;
    mult = scale;
    // generate random true color
    colorT = new Color(gene.nextInt(256), gene.nextInt(256), gene.nextInt(256));
    // generate random false color
    colorF = new Color(gene.nextInt(256), gene.nextInt(256), gene.nextInt(256));
  }
  
  private void fillArray() {
    // populates pixelArray with true/false values that
    // correspond with the rule number
    // first row randomly seeded
    nHei = height/mult+1; // reduce unnecessary work
    nWid = width/mult+1; // reduce unnecessary work
    pixelArray = new boolean[nHei][nWid]; // give pixelArray dimension
    for(int i = 0; i<nWid; i++) { // start width loop to populate first row
      pixelArray[0][i] = gene.nextBoolean(); // random value
    }
    // following rows populated using rule's value when
    // passed to eval() to dictate the next row
    // which dictates which?
    // case a |- case b -| case c
    // XX000X XXX000 00XXX0 X000XX
    // ^00000 0^0000 000^00 00000^
    // 000000 000000 000000 000000
    for(int i = 1; i<nHei; i++) { // start height loop
      for(int j = 0; j<nWid; j++) { // start width loop
        boolean a; // above left
        boolean b; // above
        boolean c; // above right
        if(j==0) { // case a
          a = pixelArray[i-1][nWid-1];
          b = pixelArray[i-1][0];
          c = pixelArray[i-1][1];
        } else if(j==nWid-1) { // case c
          a = pixelArray[i-1][j-1];
          b = pixelArray[i-1][j];
          c = pixelArray[i-1][0];
        } else { // case b
          a = pixelArray[i-1][j-1];
          b = pixelArray[i-1][j];
          c = pixelArray[i-1][j+1];
        }
        // convert to oct val
        int d = 0;
        d += a ? 4 : 0;
        d += b ? 2 : 0;
        d += c ? 1 : 0;
        // use eval(d) to dictate new boolean val
        pixelArray[i][j] = eval(d);
      }
    }
  }
  
  private boolean eval(int oct) {
    // receives val from to check against cellular
    // automation rule defined by int rule
    // create rule book to check against
    boolean[] ruleBook = new boolean[8];
    // create helper to encode ruleBook's rules
    int helper = rule;
    // loop from 7 to 1
    // idk why the rules switch orientation, but they do...
    // rule 30 (000XXXX0) should be rule 225 (XXX0000X)
    // i could have gone with 255-rule, but meh.
    for(int i = 7; i>0; i--) {
      // decode. assign accordingly
      if(rule-Math.pow(2, i)<0) {
        ruleBook[i] = false;
        helper = helper-(int)(Math.pow(2, i));
      } else {
        ruleBook[i] = true;
      }
    }
    return ruleBook[oct]; // using the oct-th rule of the ruleBook
  }
  
  public void draw(Graphics page) {
    // draw method
    // populate array first
    fillArray();
    for(int i = 0; i<nHei; i++) { // start height loop
      for(int j = 0; j<nWid; j++) { // nest width loop
        if(pixelArray[i][j]) { // check pixelArray to tell if colorT or colorF
          page.setColor(colorT);
        } else {
          page.setColor(colorF);
        }
        // draw the window
        // use Math.min to keep the object in it's assigned range
        page.fillRect(x+j*mult, y+i*mult, Math.min(mult, xMax-(x+j*mult)),
                      Math.min(mult, yMax-(y+i*mult)));
      }
    }
  }
  
  public void setX(int newX) {
    // x setter - recalc xMax
    xMax = newX+width;
    x = newX;
  }
  
  public void setY(int newY) {
    // y setter - recalc yMax
    yMax = newY+height;
    y = newY;
  }
  
  public void setWidth(int newWidth) {
    // width setter - recalc xMax
    xMax = x+newWidth;
    width = newWidth;
  }
  
  public void setHeight(int newHeight) {
    // height setter - recalc yMax
    yMax = y+newHeight;
    height = newHeight;
  }
  
  public void setMult(int newMult) {
    // mult setter -> all calcs made using mult
    // are done during render
    mult = newMult;
  }
  
  public void setRule(int newRule) {
    // rule setter
    rule = newRule;
  }
  
  public void setColorT(Color newCol) {
    // colorT setter
    colorT = newCol;
  }
  
  public void setColorF(Color newCol) {
    // colorF setter
    colorF = newCol;
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
  
  public Color getColorT() {
    // colorT getter
    return colorT;
  }
  
  public Color getColorF() {
    // colorF getter
    return colorF;
  }
  
  public int getMult() {
    // mult getter
    return mult;
  }
  
  public int getRule() {
    // rule getter
    return rule;
  }
}