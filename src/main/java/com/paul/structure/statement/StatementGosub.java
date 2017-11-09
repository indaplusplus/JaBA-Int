package com.paul.structure.statement;

import com.paul.core.Interpreter;
import com.paul.structure.Line;
import com.paul.structure.expression.Expression;

public class StatementGosub extends Statement {

  private Line currentLine;
  private Expression jump;

  public StatementGosub(Interpreter interpreter, Line currentLine, Expression jump) {
    super(interpreter);
    this.currentLine = currentLine;
    this.jump = jump;
  }

  @Override
  public void execute() {
    interpreter.setGosub(interpreter.getCode().getNextLine(currentLine.getLineNumber()).getLineNumber());
    interpreter.setGoto((int) jump.evaluate());
  }
}
