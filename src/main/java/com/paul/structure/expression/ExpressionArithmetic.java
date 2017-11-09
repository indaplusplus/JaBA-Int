package com.paul.structure.expression;

import com.paul.core.Interpreter;

public class ExpressionArithmetic extends Expression<Integer> {

  private String operator;
  private Expression left, right;

  public ExpressionArithmetic(Interpreter interpreter, String operator, Expression left, Expression right) {
    super(interpreter);
    this.operator = operator;
    this.left = left;
    this.right = right;
  }

  @Override
  public Integer evaluate() {
    int leftValue = (int) left.evaluate();
    int rightValue = (int) right.evaluate();

    int value = -1;
    switch(operator) {
      case "+":
        value = leftValue + rightValue;
        break;
      case "-":
        value = leftValue - rightValue;
        break;
      case "*":
        value = leftValue * rightValue;
        break;
      case "/":
        value = leftValue / rightValue;
        break;
    }

    return value;
  }
}
