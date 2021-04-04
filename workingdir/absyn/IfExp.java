package absyn;

public class IfExp extends Exp {
    Exp _test;
    Exp _then;
    Exp _else;

    public IfExp(int pos, Exp _test, Exp _then, Exp _else)
    {
        this.pos = pos;
        this._test = _test;
        this._then = _then;
        this._else = _else;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}