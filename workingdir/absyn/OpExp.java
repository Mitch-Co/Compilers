package absyn;

public class OpExp extends Exp {
    Exp left;
    int op;
    Exp right;

    enum type {
        PLUS,
        MINUS,
        MUL,
        DIV,
        EQ,
        NE,
        LT,
        LE,
        GT,
        GE,
        ASSIGN
    }

    public OpExp(int pos, Exp left, int op, Exp right)
    {
        this.pos = pos;
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}
