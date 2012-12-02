/*
 * Chris Jacobs (cjacobs2)
 * RandomSkyline
 * Feb 28, 2011
 * CS111B - TTh 11 AM - 1 PM
 * Assignment: K3 and K4
 * This program creates a random skyline
 * with building and windows...
 * there are some stars and a moon...
 * did you find Waldo?
 */

import javax.swing.*;

public class RandomSkyline {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Skyline"); // create jframe
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new BuildingPanel()); // add crap to it
    frame.pack(); // pack it up - pack it in
    frame.setVisible(true); // let me begin
  }
}
