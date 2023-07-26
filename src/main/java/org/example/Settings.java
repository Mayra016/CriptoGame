package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Settings extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    private static Thread thread;
    private static final int WIDTH = 526;
    private static final int HEIGHT = 828;

    private static final String name = "CriptoGame";
    private static int aps = 0;
    private static int fps = 0;

    private static JFrame window;
    boolean isRunning;
    public Settings(){
        setPreferredSize(new java.awt.Dimension(WIDTH,HEIGHT));
        window = new JFrame(this.name);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }


    public synchronized void start(){
        isRunning = true;
        Thread thread = new Thread((Runnable) this, "Graphics");
        thread.start();
    }

    public synchronized void end(){
        isRunning = false;
        try{
            thread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();;
        }
    }

@Override
    public void run(){
        final int ns_per_second = 1000000000;
        final byte aps_Obj = 60;
        final double ns_per_actualization = ns_per_second / aps_Obj;
        long reference_actualization = System.nanoTime();
        long reference_timer = System.nanoTime();
        double time_played;
        double delta = 0;

        while (isRunning) {
            final long start_loop = System.nanoTime();
            time_played = start_loop - reference_actualization;
            reference_actualization = start_loop;
            delta += time_played / ns_per_actualization;
            while(delta >= 1){
                refresh();
                delta--;
            }
            if (System.nanoTime() - reference_timer > ns_per_second){
                window.setTitle(name + " || APS = " + aps + " || FPS " + fps);
                aps = 0;
                fps = 0;
                reference_timer = System.nanoTime();
            }
        }
    }

    private void draw(){
        fps++;
    }

    public void refresh(){
        aps++;
        repaint();
    }
}
