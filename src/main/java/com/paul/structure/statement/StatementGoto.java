package com.paul.structure.statement;

import com.paul.core.Interpreter;

public class StatementGoto extends Statement {

  private int jump;

  public StatementGoto(Interpreter interpreter, int jump) {
    super(interpreter);
    this.jump = jump;
  }

  @Override
  public void execute() {
    interpreter.setGoto(jump);
  }
}
