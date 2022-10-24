package br.ufsm.csi.redes.carvanha.server;

import java.net.InetAddress;
import java.util.Scanner;

import br.ufsm.csi.redes.carvanha.model.Payload;
import br.ufsm.csi.redes.carvanha.model.Response;
import br.ufsm.csi.redes.carvanha.model.Payload.Type;
import br.ufsm.csi.redes.carvanha.util.PacketSender;
import lombok.SneakyThrows;

public class Client implements Runnable {
    @Override
    @SneakyThrows
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter hostname: ");
            String hostname = scanner.nextLine();

            InetAddress address = InetAddress.getByName(hostname);

            PacketSender sender = new PacketSender();

            Payload ping = Payload
                    .builder()
                    .type(Type.PING)
                    .name(name)
                    .build();

            sender.send(ping, address);

            Response response = sender.receive();

            if (response.getData().getType() == Type.PONG) {
                System.out.println(String.format("PING: %d ms",
                        (System.nanoTime() - response.getData().getTime()) / 1_000_000));
            }

            while (true) {
                String message = scanner.nextLine();

                Payload payload = Payload
                        .builder()
                        .name(name)
                        .content(message)
                        .build();

                sender.send(payload, address);
            }
        }
    }
}
