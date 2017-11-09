package com.paul;

import com.paul.core.Interpreter;
import com.paul.structure.Code;

public class Main {

  public static void main(String[] args) {
    Code code = new Code();

    code.insertLine("10 INPUT A");
    code.insertLine("20 GOSUB 40");
    code.insertLine("30 LET A = A * 2");
    code.insertLine("40 LET A = A + 1");
    code.insertLine("50 RETURN");
    code.insertLine("60 PRINT A");
    code.insertLine("70 END");

//    code.insertLine("05 LET A = 5");
//    code.insertLine("10 PRINT \"Hello, World!\"");
//    code.insertLine("15 LET A = A * 5");
//    code.insertLine("35 PRINT A");
//    code.insertLine("50 END");

    Interpreter interpreter = new Interpreter(code);

    interpreter.run();
  }
}
