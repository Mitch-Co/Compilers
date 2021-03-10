package absyn;

public class CompoundStmt extends Statement {
  public DecList LocalDec;
  public StatementList sl;

  public CompoundStmt( int row, int col, DecList LocalDec, StatementList sl) {
    this.row = row;
    this.col = col;
    this.LocalDec = LocalDec;
    this.sl = sl;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
