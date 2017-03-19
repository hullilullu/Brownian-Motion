import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
*A table that let's you track Particle objects in a BrownModel object.
*/
class BrownTracker extends JFrame implements TableModelListener{
  BrownModel m;
  JTable Table;
  Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.orange,
    Color.pink, Color.cyan, Color.magenta, Color.lightGray, new Color(255,136,170)};
  Object[][] testData = new Object[10][5];


  public BrownTracker(BrownModel m){
    this.m = m;
    String[] testCol = {"index","x","y","moving","tracking"};
    this.testData = initData(this.testData);
    DefaultTableModel defModel = new DefaultTableModel(this.testData, testCol);
    defModel.addTableModelListener(this);
    this.Table = initTable(defModel);

    add(new JScrollPane(this.Table));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300,300);
    setVisible(true);
  }

  public static void main(String[] args){
    BrownModel m = new BrownModel(300,300,250);
    new BrownTracker(m);
  }

  public void update(){
    DefaultTableModel mod = (DefaultTableModel)this.Table.getModel();
    for (int i = 0; i < this.Table.getModel().getRowCount(); i++){
      Particle p = this.m.getParticles().get(i);
      mod.setValueAt((double) p.x, i, 1);
      mod.setValueAt((double) p.y, i, 2);
      mod.setValueAt(p.moving, i, 3);
    }
  }

  /**
  *Makes a JTable object with the correct renderer for each column
  */
  public JTable initTable(DefaultTableModel defModel){
    JTable retTable = new JTable(defModel){
      @Override
      public Class getColumnClass(int column){
        switch(column){
          case 0:
          return Integer.class;
          case 1:
          return Double.class;
          case 2:
          return Double.class;
          case 3:
          return String.class;
          default:
          return Boolean.class;
        }
      }
    };
    return retTable;
  }

  /**
  *Initiates the data for the table
  */
  private Object[][] initData(Object[][] data){
    for (int i = 0; i < data.length; i++){
      data[i][0] = i;
      data[i][1] = 0.0;
      data[i][2] = 0.0;
      data[i][3] = false;
      data[i][4] = false;
    }
    return data;
  }

  public void tableChanged(TableModelEvent e){
    int row = e.getFirstRow();
    int pIndex = (int)this.Table.getModel().getValueAt(e.getFirstRow(),0);
    Particle p = this.m.getParticles().get(pIndex);
    boolean on = (boolean) this.Table.getModel().getValueAt(e.getFirstRow(),4);
    if (on){
      p.color = this.colors[pIndex];
      p.selected = true;
    }else{
      p.color = Color.white;
      p.selected = false;
    }

    //System.out.println("tableChanged!" +"   "+ this.Table.getValueAt(e.getFirstRow(),0));

  }
}
