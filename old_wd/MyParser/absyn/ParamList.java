package absyn;

public class ParamList extends Absyn {
  public Param head;
  public ParamList tail;

  public ParamList( Param head, ParamList tail ) {
    this.head = head;
    this.tail = tail;
  }

  public void accept( AbsynVisitor visitor, int level ) {
    visitor.visit( this, level );
  }
}
