package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by barabashka on 13.07.17.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Сервер запущен.");

            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка сервера. Сервер остановлен.");
        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME) {
                    if (!answer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(answer.getData())) {
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return answer.getData();
                        }
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair: connectionMap.entrySet()) {
                if (!pair.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message != null && message.getType() == MessageType.TEXT) {
                    String data = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, data));
                } else {
                    ConsoleHelper.writeMessage("Ошибка!");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Установленно новое соединение с " + socket.getRemoteSocketAddress());
            String clientName = null;
            try (Connection connection = new Connection(socket)){
                ConsoleHelper.writeMessage("Соединение через порт " + connection.getRemoteSocketAddress());
                clientName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                sendListOfUsers(connection, clientName);
                serverMainLoop(connection, clientName);
            } catch (IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage("Ошибка связи с удаленным адресом");
            } finally{
                if (clientName != null){
                    connectionMap.remove(clientName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
                    ConsoleHelper.writeMessage(String.format("Соединение с удаленным адресом (%s) закрыто.", socket.getRemoteSocketAddress()));
                }
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Connection connection: connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка отправки сообщения.");
            }
        }
    }
}
