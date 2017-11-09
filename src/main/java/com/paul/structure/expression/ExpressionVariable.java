package com.paul.structure.expression;

import com.paul.core.Interpreter;

public class ExpressionVariable extends Expression<Integer> {

  private String value;

  public ExpressionVariable(Interpreter interpreter, String value) {
    super(interpreter);

    this.value = value;
  }

  public String getVariableName() {
    return value;
  }

  @Override
  public Integer evaluate() {
    return interpreter.variables.get(value);
  }
}
