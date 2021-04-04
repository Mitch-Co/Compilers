package absyn;

public class SimpleExpression extends Absyn {
  public AdditiveExpression ae;
  public RelOP r;
  public AdditiveExpression ae2;

  public SimpleExpression(int row, int col, AdditiveExpression ae, RelOP r, AdditiveExpression ae2) {
    this.row = row;
    this.col = col;
    this.ae = ae;
    this.r = r;
    this.ae2 = ae2;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
