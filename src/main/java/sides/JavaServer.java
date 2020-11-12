package sides;

import chavevalor.*;
import handlers.ChaveValorHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class JavaServer {
    public static ChaveValorHandler handler;
    public static ChaveValor.Processor processor;

    public static void main(String[] args) {
        Runnable simple = new Runnable() {
            public void run() {
                simple(processor);
            }
        };

        new Thread(simple).start();
    }

    private static void simple(ChaveValor.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
