package client;

import server.Server;

public class Client {
    private String name;
    private final ClientView clientView;

    private final Server server;
    private boolean connected;

    public Client(Server server) {
        this.clientView = new ClientGUI(this, server);
        this.server = server;
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)) {
            printText("Вы успешно подключились!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                printText(log);
            }
            return true;
        } else {
            printText("Подключение не удалось");
            return false;
        }
    }

    //мы посылаем
    public void sendMessage(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage(name + ": " + message);
            }
        } else {
            printText("Нет подключения к серверу");
        }
    }

    //нам посылают
    public void serverAnswer(String answer) {
        printText(answer);
    }

    public void disconnect() {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            printText("Вы были отключены от сервера!");
        }
    }

    public String getName() {
        return name;
    }

    private void printText(String text) {
        clientView.showMessage(text);
    }

    public void answer(String text) {
        server.getServerView().appendLog(text);

    }
}
