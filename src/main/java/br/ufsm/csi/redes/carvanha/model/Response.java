package br.ufsm.csi.redes.carvanha.model;

import java.net.InetAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    public InetAddress address;
    public int port;
    public Payload data;
}
