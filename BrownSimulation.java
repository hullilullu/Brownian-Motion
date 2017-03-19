// author: Christian Beck

/**
*Simulates brownian motion in particles.
*
*/

class BrownSimulation extends Thread{
  BrownModel m;
public BrownSimulation(BrownModel m){
  this.m = m;
}


  public void run(){
    BrownTracker tracker = new BrownTracker(this.m);
    brownFrame frame = new brownFrame(this.m);
    //MAIN LOOP
    while(true){
    tracker.update();
    frame.update();
    try {
    sleep(frame.simSpeed);
  }catch(Exception e){
      System.out.println("-- MAIN LOOP ERROR --");
    }
  }

  }
  public static void main(String[] args) throws Exception{
    int amount;
    int szX = 300;
    int szY = 300;
    System.out.println("- arg 0: amount of particles(default 250)\n- arg 1: width(default 300)\n- arg 2: height(default 300 or width)");
    if (args.length == 0){
      amount = 250;
    }else if(args.length == 1){
      amount = Integer.parseInt(args[0]);
    }else if(args.length == 2){
      amount = Integer.parseInt(args[0]);
      szX = Integer.parseInt(args[1]);
      szY = Integer.parseInt(args[1]);
    }else{
      amount = Integer.parseInt(args[0]);
      szX = Integer.parseInt(args[1]);
      szY = Integer.parseInt(args[2]);
    }
    BrownModel m = new BrownModel(szX,szY,amount);
    new BrownSimulation(m).start();
  }
}
