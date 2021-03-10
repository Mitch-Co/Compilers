package absyn;

public class MulOP extends Absyn {
  public String value;

  public MulOP( int row, int col, String value ) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
