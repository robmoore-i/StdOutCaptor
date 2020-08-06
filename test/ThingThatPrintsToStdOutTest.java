import org.junit.jupiter.api.Test;

class ThingThatPrintsToStdOutTest {

    private final StdOutCaptor stdOutCaptor = new StdOutCaptor();

    @Test
    void printMessageToStdOut() {
        ThingThatPrintsToStdOut thing = new ThingThatPrintsToStdOut();
        stdOutCaptor.startCapture();
        thing.doThing();
        stdOutCaptor.endCapture();

        stdOutCaptor.assertLineCaptured("Doing a thing");
    }
}