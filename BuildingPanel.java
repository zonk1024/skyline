/*
 * Chris Jacobs (cjacobs2)
 * RandomSkyline
 * Feb 28, 2011
 * CS111B - TTh 11 AM - 1 PM
 * Assignment: K3 and K4
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BuildingPanel extends JPanel {
  private static final long serialVersionUID = 1L; // asked for by eclipse
  private Random gene = new Random(); // random object for random values
  private final int WIDTH = 1580; // width of app
  private final int HEIGHT = 800; // height of app
  private int maxBuildBuff = 21; // max val added to min for buffer size
  private int minBuildBuff = 0; // buffer minimum size
  private int maxBuildWidth = 71; // max val added to min for build width
  private int minBuildWidth = 30; // min build width
  // max val added to min building height
  private int maxBuildHeight = (int)(HEIGHT*.6);
  private int minBuildHeight = 20; // min value of building height
  private int maxBuildMult = 10; // max building mult added to min
  private int minBuildMult = 1; // min building mult
  private int starMult = 3; // quick access var for num of stars
  
  public BuildingPanel() {
    // BuildingPanel constructor
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.black);
  }
  
  private int[][] buildBuildings() {
    // creates values to use for the buildings
    // gives about 50:50 odds that 1st building is on the left edge
    int first = gene.nextBoolean() ? 0 : 1;
    int tot = WIDTH; // used to stay in assigned space
    int cur = 0; // keep track of how many times while runs
    // create build array to store/pass values
    int[][] build = new int[(int)(WIDTH/(minBuildBuff+minBuildWidth))+1][5];
    // while there is room to add a building... add one
    while (tot>0) {
      build[cur][0] = (gene.nextInt(maxBuildBuff)+minBuildBuff)*first; // Buffer
      build[cur][1] = gene.nextInt(maxBuildWidth)+minBuildWidth; // Width
      build[cur][2] = gene.nextInt(maxBuildHeight)+minBuildHeight; // Height
      build[cur][3] = gene.nextInt(maxBuildMult)+minBuildMult; // Mult
      tot -= build[cur][0]+build[cur][1]; // update tot
      first = 1; // we're now done with you...
      cur++; // update cur
    }
    build[0][4] = cur; // pack index size in a convenient spot
    return build; // send it back
  }
  
  public void miniSky(int x, int y, int wide, int tall, Color color,
                      Graphics page) {
    // logo thingy
    page.setColor(color);
    page.fillRect(x+wide*0/5+2, y+tall*3/6, wide/5-4, tall*3/6);
    page.fillRect(x+wide*1/5+2, y+tall*4/6, wide/5-4, tall*2/6);
    page.fillRect(x+wide*2/5+2, y+tall*1/6, wide/5-4, tall*5/6);
    page.fillRect(x+wide*3/5+2, y+tall*5/6, wide/5-4, tall*1/6);
    page.fillRect(x+wide*4/5+2, y+tall*2/6, wide/5-4, tall*4/6);
  }
  
  public void finishDraw(Graphics page) {
    // finishing touches
    page.setColor(Color.black);
    page.fillRect(0, HEIGHT-50, WIDTH, HEIGHT); // bottom of window
    page.setColor(Color.gray);
    page.fillRect(WIDTH/2-50, HEIGHT-40, 100, 30); // name plate
    page.setColor(new Color(191, 0, 0));
    page.drawString("Chris' Skyline", WIDTH/2-40, HEIGHT-20); // title
    // left miniSky
    miniSky((WIDTH/2-25)/2-25, HEIGHT-40, 50, 30, new Color(191, 0, 0), page);
    // right miniSky
    miniSky(((WIDTH/2+25)+WIDTH)/2-25, HEIGHT-40, 50, 30, Color.gray, page);
  }
  
  public void paintComponent(Graphics page) {
    super.paintComponent(page);
    // add sky
    Sky sky = new Sky(WIDTH, HEIGHT, (gene.nextInt(500)+501)*starMult);
    // draw sky
    sky.draw(page); // draw sky
    int[][] valArray = buildBuildings(); // fetch random values for builds
    Building[] build = new Building[valArray[0][4]]; // array size
    int xCur = 0; // start x cursor at 0
    for(int i = 0; i<valArray[0][4]; i++) { // valArray[0][4] is array size
      xCur += valArray[i][0]; // add buffer to x cursor
      if(xCur<WIDTH) { // only render if on page
        // create building
        build[i] =
                   new Building(xCur, HEIGHT-50-valArray[i][2], valArray[i][1],
                                valArray[i][2], valArray[i][3]);
        // pass random values down to the windows
        build[i].windows.setWinHei(gene.nextInt(8)+3);
        build[i].windows.setWinWid(gene.nextInt(5)+2);
        build[i].windows.setXBuf(gene.nextInt(3)+3);
        build[i].windows.setYBuf(gene.nextInt(3)+4);
        // randomly pick a rule to render the bricks with
        int[] ruleList = {30, 90, 110};
        int which = gene.nextInt(3);
        build[i].bricks.setRule(ruleList[which]);
        xCur += valArray[i][1]; // add width to x cursor
        if(build[0].getX()==0) { // if 1st build is at x=0
          if(gene.nextBoolean()) { // 50:50 shot to use mode 2 if x=0
            build[0].windows.rendMode = 2; // special rendering mode on
          }
        }
        build[i].draw(page); // draw fresh building
      }
    }
    finishDraw(page); // stamp of approval the bottom
  }
}