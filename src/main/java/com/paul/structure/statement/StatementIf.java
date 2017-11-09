package com.paul.structure.statement;

import com.paul.core.Interpreter;
import com.paul.structure.expression.Expression;

public class StatementIf extends Statement {

  private String operator;
  private Expression left, right;
  private Statement statement;

  public StatementIf(Interpreter interpreter,
      String operator,
      Expression left,
      Expression right,
      Statement statement) {
    super(interpreter);
    this.operator = operator;
    this.left = left;
    this.right = right;
    this.statement = statement;
  }

  @Override
  public void execute() {
    int leftVal = (int) left.evaluate();
    int rightVal = (int) right.evaluate();

    boolean run = false;

    switch (operator) {
      case "=":
        run = (leftVal == rightVal);
        break;
      case "><":
        run = (leftVal != rightVal);
        break;
      case "<":
        run = (leftVal < rightVal);
        break;
      case ">":
        run = (leftVal > rightVal);
        break;
      case ">=":
        run = (leftVal >= rightVal);
        break;
      case "<=":
        run = (leftVal <= rightVal);
        break;
    }

    if (run) {
      statement.execute();
    }
  }
}
