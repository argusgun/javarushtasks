package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Server {
    private static Map<String,Connection> connectionMap;

    static {
        connectionMap = new ConcurrentHashMap<String, Connection>();
    }

    private static class Handler extends Thread {
        private Socket socket;

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() != MessageType.USER_NAME) continue;
                String userName = answer.getData();
                if (userName == null || userName.isEmpty()) continue;
                if (connectionMap.containsKey(userName)) continue;
                connectionMap.put(userName, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return userName;
            }
        }

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, name));
//                    connectionMap.get(name).send(new Message(MessageType.USER_ADDED, "Добавлен новый пользователь с именем " + userName));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message m = connection.receive();
                if (m.getType() == MessageType.TEXT) {
                    String s = userName + ": " + m.getData();
                    Message mes = new Message(MessageType.TEXT, s);
                    sendBroadcastMessage(mes);
                } else {
                    ConsoleHelper.writeMessage("Ошибка!");
                }
            }
        }


        public void run(){
            ConsoleHelper.writeMessage(socket.getRemoteSocketAddress().toString());
            String nameUser = null;
            try {
                Connection connection = new Connection(socket);
                try {
                    nameUser = serverHandshake(connection);
                    sendBroadcastMessage(new Message(MessageType.USER_ADDED, nameUser));
                    sendListOfUsers(connection, nameUser);
                    serverMainLoop(connection, nameUser);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace( );
                    ConsoleHelper.writeMessage("ошибка при обмене данными с удаленным адресом");
                }
            } catch (Exception e) {
                e.printStackTrace( );
                ConsoleHelper.writeMessage("ошибка при обмене данными с удаленным адресом");
            }
            finally {
                if (nameUser != null){
                    connectionMap.remove(nameUser);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED,nameUser));
                }
            }
            ConsoleHelper.writeMessage("соединение с удаленным адресом закрыто");

        }
    }

    public static void sendBroadcastMessage(Message message){
        try{
            for (ConcurrentMap.Entry<String, Connection> pair : connectionMap.entrySet())
                pair.getValue().send(message);
        }catch(IOException e){
            System.out.println("Сообщение не отправлено");
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        System.out.println("Сервер запущен");

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (IOException e){
            System.out.println(" произошла ошибка.");
            serverSocket.close();
        }
    }

}
