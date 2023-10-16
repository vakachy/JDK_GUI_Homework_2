package server;

import java.awt.*;

public interface ServerView {
    Point getDimension();

    void disconnectUser();

    void appendLog(String text);
}
