import junit.framework.TestCase;

import com.paul.structure.Code;

public class TestInsertCode extends TestCase {

  public TestInsertCode() {
    super("Test Insert Code");
  }

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertionRemoval() {
    Code code = new Code();

    code.insertLine("nonsense");
    code.insertLine("this is also nonsense");

    assertEquals(code.getLineAmount(), 0);

    code.insertLine("10 PRINT \"Hello, World!\"");
    assertEquals(code.getLineAmount(), 1);

    code.insertLine("20 END");
    assertEquals(code.getLineAmount(), 2);

    assertEquals(code.getLine(20).getCompleteLineText(), "20 END");

    code.removeLine(20);

    assertEquals(code.getLineAmount(), 1);
    assertEquals(code.getLine(20), null);
  }

  public void testNextLine() {
    Code code = new Code();

    code.insertLine("10 PRINT \"Hello, World!\"");
    code.insertLine("20 END");

    assertEquals(code.getNextLine(10).getCompleteLineText(), "20 END");
    assertEquals(code.getNextLine(10).getLineText(), "END");
    assertEquals(code.getNextLine(20), null);
  }
}
