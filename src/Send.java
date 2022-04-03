
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Send implements Runnable {
    private int fromPort; //自身端口
    private String toIp; //接收方Ip
    private int toPort; //接收方端口

    private DatagramSocket datagramSocket = null;
    private BufferedReader bufferedReader = null;

    public Send(int fromPort, String toIp, int toPort) {
        this.fromPort = fromPort;
        this.toIp = toIp;
        this.toPort = toPort;

        try {
            datagramSocket = new DatagramSocket(fromPort);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = bufferedReader.readLine();
                byte[] bytes = data.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress(this.toIp, this.toPort));
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
                datagramSocket.close();
            }
        }
    }
}
