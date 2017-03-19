// author: Christian Beck
import java.util.LinkedList;


/**
*Hosts a number of Particle objects and handles updates etc.
*/
class BrownModel{
  private int w, h;
  private LinkedList<Particle> Particles;
  private boolean[][] grid;

  public BrownModel(int w, int h, int amount){
    this.grid = new boolean[w][h];
    for (int i1 = 0; i1 < this.grid.length;i1++){
      for (int i2 = 0; i2 < this.grid[i1].length;i2++){
        this.grid[i1][i2] = false;
      }
    }
    this.w = w;
    this.h = h;
    Particles = new LinkedList<Particle>();
    for (int i = 0; i < amount; i++){
      Particles.add(new Particle(w, h));
    }
  }

  public int getW(){
    return this.w;
  }

  public int getH(){
    return this.h;
  }

  public LinkedList<Particle> getParticles(){
    return Particles;
  }

  private void checkGrid(Particle p){
    int x = (int) p.x;
    int y = (int) p.y;
    for (int i1 = -1; i1 < 2;i1++){
      for(int i2 = -1; i2 < 2; i2++){
        if (this.grid[x+i1][y+i2]){
          p.moving = false;
          this.grid[x][y] = false;
        }
      }

    }
  }

  public void update(){
    for(Particle p: this.getParticles()){
      if (p.moving == true){
        p.randomMove(3.00);
        boolean c = false;
        if (p.x < 2){
          p.x = 1;
          p.moving = false;
        }else if(p.x > this.w-2){
          p.x = this.w-2;
          p.moving = false;
        }
        if(p.y < 2 ){
          p.y = 1;
          p.moving = false;
        } else if(p.y > this.h-2){
          p.y = this.h-2;
          p.moving = false;
        }
        if (p.moving){
          checkGrid(p);
        }
        if(!p.moving){
            this.grid[(int) p.x][(int) p.y] = true;
        }

      }

/*
      else{

        for(Particle p2: this.getParticles()){
          if (p2.moving == true){
            double distanceSq = (p.x-p2.x)*(p.x-p2.x)+(p.y-p2.y)*(p.y-p2.y);
            if (distanceSq<7.0){
              p2.moving = false;
            }
      }
      }

  }*/

}
}
}
