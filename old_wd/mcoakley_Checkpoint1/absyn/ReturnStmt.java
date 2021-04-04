package absyn;

public class ReturnStmt extends Statement {
  public Expression e;

  public ReturnStmt( int row, int col, Expression e) {
    this.row = row;
    this.col = col;
    this.e = e;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
