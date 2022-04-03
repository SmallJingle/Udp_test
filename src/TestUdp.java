import org.junit.Test;

public class TestUdp {
    @Test
    public void test() {
        new Thread(new Send(5555, "127.0.0.1", 9999)).start();
        new Thread(new Receive(6666)).start();
    }

    public static void main(String[] args) {
        new Thread(new Send(5555, "127.0.0.1", 9999)).start();
        new Thread(new Receive(6666)).start();
    }
}
