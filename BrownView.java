// author: Christian Beck

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

/**
*An extension of JPanel that renders the particles from a BrownModel object
*/
class BrownView extends JPanel{
  BrownModel m;
  Color backgroundColor = new Color(0,5,30);
  private int pad = 50;
  private int pSize;

public BrownView(BrownModel m){
  this.m = m;
  this.pSize = 1;
  setBackground(this.backgroundColor);
  setPreferredSize(new Dimension(this.m.getW(),this.m.getH()));
}

public void setP(int sz){
  this.pSize = (int) sz/10;
}

  /**
  *Overrides the paintcomponent of JPanel, draws the particle objects in
  *BrownModel
  */
  public void paintComponent(Graphics g){
super.paintComponent(g);
  LinkedList<Particle> selParticles = new LinkedList<Particle>();
for(Particle p: m.getParticles()){
  if (!p.selected){
    g.setColor(p.color);
    int x = this.pad+(int)p.x-this.pSize/2;
    int y = this.pad+(int)p.y-this.pSize/2;
    g.fillRect(x,y,this.pSize,this.pSize);
  }else{selParticles.add(p);}
}
for (Particle p: selParticles){
  g.setColor(p.color);
  int x = this.pad+(int)p.x-this.pSize/2;
  int y = this.pad+(int)p.y-this.pSize/2;
  g.drawLine(this.pad,y,this.m.getW()+this.pad,y);
  g.drawLine(x,this.pad,x,this.m.getH()+this.pad);
  g.fillRect(x,y,this.pSize,this.pSize);
}
  }
}
