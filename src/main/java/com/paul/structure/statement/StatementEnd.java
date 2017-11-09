package com.paul.structure.statement;

import com.paul.core.Interpreter;

public class StatementEnd extends Statement {

  public StatementEnd(Interpreter interpreter) {
    super(interpreter);
  }

  @Override
  public void execute() {
    System.exit(0);
  }
}
