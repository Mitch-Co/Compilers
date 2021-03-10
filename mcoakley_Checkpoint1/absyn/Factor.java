package absyn;

public class Factor extends Absyn {
  public Expression e;
  public Var v;
  public Call c;
  public IntVal NUM;

  public Factor(int row, int col, Expression e, Var v, Call c, IntVal NUM) {
    this.row = row;
    this.col = col;
    this.e = e;
    this.v = v;
    this.c = c;
    this.NUM = NUM;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}