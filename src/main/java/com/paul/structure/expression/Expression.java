package com.paul.structure.expression;

import com.paul.core.Interpreter;

public abstract class Expression<T> {

  protected Interpreter interpreter;

  public Expression(Interpreter interpreter) {
    this.interpreter = interpreter;
  }

  public abstract T evaluate();
}
