package absyn;

public class IfExp extends Exp {
    public Exp _test;
    public Exp _then;
    public Exp _else;

    public IfExp(int row, int col, Exp _test, Exp _then, Exp _else)
    {
        this.row = row;
        this.col = col;
        this._test = _test;
        this._then = _then;
        this._else = _else;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}