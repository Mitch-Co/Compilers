package absyn;

public class FunctionDec extends Dec {
    NameTy result;
    String func;
    VarDecList params;

    public FunctionDec(int pos, NameTy result, String func, VarDecList params)
    {
        this.pos = pos;
        this.result = result;
        this.func = func;
        this.params = params;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}