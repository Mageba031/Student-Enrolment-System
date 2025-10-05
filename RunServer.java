package za.ac.cput.serverside;

public class RunServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.listen();
    }
}

