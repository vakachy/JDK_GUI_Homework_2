package server;

import client.Client;
import repository.Repo;
import repository.Repository;

import java.awt.*;
import java.util.List;

public class Server {
    private List<Client> clients;

    public ServerView getServerView() {
        return serverView;
    }

    private final ServerView serverView;
    private final Repository repository;
    boolean work;
    public static final String LOG_PATH = "src/server/log.txt";

    public Server() {
        this.serverView = new ServerWindow(this);
        this.repository = new Repo(this);
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clients.add(client);
        return true;
    }

    public String getHistory() {
        return repository.readLog();
    }


    public void sendMessage(String text) {
        if (!work) {
            return;
        }
        text += "";
        serverView.appendLog(text);
        answerAll(text);
        repository.saveInLog(text);
    }

    public void disconnectUser(Client client) {
        clients.remove(client);
        if (client != null) {
            client.disconnect();
        }
    }

    private void answerAll(String text) {
        for (Client client : clients) {
            client.answer(text);
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public Point getServerWindowSize() {
        return serverView.getDimension();
    }

}
