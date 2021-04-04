package absyn;

public class FunDec extends Dec {
  public TypeVal ts;
  public String ID;
  public ParamList pl;
  public CompoundStmt cs;

  public FunDec(int row, int col, TypeVal ts, String ID, ParamList pl, CompoundStmt cs) {
    this.row = row;
    this.col = col;
    this.ts = ts;
    this.ID = ID;
    this.pl = pl;
    this.cs = cs;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
