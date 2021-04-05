package absyn;

public class FunctionDec extends Dec {
    public NameTy result;
    public String func;
    public VarDecList params;

    public FunctionDec(int row, int col, NameTy result, String func, VarDecList params)
    {
        this.row = row;
        this.col = col;
        this.result = result;
        this.func = func;
        this.params = params;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}