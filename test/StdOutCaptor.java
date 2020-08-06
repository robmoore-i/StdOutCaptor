import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class StdOutCaptor extends ByteArrayOutputStream {
    private List<String> lines = new ArrayList<>();
    private PrintStream originalOut;

    @Override
    public synchronized void write(byte[] b, int off, int len) {
        super.write(b, off, len);
        lines.add(new String(b));
    }

    public void startCapture() {
        this.lines = new ArrayList<>();
        this.originalOut = System.out;
        System.setOut(new PrintStream(this));
    }

    public void endCapture() {
        System.setOut(originalOut);
    }

    public void assertLineCaptured(String expectedText) {
        if (lines.stream().noneMatch(s -> s.contains(expectedText))) {
            throw new AssertionError("Expected captured lines to " +
                    "contain '" + expectedText + "', but was actually: " + lines);
        }
    }
}
