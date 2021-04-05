package absyn;

public class OpExp extends Exp {
    public Exp left;
    public int op;
    public Exp right;

    public static int PLUS = 1;
    public static int MINUS = 2;
    public static int MUL = 3;
    public static int DIV = 4;
    public static int EQ = 5;
    public static int NE = 6;
    public static int LT = 7;
    public static int LE = 8;
    public static int GT = 9;
    public static int GE = 10;
    public static int ASSIGN = 11;

    public OpExp(int row, int col, Exp left, int op, Exp right)
    {
        this.row = row;
        this.col = col;
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public void accept( AbsynVisitor visitor, int level ) 
    {
        visitor.visit( this, level );
    }
}
