package absyn;

public class NameTy extends Absyn {
    int typ;

    enum type {
        INT,
        VOID
    }

    public NameTy(int pos, int typ)
    {
        this.pos = pos;
        this.typ = typ;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}
