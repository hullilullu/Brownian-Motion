// author: Christian Beck

import java.awt.Color;

class Particle{
  double x, y;
  boolean moving = true;
  Color color;
  boolean selected = false;

  /**
  *Constructor that randomizes position
  *@param width width of the area with possible starting positions
  *@param height height of area with possible starting positions
  */
  public Particle(int width, int height){
    this.x = Math.random()*width;
    this.y = Math.random()*height;
    this.color = Color.white;
  }

  /**
  *Contructor wich sets a starting position
  */
  public Particle(double x,double y){
    this.x = x;
    this.y = y;
  }

  /**
  *Changes the postion of the particle a set distance in a random direction
  *@param dL the distance to move
  */
  public void randomMove(double dL){
    double rad = Math.random()*2*Math.PI;
    this.x += Math.cos(rad)*dL;
    this.y += Math.sin(rad)*dL;
  }
}
