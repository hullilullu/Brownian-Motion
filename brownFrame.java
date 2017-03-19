// author: Christian Beck

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

/**
*a window that hosts a BrownView and a Panel with JSrollBars that lets you
*interact with the simulation
*/
class brownFrame extends JFrame implements AdjustmentListener{
  BrownModel m;
  JPanel panel;
  BrownView playGround;
  JScrollPane scroll;
  JButton playButton;
  boolean play;
  JScrollBar speedBar, sizeBar;
  int particleSize, simSpeed;
  int szMax = 100;

  
  public brownFrame(BrownModel m){
    this.m = m;
    initPlayground(this.m);
    initPanel();
    this.play = true;
    this.scroll = new JScrollPane(this.playGround);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    add(scroll, "Center");
    add(this.panel, BorderLayout.WEST);

    setLocation(310, 0);
    setSize(600, 600);
    setVisible(true);
  }

  /**
  *Handles the updates for each iteration
  */
  public void update(){
    if (this.play){
          this.m.update();
    }
    this.playGround.setP(this.particleSize);
    this.panel.repaint();
    this.playGround.repaint();

  }

/**
*Handles the JScrollBar objects interaction with the simulation
*/
  public void adjustmentValueChanged(AdjustmentEvent e){
    if (e.getAdjustable() == sizeBar){
    this.particleSize = sizeBar.getValue();

  }else if(e.getAdjustable() == speedBar){
    this.simSpeed = speedBar.getValue();
  }
  }

  private void initPlayground(BrownModel m){
        this.playGround = new BrownView(m);
        this.playGround.setPreferredSize(new Dimension(800, 600));
        this.playGround.setBorder(BorderFactory.createLineBorder(Color.red));
        this.playGround.setP(30);
  }

  private void initPanel(){
    this.panel = new JPanel();
    this.panel.setPreferredSize(new Dimension(150, 600));
    this.panel.setBorder(BorderFactory.createLineBorder(Color.blue));


    this.playButton = new JButton();
    this.speedBar = new JScrollBar();
    this.sizeBar = new JScrollBar();
    this.speedBar.setMaximum(200);
    this.speedBar.addAdjustmentListener(this);
    this.speedBar.setValue(100);
    this.sizeBar.setMinimum(0);
    this.sizeBar.setMaximum(this.szMax);
    this.sizeBar.addAdjustmentListener(this);
    this.sizeBar.setValue(40);
    this.panel.add(speedBar);
    this.panel.add(sizeBar);
  }



}
