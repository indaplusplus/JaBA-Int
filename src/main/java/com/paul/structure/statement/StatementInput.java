package com.paul.structure.statement;

import com.paul.core.Interpreter;
import com.paul.structure.expression.Expression;
import com.paul.structure.expression.ExpressionVariable;
import java.util.ArrayList;
import java.util.Scanner;

public class StatementInput extends Statement {

  public ArrayList<ExpressionVariable> variables = new ArrayList<>();

  public StatementInput(Interpreter interpreter) {
    super(interpreter);
  }

  @Override
  public void execute() {
    Scanner s = new Scanner(System.in);

    for (ExpressionVariable variable : variables) {
      interpreter.variables.put(variable.getVariableName(), s.nextInt());
    }

    s.close();
  }
}
