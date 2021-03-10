package absyn;

public class Var extends Absyn {
  public String ID;
  public Expression e;


  public Var( int row, int col, String ID, Expression e) {
    this.row = row;
    this.col = col;
    this.ID = ID;
    this.e = e;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}