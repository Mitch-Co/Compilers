package absyn;

public interface AbsynVisitor {

  public void visit( DecList decList, int level );
  public void visit( ParamList pList, int level );
  public void visit( StatementList sList, int level );
  public void visit( ArgList aList, int level );

  public void visit( VarDec dec, int level );
  public void visit( FunDec dec, int level);

  public void visit( IntVal val, int level );
  public void visit( TypeVal val, int level );

  


 
  public void visit( Param p, int level );
  public void visit( Expression e, int level );
  public void visit( SimpleExpression se, int level );
  public void visit( AdditiveExpression ae, int level );

  public void visit( Var v, int level);
  public void visit( Term t, int level);
  public void visit( Factor f, int level);
  public void visit( Call c, int level);

  public void visit( Statement s, int level );
  public void visit( CompoundStmt cs, int level);
  public void visit( SelectionStmt ss, int level);
  public void visit( IterationStmt is, int level);
  public void visit( ReturnStmt rs, int level);
  
  
  public void visit( AddOP a, int level );
  public void visit( MulOP m, int level );
  public void visit( RelOP r, int level );



  // public void visit( ExpList exp, int level );

  // public void visit( AssignExp exp, int level );

  // public void visit( IfExp exp, int level );

  // public void visit( IntExp exp, int level );

  // public void visit( OpExp exp, int level );

  // public void visit( ReadExp exp, int level );

  // public void visit( RepeatExp exp, int level );

  // public void visit( VarExp exp, int level );

  // public void visit( WriteExp exp, int level );

}
