package absyn;

public class Term extends Absyn {
  public Term t;
  public MulOP m;
  public Factor f;

  public Term(int row, int col, Term t, MulOP m, Factor f) {
    this.row = row;
    this.col = col;
    this.t = t;
    this.m = m;
    this.f = f;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}