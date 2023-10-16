package repository;

public interface Repository {
    void appendLog(String text);

    void saveInLog(String text);

    String readLog();
}
