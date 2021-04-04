package absyn;

public class VarDec extends Dec {
  public String ID;
  public TypeVal TypeSpecifier;
  public IntVal NUM;

  public VarDec(int row, int col, String ID, TypeVal TypeSpecifier, IntVal NUM) {
    this.row = row;
    this.col = col;
    this.ID = ID;
    this.TypeSpecifier = TypeSpecifier;
    this.NUM = NUM;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}

