/**
 * Author: Abdullah Shahzad
 * Section: B Lab: C
 * Project 4
 * Date: 3/16/22
 */

import java.util.Random;

 public class CatSocialAgentSimulation {
    public static void main(String[] args) throws InterruptedException {
        // Sets width and height
        int w=500;
        int h=500;
        // If command line arguments were used, use those
        if(args.length>1) {
          w=Integer.parseInt(args[0]);
          h=Integer.parseInt(args[1]);
        }
    
        // Creates a landscape
        Landscape scape=new Landscape(w, h);
    
        // Number of agents
        final int N=200;
        
        Random gen = new Random();
        int radiusOfSensitivity=80;
    
        // Creates N agents on landscape scape
        for(int i=0;i<N;i++) {
          scape.addAgent(new CatSocialAgent(gen.nextDouble()*(scape.getWidth() - 20),
                gen.nextDouble() * (scape.getHeight() - 20), gen.nextInt(2), radiusOfSensitivity));
        }
    
        // Create and paint a GUI
        LandscapeDisplay display=new LandscapeDisplay(scape);
        display.repaint();
    
        // Updates and repaint 200 times
        for(int i=0; i<200; i++) {
          scape.updateAgents();
          display.repaint();
          Thread.sleep(50);
        }
      }
 }