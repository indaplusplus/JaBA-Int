import com.paul.util.LineClean;
import junit.framework.TestCase;

public class TestLineClean extends TestCase {

  public TestLineClean() {
    super("Test Line Clean");
  }

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testLineClean() {
    LineClean clean = new LineClean();

    assertEquals(clean.clean("this is nonsense"), "this is nonsense");
    assertEquals(clean.clean("   this is nonsense   "), "this is nonsense");
    assertEquals(clean.clean("       this is nonsense"), "this is nonsense");
    assertEquals(clean.clean(" this is nonsense  "), "this is nonsense");
  }
}
