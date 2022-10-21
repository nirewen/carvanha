package br.ufsm.csi.redes.carvanha.server;

import java.net.DatagramSocket;

import br.ufsm.csi.redes.carvanha.model.Response;
import br.ufsm.csi.redes.carvanha.model.Payload.Type;
import br.ufsm.csi.redes.carvanha.util.PacketSender;
import lombok.SneakyThrows;

public class Server implements Runnable {
    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            try (DatagramSocket socket = new DatagramSocket(5555)) {
                PacketSender sender = new PacketSender(socket);

                Response response = sender.receive();
                response.data.setType(Type.PONG);

                sender.send(response.data, response.address, response.port);
            }
        }
    }
}
