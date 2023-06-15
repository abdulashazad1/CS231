/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.util.Random;

public class SocialAgentSimulation {
    public static void main(String[] args) throws InterruptedException {
        // Setting width and height
        int w=500;
        int h=500;
        // If command line arguments were used, use those
        if(args.length>1) {
          w=Integer.parseInt(args[0]);
          h=Integer.parseInt(args[1]);
        }
    
        //  Creating a landscape
        Landscape scape=new Landscape(w, h);
    
        // Number of agents
        final int N=200;
        
        Random gen = new Random();
        int radiusOfSensitivity=30;
    
        // Create N agents on landscape scape
        for(int i=0;i<N;i++) {
            scape.addAgent(new SocialAgent(gen.nextDouble()*(scape.getWidth() - 20),
			gen.nextDouble() * (scape.getHeight() - 20), radiusOfSensitivity));
        }
    
        // Create and paint a GUI
        LandscapeDisplay display=new LandscapeDisplay(scape);
        display.repaint();
    
        // Update and repaint 200 times
        for(int i=0; i<200; i++) {
          scape.updateAgents();
          display.repaint();
          Thread.sleep(10);
        }
      }
}
