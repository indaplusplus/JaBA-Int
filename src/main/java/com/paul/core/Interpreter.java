package com.paul.core;

import com.paul.structure.Code;
import com.paul.structure.Line;
import com.paul.structure.statement.Statement;
import java.util.HashMap;
import java.util.Stack;

public class Interpreter {

  public final HashMap<String, Integer> variables = new HashMap<>();
  public final Stack<Integer> stack = new Stack<>();

  private Code code;

  private int jump = -1;
  private int gosub = -1;

  public Interpreter(Code code) {
    this.code = code;
  }

  public void run() {
    Tokenizer tokenizer = new Tokenizer();
    Line line = code.getLineFromIndex(0);

    while (line != null) {
      Parser parser = new Parser(this, line);
      Statement statement = parser.parse();
      statement.execute();

      if (jump == -1) {
        line = code.getNextLine(line.getLineNumber());
      } else {
        line = code.getLine(jump);
        jump = -1;
      }
    }
  }

  public Code getCode() {
    return code;
  }

  public void setGoto(int line) {
    this.jump = line;
  }

  public void setGosub(int gosub) {
    this.gosub = gosub;
  }

  public int getGosub() {
    return gosub;
  }
}
