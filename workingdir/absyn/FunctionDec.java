package absyn;

public class FunctionDec extends Dec {
    public NameTy result;
    public String func;
    public VarDecList params;

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