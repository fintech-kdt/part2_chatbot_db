package chatbot2;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import chatbot2.model.Message;
import chatbot2.model.Update;
import chatbot2.service.TelegramBotService;
import chatbot2.service.WordChainService;

public class Main {
    private static TelegramBotService telegramService;
    private static WordChainService wordChainService;
    private static long lastUpdateId = 0;

    public static void main(String[] args) {
        telegramService = new TelegramBotService();
        wordChainService = new WordChainService();

        while (true) {
            try {
                List<Update> updates = telegramService.getUpdates(lastUpdateId + 1);
                for (Update update : updates) {
                    processUpdate(update);
                    lastUpdateId = update.getUpdateId();
                }
                TimeUnit.SECONDS.sleep(1);  // 1초 대기
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void processUpdate(Update update) {
        Message message = update.getMessage();
        if (message != null && message.getText() != null) {
            long chatId = message.getChat().getId();
            String response = wordChainService.processWord(chatId, message.getText());
            try {
                telegramService.sendMessage(chatId, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}