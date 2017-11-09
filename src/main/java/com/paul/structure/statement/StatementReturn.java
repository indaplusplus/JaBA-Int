package com.paul.structure.statement;

import com.paul.core.Interpreter;

public class StatementReturn extends Statement {

  public StatementReturn(Interpreter interpreter) {
    super(interpreter);
  }

  @Override
  public void execute() {
    int gosub = interpreter.getGosub();
    if (gosub != -1) {
      interpreter.setGoto(gosub);
      interpreter.setGosub(-1);
    }
  }
}
