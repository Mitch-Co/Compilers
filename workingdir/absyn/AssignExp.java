package absyn;

public class AssignExp extends Exp {
    Var lhs;
    Exp rhs;

    public AssignExp(int pos, Var lhs, Exp rhs)
    {
        this.pos = pos;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}