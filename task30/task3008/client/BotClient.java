package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message != null && message.contains(":")) {
                //тело метода

            String name = message.split(": ")[0];
            String text = message.split(": ")[1];
            String date=null;
                if(name != null && text != null) {
                    switch (text) {
                        case "дата":
                            SimpleDateFormat format = new SimpleDateFormat("d.MM.YYYY");
                            date = format.format(Calendar.getInstance().getTime());
                            break;
                        case "день":
                            SimpleDateFormat format1 = new SimpleDateFormat("d");
                            date = format1.format(Calendar.getInstance().getTime());
                            break;
                        case "месяц":
                            SimpleDateFormat format2 = new SimpleDateFormat("MMMM");
                            date = format2.format(Calendar.getInstance().getTime());
                            break;
                        case "год":
                            SimpleDateFormat format3 = new SimpleDateFormat("YYYY");
                            date = format3.format(Calendar.getInstance().getTime());
                            break;
                        case "время":
                            SimpleDateFormat format4 = new SimpleDateFormat("H:mm:ss");
                            date = format4.format(Calendar.getInstance().getTime());
                            break;
                        case "час":
                            SimpleDateFormat format5 = new SimpleDateFormat("H");
                            date = format5.format(Calendar.getInstance().getTime());
                            break;
                        case "минуты":
                            SimpleDateFormat format6 = new SimpleDateFormat("m");
                            date = format6.format(Calendar.getInstance().getTime());
                            break;
                        case "секунды":
                            SimpleDateFormat format7 = new SimpleDateFormat("s");
                            date = format7.format(Calendar.getInstance().getTime());
                            break;
                            default:break;

                    }
                    if(date!=null) sendTextMessage(String.format("Информация для %s: %s", name, date));
                }
            }
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            try {
                super.clientMainLoop();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        System.out.println(botClient.getUserName());
        botClient.run();
    }
}
