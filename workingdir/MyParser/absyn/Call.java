package absyn;

public class Call extends Absyn {
  public String ID;
  public ArgList al;


  public Call( int row, int col, String ID, ArgList al) {
    this.row = row;
    this.col = col;
    this.ID = ID;
    this.al = al;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}