package br.ufsm.csi.redes.carvanha.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.ufsm.csi.redes.carvanha.model.Payload;
import br.ufsm.csi.redes.carvanha.model.Response;
import br.ufsm.csi.redes.carvanha.server.Server;
import lombok.SneakyThrows;

public class PacketSender {
    private DatagramSocket socket;

    public PacketSender(DatagramSocket socket) {
        this.socket = socket;
    }

    @SneakyThrows
    private byte[] getBuffer(Payload payload) {
        return payload.toJSON().getBytes("UTF-8");
    }

    @SneakyThrows
    private Payload getPayload(byte[] buffer, int length) {
        String json = new String(buffer, 0, length, "UTF-8");

        return Payload.fromJSON(json);
    }

    public void send(Payload payload, InetAddress address) {
        this.send(payload, address, Server.PORT);
    }

    @SneakyThrows
    public void send(Payload payload, InetAddress address, int port) {
        byte[] buffer = this.getBuffer(payload);
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, address, port);

        this.socket.send(packet);
    }

    @SneakyThrows
    public Response receive() {
        byte[] buffer = new byte[1024];
        DatagramPacket response = new DatagramPacket(buffer, 0, buffer.length);

        this.socket.receive(response);

        return Response
                .builder()
                .address(response.getAddress())
                .port(response.getPort())
                .data(this.getPayload(response.getData(), response.getLength()))
                .build();
    }
}
