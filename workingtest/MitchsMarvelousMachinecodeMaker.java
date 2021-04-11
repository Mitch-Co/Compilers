import absyn.*;
import java.util.*;

public class MitchsMarvelousMachinecodeMaker implements AbsynVisitor {

    String toReturn = "";
    
    List<String> currentCommandList = null;
    static Integer MemStartReg = 0;
    static Integer ZeroValReg = 1;
    static Integer MaxMemVal = 1024;
    static Integer tempVarsOffset = 512;

    Integer currentMem = 0;
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
        if(exp.func.equals("output"))
        {
            Exp callArg = ((ExpList) exp.args).head;
            if(callArg == null)
            {
                return;
            }

            if(callArg instanceof OpExp)
            {
                solveOpExp((OpExp) callArg, 2, 3);
            }
            else
            {
                OpExp imLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, callArg);
                solveOpExp(imLazy, 2, 3);
            }
            addCommand("OUT " + String.valueOf(2) + " ,0 ,0");

        }
    }
    public void visit(OpExp exp, int level)
    {
    }
    public void visit(AssignExp exp, int level)
    {
        Integer getIndex = memoryMapVariables.get(getVarName(exp.lhs));
        if(exp.lhs instanceof SimpleVar)
        {
            if(exp.rhs instanceof OpExp)
            {
                solveOpExp((OpExp) exp.rhs, 2, 3);
                addCommand("ST " + String.valueOf(2) + ", " + memoryMapVariables.get(getVarName(exp.lhs)) + "(" + String.valueOf(MemStartReg) + ")");
            }
            if(exp.rhs instanceof IntExp)
            {
                assignReg(2, ((IntExp) exp.rhs).value);
                assignVar(getVarName(exp.lhs), 2);
            }
            if(exp.rhs instanceof VarExp)
            {
                OpExp imREALLYLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, exp.rhs);
                solveOpExp(imREALLYLazy, 2, 3);
                addCommand("ST " + String.valueOf(2) + ", " + memoryMapVariables.get(getVarName(exp.lhs)) + "(" + String.valueOf(MemStartReg) + ")");
            }
            if(exp.rhs instanceof CallExp)
            {
                OpExp imREALLYLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, exp.rhs);
                solveOpExp(imREALLYLazy, 2, 3);
                addCommand("ST " + String.valueOf(2) + ", " + memoryMapVariables.get(getVarName(exp.lhs)) + "(" + String.valueOf(MemStartReg) + ")");
            }
        }
        else if(exp.lhs instanceof IndexVar)
        {
            OpExp imLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, ((IndexVar) exp.lhs).index);
            solveOpExp(imLazy, 4, 3);

            if(exp.rhs instanceof OpExp)
            {
                solveOpExp((OpExp) exp.rhs, 2, 3);
                addCommand("ST " + String.valueOf(2) + ", " + memoryMapVariables.get(getVarName(exp.lhs)) + "(" + String.valueOf(4) + ")");
            }
            if(exp.rhs instanceof IntExp)
            {
                assignReg(2, ((IntExp) exp.rhs).value);
                addCommand("ST " + String.valueOf(2) + ", " + memoryMapVariables.get(getVarName(exp.lhs))+ "(" + String.valueOf(4) + ")");
            }
            if(exp.rhs instanceof VarExp)
            {
                OpExp imREALLYLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, exp.rhs);
                solveOpExp(imREALLYLazy, 2, 3);
                addCommand("ST " + String.valueOf(2) + ", " + memoryMapVariables.get(getVarName(exp.lhs)) + "(" + String.valueOf(4) + ")");
            }
            if(exp.rhs instanceof CallExp)
            {
                OpExp imREALLYLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, exp.rhs);
                solveOpExp(imREALLYLazy, 2, 3);
                addCommand("ST " + String.valueOf(2) + ", " + memoryMapVariables.get(getVarName(exp.lhs)) + "(" + String.valueOf(4) + ")");
            }
        
        }
        else
        {
            return;
        }


        
    }
    public void visit(IfExp exp, int level)
    {
        List<String> prevScope = currentCommandList;
        List<String> conditionScope = new ArrayList<String>();
        List<String> expressionScope = new ArrayList<String>();
        List<String> expressionScope2 = new ArrayList<String>();

        currentCommandList = conditionScope;
        if(exp._test instanceof OpExp)
        {
            OpExp tmp = (OpExp) exp._test;
            solveOpExp(tmp, 2, 3);
            
            if(tmp.op == OpExp.EQ)
            {
                addCommand("JEQ " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.NE)
            {
                addCommand("JNE " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.LT)
            {
                addCommand("JLT " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.GT)
            {
                addCommand("JGT " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.LE)
            {
                addCommand("JLE " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.GE)
            {
                addCommand("JGE " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }

        }
        currentCommandList = expressionScope;
        exp._then.accept(this, level);

        if(exp._else == null)
        {
            currentCommandList = prevScope;

            Integer n = conditionScope.size();
            Integer m = expressionScope.size();

            currentCommandList.addAll(conditionScope);
            addCommand("JEQ " + String.valueOf(ZeroValReg) + ", " +  String.valueOf(m) + "(" + String.valueOf(7) + ")");
            currentCommandList.addAll(expressionScope);
        }
        else
        {
            currentCommandList = expressionScope2;
            exp._else.accept(this, level);
            currentCommandList = prevScope;

            Integer n = conditionScope.size();
            Integer m = expressionScope.size();
            Integer q = expressionScope2.size();

            currentCommandList.addAll(conditionScope);
            addCommand("JEQ " + String.valueOf(ZeroValReg) + ", " +  String.valueOf(m + 1) + "(" + String.valueOf(7) + ")");
            currentCommandList.addAll(expressionScope);
            addCommand("JEQ " + String.valueOf(ZeroValReg) + ", " +  String.valueOf(q) + "(" + String.valueOf(7) + ")");
            currentCommandList.addAll(expressionScope2);
        }

    }
    public void visit(WhileExp exp, int level)
    {
        List<String> prevScope = currentCommandList;
        List<String> conditionScope = new ArrayList<String>();
        List<String> expressionScope = new ArrayList<String>();

        currentCommandList = conditionScope;
        if(exp.test instanceof OpExp)
        {
            OpExp tmp = (OpExp) exp.test;
            solveOpExp(tmp, 2, 3);
            
            if(tmp.op == OpExp.EQ)
            {
                addCommand("JEQ " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.NE)
            {
                addCommand("JNE " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.LT)
            {
                addCommand("JLT " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.GT)
            {
                addCommand("JGT " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.LE)
            {
                addCommand("JLE " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }
            if(tmp.op == OpExp.GE)
            {
                addCommand("JGE " + String.valueOf(2) + ", " +  String.valueOf(1) + "(" + String.valueOf(7) + ")");
            }

        }
        else
        {
            currentCommandList = prevScope;
            return;
        }

        currentCommandList = expressionScope;
        exp.body.accept(this, level);
        currentCommandList = prevScope;

        Integer n = conditionScope.size();
        Integer m = expressionScope.size();
        

        currentCommandList.addAll(conditionScope);
        addCommand("JEQ " + String.valueOf(ZeroValReg) + ", " +  String.valueOf(m + 1) + "(" + String.valueOf(7) + ")");
        currentCommandList.addAll(expressionScope);
        addCommand("JEQ " + String.valueOf(ZeroValReg) + ", " +  String.valueOf(-1* (m + n + 1 + 1)) + "(" + String.valueOf(7) + ")");
           
    }
    public void visit(ReturnExp exp, int level)
    {
    }
    public void visit(CompoundExp exp, int level)
    {
        handleVariableList(exp.decs, level);
        handleExps(exp.exps, level);
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
        addCommand("ST 0, " + currentMem + "(" + String.valueOf(ZeroValReg) +  ")");
        currentMem++;
    }
    public void visit(ArrayDec dec, int level)
    {
        memoryMapVariables.put(dec.name, currentMem);
        addCommand("ST 0, " + currentMem + "(" + String.valueOf(ZeroValReg) +  ")");
        currentMem = currentMem + ((IntExp)dec.size).value;
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
        while(!currentCommandList.isEmpty())
        {
            this.toReturn = this.toReturn + String.valueOf(i) + ": " + currentCommandList.remove(0) + "\n";
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
        currentCommandList.add(toAdd);
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
            addCommand("LDC "+ String.valueOf(regNum) + ", " + String.valueOf(val) + "(" + String.valueOf(ZeroValReg) + ")");
        }
    }

    // Do you think god stays in heaven because he too lives in fear of what he's created?
    public boolean recursiveSolve(OpExp toSolve, Integer resultMem, Integer tempReg1, Integer tempReg2)
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
        else if(toSolve.left instanceof VarExp)
        {
            Var targetVar = (Var) ((VarExp) toSolve.left).variable;
            if(targetVar instanceof SimpleVar)
            {
                addCommand("LD " + String.valueOf(tempReg1) + ", " + memoryMapVariables.get(getVarName(targetVar)) + "(" + String.valueOf(MemStartReg) + ")");
                addCommand("ST " + String.valueOf(tempReg1) + ", " + String.valueOf(memLoc1 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
            }
            else if(targetVar instanceof IndexVar)
            {
                IndexVar solveIndex = (IndexVar) targetVar;
                Integer memLoc3 = currentTemp;
                currentTemp++;
                if(solveIndex.index instanceof OpExp)
                {
                    recursiveSolve((OpExp)solveIndex.index, memLoc3 + tempVarsOffset, tempReg1, tempReg2);
                }
                else if (solveIndex.index instanceof Exp)
                {
                    OpExp imLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, solveIndex.index);
                    recursiveSolve(imLazy, memLoc3 + tempVarsOffset, tempReg1, tempReg2);
                }

                addCommand("LD " + String.valueOf(tempReg2) + ", " + String.valueOf(memLoc3 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
                assignReg(tempReg1, memoryMapVariables.get(getVarName(targetVar)));
                addCommand("LD " + String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2)+ "(" + String.valueOf(tempReg1) + ")");
                addCommand("ST " + String.valueOf(tempReg1) + ", " + String.valueOf(memLoc1 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
            }
        }
        else if(toSolve.left instanceof OpExp)
        {
            recursiveSolve((OpExp) toSolve.left, memLoc1 + tempVarsOffset, tempReg1, tempReg2);
        }
        else if(toSolve.left instanceof CallExp)
        {
            CallExp theCall = (CallExp) toSolve.left;

            if(theCall.func.equals("input"))
            {
                addCommand("IN " + String.valueOf(tempReg1) + " ,0 ,0");
                addCommand("ST " + String.valueOf(tempReg1) + ", " + String.valueOf(memLoc1 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
            }
        }

        tempVariables.put(String.valueOf(memLoc2), memLoc2 + tempVarsOffset);
        if(toSolve.right instanceof IntExp)
        {
            assignReg(tempReg2, ((IntExp) toSolve.right).value);
            addCommand("ST " + String.valueOf(tempReg2) + ", " + String.valueOf(memLoc2 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
        }
        else if(toSolve.right instanceof VarExp)
        {
            Var targetVar = (Var) ((VarExp) toSolve.right).variable;
            if(targetVar instanceof SimpleVar)
            {
                addCommand("LD " + String.valueOf(tempReg2) + ", " + memoryMapVariables.get(getVarName(targetVar)) + "(" + String.valueOf(MemStartReg) + ")");
                addCommand("ST " + String.valueOf(tempReg2) + ", " + String.valueOf(memLoc2 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
            }
            else if(targetVar instanceof IndexVar)
            {
                IndexVar solveIndex = (IndexVar) targetVar;
                Integer memLoc4 = currentTemp;
                currentTemp++;
                if(solveIndex.index instanceof OpExp)
                {
                    recursiveSolve((OpExp)solveIndex.index, memLoc4 + tempVarsOffset, tempReg1, tempReg2);
                }
                else if (solveIndex.index instanceof Exp)
                {
                    OpExp imLazy = new OpExp(-1,-1, new IntExp(-1, -1, 0), OpExp.PLUS, solveIndex.index);
                    recursiveSolve(imLazy, memLoc4 + tempVarsOffset, tempReg1, tempReg2);
                }

                addCommand("LD " + String.valueOf(tempReg2) + ", " + String.valueOf(memLoc4 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
                assignReg(tempReg1, memoryMapVariables.get(getVarName(targetVar)));
                addCommand("LD " + String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2)+ "(" + String.valueOf(tempReg1) + ")");
                addCommand("ST " + String.valueOf(tempReg1) + ", " + String.valueOf(memLoc2 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
            }
        }
        else if(toSolve.right instanceof OpExp)
        {
            recursiveSolve((OpExp) toSolve.right, memLoc2 + tempVarsOffset, tempReg1, tempReg2);
        }
        else if(toSolve.right instanceof CallExp)
        {
            CallExp theCall = (CallExp) toSolve.right;

            if(theCall.func.equals("input"))
            {
                addCommand("IN " + String.valueOf(tempReg2) + " ,0 ,0");
                addCommand("ST " + String.valueOf(tempReg2) + ", " + String.valueOf(memLoc2 + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
            }
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
        else if(toSolve.op == OpExp.EQ)
        {
            addCommand("SUB " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
            return true;
        }
        else if(toSolve.op == OpExp.NE)
        {
            addCommand("SUB " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
            return true;
        }
        else if(toSolve.op == OpExp.LT)
        {
            addCommand("SUB " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
            return true;
        }
        else if(toSolve.op == OpExp.GT)
        {
            addCommand("SUB " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
            return true;
        }
        else if(toSolve.op == OpExp.LE)
        {
            addCommand("SUB " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
            return true;
        }
        else if(toSolve.op == OpExp.GE)
        {
            addCommand("SUB " + String.valueOf(tempReg1) + ", " +  String.valueOf(tempReg1) + ", " + String.valueOf(tempReg2));
            return true;
        }

        addCommand("ST " + String.valueOf(tempReg1) + ", " + String.valueOf(resultMem) + "(" + String.valueOf(MemStartReg) + ")");
        return false;
        // put result in result memory
    }
    public void solveOpExp(OpExp toSolve, Integer resultReg, Integer tempReg)
    {
        Integer returnState = currentTemp;
        tempVariables.put(String.valueOf(currentTemp), currentTemp + tempVarsOffset);
        currentTemp++;
        boolean isComparison = recursiveSolve(toSolve, returnState + tempVarsOffset, resultReg, tempReg);
        if(!isComparison)
        {
            addCommand("LD " + String.valueOf(resultReg) + ", " + String.valueOf(returnState + tempVarsOffset) + "(" + String.valueOf(MemStartReg) + ")");
        }
        tempVariables.clear();
        currentTemp = 0;

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
        List<String> commands = new ArrayList<String>();
        currentCommandList = commands;
        prelude();
        tree.accept(this, 0);
        finale();
        buildOutput();
        return toReturn;
    } 
}