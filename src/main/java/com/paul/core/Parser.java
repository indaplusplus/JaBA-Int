package com.paul.core;

import com.paul.structure.Line;
import com.paul.structure.Token;
import com.paul.structure.expression.Expression;
import com.paul.structure.expression.ExpressionArithmetic;
import com.paul.structure.expression.ExpressionInteger;
import com.paul.structure.expression.ExpressionString;
import com.paul.structure.expression.ExpressionVariable;
import com.paul.structure.statement.Statement;
import com.paul.structure.statement.StatementEnd;
import com.paul.structure.statement.StatementGosub;
import com.paul.structure.statement.StatementGoto;
import com.paul.structure.statement.StatementIf;
import com.paul.structure.statement.StatementInput;
import com.paul.structure.statement.StatementLet;
import com.paul.structure.statement.StatementPrint;
import com.paul.structure.statement.StatementReturn;
import java.util.ArrayList;

public class Parser {

  private Interpreter interpreter;
  private Line line;
  private ArrayList<Token> tokens;
  private Token current;

  public Parser(Interpreter interpreter, Line line) {
    this.interpreter = interpreter;
    this.line = line;
    this.tokens = new Tokenizer().getTokens(line);
    this.current = tokens.get(0);
  }

  public Statement parse() {
    switch (current.getValue()) {
      case "end":
        return new StatementEnd(interpreter);

      case "print":
        consume();
        StatementPrint statement = new StatementPrint(interpreter);

        while (current != null &&
            (current.getType() == Token.Type.SPLITTER
            || current.getType() == Token.Type.VARIABLE
            || current.getType() == Token.Type.NUMBER
            || current.getType() == Token.Type.OPERATOR
            || current.getType() == Token.Type.STRING)) {
          if (current.getType() == Token.Type.STRING) {
            statement.expressions.add(new ExpressionString(interpreter, current.getValue()));
            consume();
          } else if (current.getType() != Token.Type.SPLITTER) {
            statement.expressions.add(parseExpression());
          } else {
            consume();
          }
        }

        return statement;
      case "goto":
        consume();
        return new StatementGoto(interpreter, Integer.valueOf(current.getValue()));
      case "gosub":
        consume();
        return new StatementGosub(interpreter, line,
            new ExpressionInteger(interpreter, Integer.valueOf(current.getValue())));
      case "let":
        consume();

        Expression variable = new ExpressionVariable(interpreter, current.getValue());
        consume();

        if (current.getValue().equals("=")) {
          consume();

          return new StatementLet(interpreter, variable, parseExpression());
        }
      case "return":
        return new StatementReturn(interpreter);
      case "if":
        consume();

        Expression left = parseExpression();

        String operator = current.getValue();
        consume();

        Expression right = parseExpression();

        consume();

        Statement then = parse();
        return new StatementIf(interpreter, operator, left, right, then);
      case "input":
        consume();

        StatementInput input = new StatementInput(interpreter);

        while (current != null &&
            (current.getType() == Token.Type.SPLITTER || current.getType() == Token.Type.VARIABLE)) {
          if (current.getType() == Token.Type.VARIABLE) {
            input.variables.add(new ExpressionVariable(interpreter, current.getValue()));
          }
          consume();
        }

        return input;
    }

    return null;
  }

  public Expression parseExpression() {
    if (current.getType() == Token.Type.OPERATOR &&
        (current.getValue().equals("+") || current.getValue().equals("-"))) {
      consume();
    }

    Expression expr = parseTerm();

    while (current != null
        && current.getType() == Token.Type.OPERATOR
        && (current.getValue().equals("+") || current.getValue().equals("-"))) {
      String operator = current.getValue();
      consume();
      expr = new ExpressionArithmetic(interpreter, operator, expr, parseTerm());
    }

    return expr;
  }

  public Expression parseTerm() {
    Expression expr = parseFactor();

    while (current != null
        && current.getType() == Token.Type.OPERATOR
        && (current.getValue().equals("*") || current.getValue().equals("/"))) {
      String operator = current.getValue();
      consume();
      expr = new ExpressionArithmetic(interpreter, operator, expr, parseFactor());
    }

    return expr;
  }

  public Expression parseFactor() {
    switch(current.getType()) {
      case NUMBER:
        Expression expr = new ExpressionInteger(interpreter, Integer.valueOf(current.getValue()));
        consume();
        return expr;
      case VARIABLE:
        expr = new ExpressionVariable(interpreter, current.getValue());
        consume();
        return expr;
      case LEFT_PARENTHESIS:
        consume();
        expr = parseExpression();
        consume();
        return expr;
    }

    return null;
  }

  public void consume() {
    if (!tokens.isEmpty()) {
      tokens.remove(0);

      if (tokens.isEmpty()) {
        current = null;
      } else {
        current = tokens.get(0);
      }
    }
  }
}
