/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikfinal1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
class dikdortgen
{
    public int x,y;
    public int aci=0;
    public int sag,sol;
    public dikdortgen()
    {
        x=0;y=0;
    }
    public void move()
    {
        if(sag==1)
        {
            aci+=10;
        }
        if(sol==1)
        {
            aci-=10;
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT)
        {
            sag=0;
            sol=0;
        }
        
        if(key == KeyEvent.VK_RIGHT)
        {
            sag=0;
            sol=0;
        }
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT)
        {
            sag=1;
            sol=0;
        }
        
        if(key == KeyEvent.VK_RIGHT)
        {
            sol=1;
            sag=0;
        }
    }
}
class Board extends JPanel implements ActionListener{
    
    public Timer timer;
    public int DELAY=100;
    dikdortgen d1;
    Random rnd;
    public int r,t,y;
    public int r1,t1,y1;
    
    int i=0;
    
    int derece = 10;
    
    double radyan = derece*(Math.PI/180);
    
    public Board()
    {
        d1 = new dikdortgen();
        timer = new Timer(DELAY,this);
        timer.start();
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        rnd = new Random();
        
        r = rnd.nextInt(255);
        t = rnd.nextInt(255);
        y = rnd.nextInt(255);
        
        r1 = rnd.nextInt(255);
        t1 = rnd.nextInt(255);
        y1 = rnd.nextInt(255);
    }
    
    private void doDrawing(Graphics g)
    {
        
        Graphics2D g2d = (Graphics2D) g.create();

        AffineTransform old = g2d.getTransform();
        
        //draw shape/image (will be rotated)
        g2d.rotate(Math.toRadians(d1.aci), getWidth()/2, getHeight()/2);
           
        d1.x= getWidth()/2;
        d1.y= getHeight()/2;

        g2d.setPaint(new Color(r,t,y));
        g2d.drawRect(d1.x-70, d1.y-30, 140, 60);
               
        g2d.setPaint(new Color(r1,t1,y1));
        g2d.fillRect(d1.x-69, d1.y-29, 139, 59);
        
        g2d.setTransform(old);
        
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        d1.move();
    }
    
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
            d1.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            d1.keyPressed(e);
        }
    }
  
    
}
public class GrafikFinal1 extends JFrame{
    
    public GrafikFinal1()
    {
        initUI();
    }
    private void initUI()
    {
        Board board = new Board();
        add(board);
        setTitle("GRAFİK FİNAL SORUSU -1-");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GrafikFinal1 final1 = new GrafikFinal1();
        final1.setVisible(true);
    }
    
}
