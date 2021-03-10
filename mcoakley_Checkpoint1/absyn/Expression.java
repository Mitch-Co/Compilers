package absyn;

public class Expression extends Statement {
  public Var v;
  public Expression e;
  public SimpleExpression se;

  public Expression(int row, int col, Var v, Expression e, SimpleExpression se) {
    this.row = row;
    this.col = col;
    this.v = v;
    this.e = e;
    this.se = se;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}

