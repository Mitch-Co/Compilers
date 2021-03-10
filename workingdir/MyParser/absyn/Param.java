package absyn;

public class Param extends Absyn {
  public TypeVal t;
  public String ID;


  public Param(int row, int col, TypeVal t, String ID) {
    this.row = row;
    this.col = col;
    this.t = t;
    this.ID = ID;

  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}