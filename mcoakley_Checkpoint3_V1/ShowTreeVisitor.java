import absyn.*;

public class ShowTreeVisitor implements AbsynVisitor {

  final static int SPACES = 3;

  private void indent( int level ) {
    for( int i = 0; i < level * SPACES; i++ ) System.out.print( " " );
  }

  public void visit(NameTy type, int level)
  {
    indent(level);
    if(type.typ == NameTy._INT)
    {
      System.out.println("NameTy: INT\n");
    }
    else if (type.typ == NameTy._VOID)
    {
      System.out.println("NameTy: VOID\n");
    }
    
  }

  public void visit(SimpleVar var, int level)
  {
    indent(level);
    System.out.println("SimpleVar: " + var.name);
  }
  public void visit(IndexVar var, int level)
  {
    indent(level);
    System.out.println("IndexVar: " + var.name);
    var.index.accept(this, level + 1);
  }

  public void visit(NilExp exp, int level)
  {

  }
  public void visit(VarExp exp, int level)
  {
    exp.variable.accept(this, level);
  }
  public void visit(IntExp exp, int level)
  {
    indent(level);
    System.out.println("IntExp: " + exp.value);
  }
  public void visit(CallExp exp, int level)
  {
    indent(level);
    System.out.println("CallExp: " + exp.func);
    exp.args.accept(this, level + 1);
  }
  public void visit(OpExp exp, int level)
  {
    indent(level);
    System.out.print("OpExp:");
    if(exp.op == OpExp.PLUS)
    {
      System.out.print(" + ");
    }
    else if(exp.op == OpExp.MINUS)
    {
      System.out.print(" - ");
    }
    else if(exp.op == OpExp.MUL)
    {
      System.out.print(" * ");
    }
    else if(exp.op == OpExp.DIV)
    {
      System.out.print(" / ");
    }
    else if(exp.op == OpExp.EQ)
    {
      System.out.print(" == ");
    }
    else if(exp.op == OpExp.NE)
    {
      System.out.print(" != ");
    }
    else if(exp.op == OpExp.LT)
    {
      System.out.print(" < ");
    }
    else if(exp.op == OpExp.GT)
    {
      System.out.print(" > ");
    }
    else if(exp.op == OpExp.GE)
    {
      System.out.print(" >= ");
    }
    else if(exp.op == OpExp.LE)
    {
      System.out.print(" <= ");
    }
    
    System.out.print("\n");
    exp.left.accept(this, level + 1);
    exp.right.accept(this, level + 1);
    
  }
  public void visit(AssignExp exp, int level)
  {
    indent(level);
    System.out.print("AssignExp: "+ "\n");
    exp.lhs.accept(this, level + 1);
    exp.rhs.accept(this, level + 1);
  }
  public void visit(IfExp exp, int level)
  {
    indent(level);
    System.out.print("IfExp:\n");
    exp._test.accept(this, level + 1);
    exp._then.accept(this, level + 1);
    if(exp._else != null)
    {
      indent(level);
      System.out.print("Else:\n");
      exp._else.accept(this, level + 1);
    }
  }
  public void visit(WhileExp exp, int level)
  {
    indent(level);
    System.out.print("WhileExp:\n");
    exp.test.accept(this, level + 1);
    exp.body.accept(this, level + 1);
  }
  public void visit(ReturnExp exp, int level)
  {
    indent(level);
    System.out.print("ReturnExp:\n");
    if(exp.exp != null)
    {
      exp.exp.accept(this, level + 1);
    }
  }
  public void visit(CompoundExp exp, int level)
  {
    exp.decs.accept(this, level);
    exp.exps.accept(this, level);
  }

  public void visit(FunctionDec dec, int level)
  {
    indent(level);
    if(dec.result.typ == NameTy._INT)
    {
      System.out.print("Function Dec: int " + dec.func + "\n");
    }
    else if(dec.result.typ == NameTy._VOID)
    {
      System.out.print("Function Dec: void " + dec.func + "\n");
    }

    VarDecList list = dec.params;
    dec.params.accept(this, level + 1);
    dec.body.accept(this, level + 1);

  }
  public void visit(SimpleDec dec, int level)
  {
    indent(level);
    String isParam = dec.isParam?"(Param)":"";
    System.out.print("SimpleDec" + isParam + ": " + dec.name + "\n");
  }
  public void visit(ArrayDec dec, int level)
  {
    indent(level);
    String isParam = dec.isParam?"(Param)":"";
    String hasValue = (dec.size == null)?"":String.valueOf(dec.size.value);
    System.out.print("ArrayDec" + isParam + ": " + dec.name + "[" + hasValue + "]" + "\n");
  }

  public void visit(DecList list, int level)
  {
    while(list != null) {
      if(list.head != null)
      {
        list.head.accept(this, level);
      }
      list = list.tail;
    } 
  }
  public void visit(VarDecList list, int level)
  {
    while(list != null) {
      if(list.head != null)
      {
        list.head.accept(this, level);
      }
      list = list.tail;
    } 
  }
  public void visit(ExpList list, int level)
  {
    while(list != null) {
      if(list.head != null)
      {
        list.head.accept(this, level);
      }
      list = list.tail;
    } 
  }

}
