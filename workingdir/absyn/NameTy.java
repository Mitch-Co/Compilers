package absyn;

public class NameTy extends Absyn {
    public int typ;

    public static int _INT = 1;
    public static int _VOID = 2;

    public NameTy(int row, int col, int typ)
    {
        this.row = row;
        this.col = col;
        this.typ = typ;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}
