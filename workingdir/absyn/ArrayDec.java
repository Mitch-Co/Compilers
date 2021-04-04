package absyn;

public class ArrayDec extends VarDec {
    NameTy typ;
    String name;
    IntExp size;

    public ArrayDec(int pos, NameTy typ, String name, IntExp size)
    {
        this.pos = pos;
        this.name = name;
        this.typ = typ;
        this.size = size;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}