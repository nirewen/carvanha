package br.ufsm.csi.redes.carvanha;

import br.ufsm.csi.redes.carvanha.server.Client;
import br.ufsm.csi.redes.carvanha.server.Server;

public class App {
    public static void main(String[] args) {
        new Thread(new Server()).start();
        new Thread(new Client()).start();
    }
}
