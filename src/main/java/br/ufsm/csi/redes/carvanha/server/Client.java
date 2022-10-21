package br.ufsm.csi.redes.carvanha.server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import br.ufsm.csi.redes.carvanha.model.Payload;
import br.ufsm.csi.redes.carvanha.model.Payload.Type;
import br.ufsm.csi.redes.carvanha.util.PacketSender;
import lombok.SneakyThrows;

public class Client implements Runnable {
    @Override
    @SneakyThrows
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter hostname: ");
            String hostname = scanner.nextLine();

            while (true) {
                try (DatagramSocket socket = new DatagramSocket()) {
                    String message = scanner.nextLine();

                    Payload payload = Payload
                            .builder()
                            .type(Type.MESSAGE)
                            .time(System.nanoTime())
                            .content(message)
                            .build();

                    PacketSender sender = new PacketSender(socket);

                    sender.send(payload, InetAddress.getByName(hostname));
                }
            }
        }
    }
}
