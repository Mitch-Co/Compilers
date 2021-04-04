package absyn;

public class IterationStmt extends Statement {
  public Expression e;
  public Statement s;

  public IterationStmt( int row, int col, Expression e, Statement s ) {
    this.row = row;
    this.col = col;
    this.e = e;
    this.s = s;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
