package sides;

import chavevalor.*;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class JavaClient {
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new  TBinaryProtocol(transport);
            ChaveValor.Client client = new ChaveValor.Client(protocol);
            
            perform(client);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(ChaveValor.Client client) throws TException {
        String buffer;

        buffer = client.setKV(1, "opa");
        System.out.println("Valor associado á chave anteriormente: " + buffer);
        System.out.println("Novo valor: " + client.getKV(1));
        System.out.println("");
        buffer = client.setKV(1, "hello");
        System.out.println("Valor associado á chave anteriormente: " + buffer);
        System.out.println("Novo valor: " + client.getKV(1));
    }
}
