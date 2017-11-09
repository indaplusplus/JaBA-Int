package com.paul.structure.expression;

import com.paul.core.Interpreter;

public class ExpressionInteger extends Expression<Integer> {

  private int value;

  public ExpressionInteger(Interpreter interpreter, int value) {
    super(interpreter);
    this.value = value;
  }

  @Override
  public Integer evaluate() {
    return value;
  }
}
