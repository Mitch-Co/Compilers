package absyn;

public class AdditiveExpression extends Absyn {
  public AdditiveExpression ae;
  public AddOP a;
  public Term t;

  public AdditiveExpression(int row, int col, AdditiveExpression ae, AddOP a, Term t) {
    this.row = row;
    this.col = col;
    this.ae = ae;
    this.a = a;
    this.t = t;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
