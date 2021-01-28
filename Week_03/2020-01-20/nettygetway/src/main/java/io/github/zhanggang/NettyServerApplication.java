package io.github.zhanggang;

import io.github.zhanggang.inbound.HttpInboundServer;

import java.util.Arrays;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    public static void main(String[] args) {
        String proxtPort = System.getProperty("proxyPort", "9999");

        String proxyServers = System.getProperty("proxyServers", "http://localhost:8801,http://localhost:8802,http://localhost:8802");

        int port = Integer.parseInt(proxtPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for server:" + server.toString());

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
