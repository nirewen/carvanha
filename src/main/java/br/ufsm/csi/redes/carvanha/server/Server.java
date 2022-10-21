package br.ufsm.csi.redes.carvanha.server;

import java.net.DatagramSocket;

import br.ufsm.csi.redes.carvanha.model.Response;
import br.ufsm.csi.redes.carvanha.model.Payload.Type;
import br.ufsm.csi.redes.carvanha.util.PacketSender;
import lombok.SneakyThrows;

public class Server implements Runnable {
    public static final int PORT = 5555;

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            try (DatagramSocket socket = new DatagramSocket(PORT)) {
                PacketSender sender = new PacketSender(socket);

                Response response = sender.receive();
                response.data.setType(Type.PONG);

                System.out.println(response.data.content);
            }
        }
    }
}
