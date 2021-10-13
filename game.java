
package launcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class game implements Runnable {
    
    private display display;
    public int height,width;
    public String title;
    
    private Thread thread;
    private boolean running=false;
    
    private BufferStrategy bs;
    private Graphics g;
    
            
    
    
    
    
    public game(String title, int width, int height){
        this.width=width;
        this.height=height;
        this.title=title;
        
    }
    
    private void init(){
        display = new display (title, width, height);
        
    }
    
     int x,y;
     double X,Y;
     double v0=110, alpha=80;
     double a =  Math.toRadians(alpha);
     double t=0;
     
      
     
     
     int rx,ry;
      
     private void target(){
        rx=(int) 510;
        ry=(int) 510;
      
     }
     
     private void tick(){
         
         
        Y=5*t*t-v0*Math.sin(a)*t+630;
        X=Math.cos(a)*v0*t+20;
        t+=0.1;
        y=(int)Y;
        x=(int)X;
        
    }
    
    public void render(){
        
        bs= display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        g= bs.getDrawGraphics();
        //clear screan
        g.clearRect(0, 0, width, height);
        //draw here
        
        g.fillOval(x, y, 20, 20);
        g.drawRect(rx,ry,40,40);
     if((x > rx-15 && x < rx+15) && (y>ry-15 && y < ry +15) ){
        
            t-=0.1;
            
        }
        
      // end drawing
        bs.show();
        g.dispose();
    }
    
    
    
    
    public void run(){
        
        init();
        target();
        
        int fps =100;
        double timepertick =1000000000/fps;
        double delta=0;
        long now;
        long lasttime=System.nanoTime();
        while(running){
            now=System.nanoTime();
            delta+=(now-lasttime)/timepertick;
            lasttime=now;
            if(delta>=1){
            tick();
            render();
            delta--;
            }
        }
        stop();
    }

    public synchronized void start(){
        if(running)
            return;
        running =true;
        thread =new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running)
            return;
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
