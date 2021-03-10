package absyn;

public class SelectionStmt extends Statement {
  public Expression ifE;
  public Statement thenS;
  public Statement elseS;

  public SelectionStmt( int row, int col, Expression ifE, Statement thenS, Statement elseS) {
    this.row = row;
    this.col = col;
    this.ifE = ifE;
    this.thenS = thenS;
    this.elseS = elseS;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
