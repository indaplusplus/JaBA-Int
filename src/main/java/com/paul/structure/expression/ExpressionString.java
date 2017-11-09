package com.paul.structure.expression;

import com.paul.core.Interpreter;

public class ExpressionString extends Expression<String> {

  private String string;

  public ExpressionString(Interpreter interpreter, String string) {
    super(interpreter);
    this.string = string;
  }

  @Override
  public String evaluate() {
    return string;
  }
}
