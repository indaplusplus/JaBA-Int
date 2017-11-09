package com.paul.structure.statement;

import com.paul.core.Interpreter;
import com.paul.structure.expression.Expression;

public class StatementPrint extends Statement {

  public StatementPrint(Interpreter interpreter) {
    super(interpreter);
  }

  @Override
  public void execute() {
    for (Expression expression : expressions) {
      System.out.println(expression.evaluate());
    }
  }
}
