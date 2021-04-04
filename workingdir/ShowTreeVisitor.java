import absyn.*;

public class ShowTreeVisitor implements AbsynVisitor {

  final static int SPACES = 3;

  private void indent( int level ) {
    for( int i = 0; i < level * SPACES; i++ ) System.out.print( " " );
  }

  public void visit(NameTy type, int level)
  {

  }

  public void visit(SimpleVar var, int level)
  {

  }
  public void visit(IndexVar var, int level)
  {

  }

  public void visit(NilExp exp, int level)
  {

  }
  public void visit(VarExp exp, int level)
  {
  
  }
  public void visit(IntExp exp, int level)
  {
    indent(level);
    System.out.println("IntExp: " + exp.value);
  }
  public void visit(CallExp exp, int level)
  {

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
    
    System.out.print("\n");
    exp.left.accept(this, level + 1);
    exp.right.accept(this, level + 1);
    
  }
  public void visit(AssignExp exp, int level)
  {

  }
  public void visit(IfExp exp, int level)
  {

  }
  public void visit(WhileExp exp, int level)
  {

  }
  public void visit(ReturnExp exp, int level)
  {

  }
  public void visit(CompoundExp exp, int level)
  {

  }

  public void visit(FunctionDec dec, int level)
  {

  }
  public void visit(SimpleDec dec, int level)
  {

  }
  public void visit(ArrayDec dec, int level)
  {

  }

  public void visit(DecList list, int level)
  {

  }
  public void visit(VarDecList list, int level)
  {

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


  // /* -------- BEGINNING OF LISTS -------- */
  // public void visit( DecList decList, int level ) {
  //   while( decList != null) {
  //     if(decList.head != null)
  //     {
  //       decList.head.accept( this, level );
  //     }
  //     decList = decList.tail;
  //   } 
  // }

  // public void visit( ParamList pList, int level ) {
  //   while( pList != null) {
  //     if(pList.head != null)
  //     {
  //       pList.head.accept( this, level );
  //     }
  //     pList = pList.tail;
  //   } 
  // }

  // public void visit( StatementList sList, int level ) {
  //   while( sList != null) {
  //     if(sList.head != null)
  //     {
  //       sList.head.accept( this, level );
  //     }
  //     sList = sList.tail;
  //   } 
  // }

  // public void visit( ArgList aList, int level ) {
  //   while( aList != null) {
  //     if(aList.head != null)
  //     {
  //       aList.head.accept( this, level );
  //     }
  //     aList = aList.tail;
  //   } 
  // }
  // /* -------- END OF LISTS -------- */
  

  // public void visit( VarDec dec, int level ) {
  //   indent( level );
  //   System.out.println( "VarDec: ");
  //   indent( level + 1);
  //   System.out.println( "ID: " + dec.ID);
  //   dec.TypeSpecifier.accept(this, level + 1);
  //   if(dec.NUM != null)
  //   {
  //     dec.NUM.accept(this, level + 1);
  //   }
  // }

  // public void visit(FunDec dec, int level)
  // {
  //   indent( level );
  //   System.out.println( "FunDec: "); 
  //   dec.ts.accept( this, level + 1);
  //   indent( level + 1);
  //   System.out.println( "ID: " + dec.ID); 
  //   dec.cs.accept( this, level + 1);
  // }

  // public void visit( IntVal val, int level ) {
  //   indent( level );
  //   System.out.println( "IntVal: " + val.value); 
  // }

  // public void visit( TypeVal val, int level ) {
  //   indent( level );
  //   System.out.println( "TypeVal: " + val.value); 
  // }
  

  
  // public void visit( Param p, int level )
  // {
  //   indent( level );
  //   System.out.println( "Param: ");
  //   indent( level + 1 );
  //   System.out.println( "ID: ");
  //   p.t.accept( this, level + 1);
     
  // }
  // public void visit( Expression e, int level )
  // {
  //   indent( level );
  //   System.out.println( "Expression: "); 
  //   if(e.e != null)
  //   {
  //     e.e.accept( this, level + 1);
  //   }
  //   if(e.v != null)
  //   {
  //     e.v.accept( this, level + 1);
  //   }
  //   if(e.se != null)
  //   {
  //     e.se.accept( this, level + 1);
  //   }
    
  // }

  // public void visit( SimpleExpression se, int level )
  // {
  //   indent( level );
  //   System.out.println( "SimpleExpression: "); 
  //   if(se.r != null)
  //   {
  //     se.ae.accept( this, level + 1);
  //     se.r.accept( this, level + 1);
  //     se.ae2.accept( this, level + 1);
  //   }
  //   else
  //   {
  //     se.ae.accept( this, level + 1);
  //   }
  // }

  // public void visit( AdditiveExpression ae, int level )
  // {
  //   indent( level );
  //   System.out.println( "AdditiveExpression: "); 
  //   if(ae.ae != null)
  //   {
  //     ae.ae.accept( this, level + 1);
  //     ae.a.accept( this, level + 1);
  //     ae.t.accept( this, level + 1);
  //   }
  //   else
  //   {
  //     ae.t.accept( this, level + 1);
  //   }
  // }

  // public void visit( Var v, int level)
  // {
  //   indent( level ); 
  //   if(v.e != null)
  //   {
  //     System.out.println( "Var: ");
  //     indent( level + 1);
  //     System.out.println( "ID: " + v.ID);
  //     v.e.accept( this, level + 1);
  //   }
  //   else
  //   {
  //     System.out.println( "Var: ");
  //     indent( level + 1);
  //     System.out.println( "ID: " + v.ID);
  //   }
  // }

  // public void visit( Term t, int level)
  // {
  //   indent( level );
  //   System.out.println( "Term: "); 
  //   if(t.t != null)
  //   {
  //     t.t.accept( this, level + 1);
  //     t.m.accept( this, level + 1);
  //     t.f.accept( this, level + 1);
  //   }
  //   else
  //   {
  //     t.f.accept( this, level + 1);
  //   }
  // }
  // public void visit( Factor f, int level)
  // {
  //   indent( level );
  //   System.out.println( "Factor: "); 
  //   if(f.e != null)
  //   {
  //     f.e.accept( this, level + 1);
  //   }
  //   if(f.v != null)
  //   {
  //     f.v.accept( this, level + 1);
  //   }
  //   if(f.c != null)
  //   {
  //     f.c.accept( this, level + 1);
  //   }
  //   if(f.NUM != null)
  //   {
  //     f.NUM.accept( this, level + 1);
  //   }
  // }

  // public void visit( Call c, int level){
  //   indent( level );
  //   System.out.println( "Call: ");
  //   indent( level + 1);
  //   System.out.println( "ID: " + c.ID);
  //   c.al.accept( this, level + 1);
  // }

  // public void visit( Statement s, int level )
  // {
  //   // ????? NEEDED??
  //   s.accept( this, level);
  // }
  // public void visit( CompoundStmt cs, int level)
  // {
  //   indent( level );
  //   System.out.println( "CompoundStatement: ");
  //   cs.LocalDec.accept( this, level + 1);
  //   cs.sl.accept( this, level + 1);
  // }
  // public void visit( SelectionStmt ss, int level)
  // {
  //   indent( level );
  //   System.out.println( "SelectionStatement: ");
  //   indent( level );
  //   System.out.println( "SelectionIF: "); 
  //   ss.ifE.accept( this, level + 1);
  //   ss.thenS.accept( this, level + 1);
  //   if(ss.elseS != null)
  //   {
  //     indent( level );
  //     System.out.println( "SelectionELSE: ");
  //     ss.elseS.accept( this, level + 1);
  //   }

  // }
  // public void visit( IterationStmt is, int level)
  // {
  //   indent( level );
  //   System.out.println( "IterationStatement: ");
  //   is.e.accept( this, level + 1);
  //   is.s.accept( this, level + 1);
  // }
  // public void visit( ReturnStmt rs, int level)
  // {
  //   indent( level );
  //   System.out.println( "ReturnStatement: ");
  //   if(rs.e != null)
  //   {
  //     rs.e.accept( this, level + 1);
  //   }

  // }
  
  
  // public void visit( AddOP a, int level )
  // {
  //   indent( level );
  //   System.out.println("AddOP: " + a.value);
  // }
  // public void visit( MulOP m, int level )
  // {
  //   indent( level );
  //   System.out.println("MulOP: " + m.value);
  // }
  // public void visit( RelOP r, int level )
  // {
  //   indent( level );
  //   System.out.println("RelOP: " + r.value);
  // }


  // public void visit( ExpList expList, int level ) {
  //   while( expList != null ) {
  //     expList.head.accept( this, level );
  //     expList = expList.tail;
  //   } 
  // }

  // public void visit( AssignExp exp, int level ) {
  //   indent( level );
  //   System.out.println( "AssignExp:" );
  //   level++;
  //   exp.lhs.accept( this, level );
  //   exp.rhs.accept( this, level );
  // }

  // public void visit( IfExp exp, int level ) {
  //   indent( level );
  //   System.out.println( "IfExp:" );
  //   level++;
  //   exp.test.accept( this, level );
  //   exp.thenpart.accept( this, level );
  //   if (exp.elsepart != null )
  //      exp.elsepart.accept( this, level );
  // }

  // public void visit( IntExp exp, int level ) {
  //   indent( level );
  //   System.out.println( "IntExp: " + exp.value ); 
  // }

  // public void visit( OpExp exp, int level ) {
  //   indent( level );
  //   System.out.print( "OpExp:" ); 
  //   switch( exp.op ) {
  //     case OpExp.PLUS:
  //       System.out.println( " + " );
  //       break;
  //     case OpExp.MINUS:
  //       System.out.println( " - " );
  //       break;
  //     case OpExp.TIMES:
  //       System.out.println( " * " );
  //       break;
  //     case OpExp.OVER:
  //       System.out.println( " / " );
  //       break;
  //     case OpExp.EQ:
  //       System.out.println( " = " );
  //       break;
  //     case OpExp.LT:
  //       System.out.println( " < " );
  //       break;
  //     case OpExp.GT:
  //       System.out.println( " > " );
  //       break;
  //     default:
  //       System.out.println( "Unrecognized operator at line " + exp.row + " and column " + exp.col);
  //   }
  //   level++;
  //   exp.left.accept( this, level );
  //   exp.right.accept( this, level );
  // }

  // public void visit( ReadExp exp, int level ) {
  //   indent( level );
  //   System.out.println( "ReadExp:" );
  //   exp.input.accept( this, ++level );
  // }

  // public void visit( RepeatExp exp, int level ) {
  //   indent( level );
  //   System.out.println( "RepeatExp:" );
  //   level++;
  //   exp.exps.accept( this, level );
  //   exp.test.accept( this, level ); 
  // }

  // public void visit( VarExp exp, int level ) {
  //   indent( level );
  //   System.out.println( "VarExp: " + exp.name );
  // }

  // public void visit( WriteExp exp, int level ) {
  //   indent( level );
  //   System.out.println( "WriteExp:" );
  //   exp.output.accept( this, ++level );
  // }

}
