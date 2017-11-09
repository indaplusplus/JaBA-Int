package com.paul.structure.statement;

import com.paul.core.Interpreter;
import com.paul.structure.expression.Expression;
import com.paul.structure.expression.ExpressionVariable;

public class StatementLet extends Statement {

  private Expression variable, value;

  public StatementLet(Interpreter interpreter, Expression variable, Expression value) {
    super(interpreter);
    this.variable = variable;
    this.value = value;
  }

  @Override
  public void execute() {
    interpreter.variables.put(((ExpressionVariable) variable).getVariableName(), (int) value.evaluate());
  }
}
