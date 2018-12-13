package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread
    {

        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + " join to chat.");
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + " leave the chat.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this)
            {
                Client.this.notify();
            }
        }


        protected void clientHandshake() throws IOException, ClassNotFoundException
        {
            Message message;
            while(!clientConnected)
            {
                message = connection.receive();


                    if(message.getType()==MessageType.NAME_REQUEST) connection.send(new Message(MessageType.USER_NAME,getUserName()));

                    else if(message.getType()==MessageType.NAME_ACCEPTED) notifyConnectionStatusChanged(true);

                    else
                        throw  new IOException("Unexpected MessageType");

            }
        }



        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            Message message;
            while (!clientConnected){

                    if(currentThread().isInterrupted())
                    {
                        break;
                    }
                    message = connection.receive();
                    if (message.getType()==MessageType.TEXT)
                            processIncomingMessage(message.getData());
                    else if(message.getType()==MessageType.USER_ADDED)
                            informAboutAddingNewUser(message.getData());

                    else if(message.getType()==MessageType.USER_REMOVED)
                            informAboutDeletingNewUser(message.getData());
                    else
                           throw new IOException("Unexpected MessageType");


            }
        }

        @Override
        public void run() {

            String adressServer = null;
            Socket socket = null;
            int port = 0;
            try {
                adressServer = getServerAddress();
                port = getServerPort();
                socket = new Socket(adressServer, port);
                Connection connection = new Connection(socket);
                Client.this.connection = connection;

                    clientHandshake();
                    clientMainLoop();



            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            }
            catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }



    }

    protected String getServerAddress() throws IOException {
        String address;
        ConsoleHelper.writeMessage("Write a server address :");
        address = ConsoleHelper.readString();
        return address;
    }

    protected int getServerPort() throws IOException {
        int port;
        ConsoleHelper.writeMessage("Write a server port : ");
        port = ConsoleHelper.readInt();
        return port;
    }

    protected String getUserName() throws IOException, ClassNotFoundException {
        String name;
        ConsoleHelper.writeMessage("Write name user : ");
        name = ConsoleHelper.readString();
        return name;
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Error to send mail.");
            clientConnected = false;
        }
    }

    public void run()
    {

        {
            SocketThread socketThread = getSocketThread();
            socketThread.setDaemon(true);
            socketThread.start();
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Ошибка потока...");
                System.exit(1);
            }
            if (clientConnected) {
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’");
                while (clientConnected) {
                    String message = ConsoleHelper.readString();
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    } else {
                        if (shouldSendTextFromConsole()) {
                            sendTextMessage(message);
                        }
                    }
                }
            } else {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
        }
    }



    public static void main(String[] args) {

        Client client = new Client();
        client.run();


    }


}
