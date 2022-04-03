
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receive implements Runnable {
    private int port; //服务端端口

    private DatagramSocket datagramSocket;

    public Receive(int port) {
        this.port = port;

        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] container = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(container, 0, container.length);
                datagramSocket.receive(datagramPacket);
                byte[] data = datagramPacket.getData();
                String receiveData = new String(data, 0, data.length);
                System.out.println(receiveData);
            } catch (IOException e) {
                e.printStackTrace();
                datagramSocket.close();
            }
        }
    }
}
