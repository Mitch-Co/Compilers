package absyn;

public class StatementList extends Absyn {
  public Statement head;
  public StatementList tail;

  public StatementList( Statement head, StatementList tail ) {
    this.head = head;
    this.tail = tail;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
