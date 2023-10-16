import client.Client;
import server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        new Client(server);
        new Client(server);
    }
}