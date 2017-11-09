package com.paul.structure.statement;

import com.paul.core.Interpreter;
import com.paul.structure.expression.Expression;
import java.util.ArrayList;

public abstract class Statement {

  public ArrayList<Expression> expressions = new ArrayList<>();
  protected Interpreter interpreter;

  public Statement(Interpreter interpreter) {
    this.interpreter = interpreter;
  }

  public abstract void execute();
}
