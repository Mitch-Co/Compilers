import absyn.*;
import java.util.*;

public class MitchsMarvelousMachinecodeMaker implements AbsynVisitor {

    String toReturn = "";
    List<String> commands = new ArrayList<String>();

    Integer currentMem = 1;
    HashMap<String, Integer> memoryMapVariables = new HashMap<String, Integer>();
    HashMap<String, Integer> tempVariables = new HashMap<String, Integer>();


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
    }
    public void visit(CallExp exp, int level)
    {
    }
    public void visit(OpExp exp, int level)
    {
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
        if(dec.func.equals("main"))
        {
            //commands.add("HIT MAIN");
            handleVariableList(dec.body.decs, level);
            handleExps(dec.body.exps, level);
        }
    }
    public void visit(SimpleDec dec, int level)
    {
        memoryMapVariables.put(dec.name, currentMem);
        commands.add("ST 0, " + currentMem + "(0)");
        currentMem++;
    }
    public void visit(ArrayDec dec, int level)
    {

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

    }
    public void visit(ExpList list, int level)
    {

    }

    public void handleExps(ExpList list, int level)
    {
        while(list != null) {
            if(list.head != null)
            {
                list.head.accept(this, level);
            }
            list = list.tail;
        } 
    }

    public void handleVariableList(VarDecList list, int level)
    {
        while(list != null) {
            if(list.head != null)
            {
                list.head.accept(this, level);
            }
            list = list.tail;
        } 
    }

    public void buildOutput()
    {
        int i = 0;
        while(!commands.isEmpty())
        {
            this.toReturn = this.toReturn + "\n" + String.valueOf(i)+ ": " + commands.remove(0);
            i++;
        }
        
    }

    public void prelude()
    {
        commands.add("LD 6, 0(0)");
        commands.add("LDA 5, 0(6)");
        commands.add("ST 0, 0(0)");
    }

    public void finale()
    {
        commands.add("HALT 0, 0, 0");
    }

    public String generateAssembly(Absyn tree)
    {
        prelude();
        tree.accept(this, 0);
        finale();
        buildOutput();
        return toReturn;
    } 
}