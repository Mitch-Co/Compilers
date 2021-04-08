import absyn.*;
import java.util.*;

public class MitchsMarvelousMachinecodeMaker implements AbsynVisitor {

    String toReturn = "";
    List<String> commands = new ArrayList<String>();

    static Integer MemStartReg = 0;
    static Integer ZeroValReg = 1;
    static Integer MaxMemVal = 1024;
    static Integer tempVarsOffset = 512;

    Integer currentMem = 0;
    Integer currentInstruction = 0;
    HashMap<String, Integer> memoryMapVariables = new HashMap<String, Integer>();

    Integer currentTemp = 0;
    HashMap<String, Integer> tempVariables = new HashMap<String, Integer>();

    HashMap<String, Integer> lineNumbers = new HashMap<String, Integer>();


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
        if(exp.rhs instanceof OpExp)
        {
            solveOpExp((OpExp) exp.rhs, 2, 3);
        }
        if(exp.rhs instanceof IntExp)
        {
            IntExp tmp = (IntExp) exp.rhs;
            assignVar(getVarName(exp.lhs), tmp.value);
        }
        
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
        addCommand("ST 0, " + currentMem + "(0)");
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
            this.toReturn = this.toReturn + "\n" + String.valueOf(i) + ": " + commands.remove(0);
            i++;
        }
        
    }

    public void assignVar(String varName, Integer regVal)
    {
        Integer memSet = memoryMapVariables.get(varName);

        if(memSet != null)
        {
            addCommand("ST " + String.valueOf(regVal) + ", " + String.valueOf(memSet) + "(" + String.valueOf(MemStartReg) + ")");
        }

    }

    public void addCommand(String toAdd)
    {
        commands.add(toAdd);
        this.currentInstruction += 1;
    }

    public void zeroRegister(Integer regNum)
    {
        if(regNum < 8  && regNum > 0)
        {
            String regNumStr = String.valueOf(regNum);
            addCommand("SUB " + regNum + ", " + regNum + ", " + regNum);
        }
        
    }

    public void assignReg(Integer regNum, Integer val)
    {
        if(regNum < 8 && regNum > 0 && regNum != ZeroValReg)
        {
            zeroRegister(regNum);
            addCommand("LDC "+ String.valueOf(regNum) + ", " + String.valueOf(val) + "(" + String.valueOf(ZeroValReg) + ")");
        }
    }

    public void recursiveSolve(OpExp toSolve, Integer resultMem, Integer tempReg1, Integer tempReg2)
    {

        Integer memLoc1 = currentTemp;
        currentTemp++;
        Integer memLoc2 = currentTemp;
        currentTemp++;


        // Make variable in memory
        tempVariables.put(String.valueOf(memLoc1), memLoc1 + tempVarsOffset);
        if(toSolve.left instanceof IntExp)
        {
            assignReg(tempReg1, ((IntExp) toSolve.left).value);
            addCommand("ST " + String.valueOf(tempReg1) + ", " + String.valueOf(memLoc1 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
            
        }
        else if(toSolve.left instanceof OpExp)
        {
            recursiveSolve((OpExp) toSolve.left, memLoc1 + tempVarsOffset, tempReg1, tempReg2);
        }

        tempVariables.put(String.valueOf(memLoc2), memLoc2 + tempVarsOffset);
        if(toSolve.right instanceof IntExp)
        {
            assignReg(tempReg2, ((IntExp) toSolve.right).value);
            addCommand("ST " + String.valueOf(tempReg2) + ", " + String.valueOf(memLoc2 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
        }
        else if(toSolve.right instanceof OpExp)
        {
            recursiveSolve((OpExp) toSolve.right, memLoc2 + tempVarsOffset, tempReg1, tempReg2);
        }

        addCommand("LD " + String.valueOf(tempReg1) + ", " + String.valueOf(memLoc1 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
        addCommand("LD " + String.valueOf(tempReg2) + ", " + String.valueOf(memLoc2 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
        
        if(toSolve.op == OpExp.PLUS)
        {
            addCommand("ADD " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
        }
        else if(toSolve.op == OpExp.MINUS)
        {
            addCommand("SUB " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
        }
        else if(toSolve.op == OpExp.MUL)
        {
            addCommand("MUL " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
        }
        else if(toSolve.op == OpExp.DIV)
        {
            addCommand("DIV " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
        }

        addCommand("ST " + String.valueOf(tempReg1) + ", " + String.valueOf(resultMem) + "(" + String.valueOf(MemStartReg) + ")");
        // put result in result memory
    }
    public void solveOpExp(OpExp toSolve, Integer resultReg, Integer tempReg)
    {
        Integer returnState = currentTemp;
        tempVariables.put(String.valueOf(currentTemp), currentTemp + tempVarsOffset);
        currentTemp++;
        recursiveSolve(toSolve, returnState + tempVarsOffset, resultReg, tempReg);
        addCommand("LD " + String.valueOf(resultReg) + ", " + String.valueOf(returnState + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
    }

    public void prelude()
    {
        addCommand("LD 6, 0(0)");
        addCommand("LDA 5, 0(6)");
        addCommand("ST 0, 0(0)");
        zeroRegister(ZeroValReg);
    }

    public String getVarName(Var v)
    {
        if(v instanceof SimpleVar)
        {
            return ((SimpleVar)v).name;
        }
        else if(v instanceof IndexVar)
        {
            return ((IndexVar)v).name;
        }
        return "x";
    }
    public void finale()
    {
        addCommand("HALT 0, 0, 0");
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