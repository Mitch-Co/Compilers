package absyn;

public class ArgList extends Absyn {
  public Expression head;
  public ArgList tail;

  public ArgList( Expression head, ArgList tail ) {
    this.head = head;
    this.tail = tail;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
