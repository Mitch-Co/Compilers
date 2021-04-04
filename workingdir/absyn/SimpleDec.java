package absyn;

public class SimpleDec extends VarDec {
    NameTy typ;
    String name;

    public SimpleDec(int pos, NameTy typ, String name)
    {
        this.pos = pos;
        this.name = name;
        this.typ = typ;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}