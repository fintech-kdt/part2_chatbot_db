package chatbot2.test;

import java.io.IOException;
import java.util.List;

import chatbot2.model.Message;
import chatbot2.model.Update;
import chatbot2.service.TelegramBotService;

public class TelegramBotServiceTest {

    private static TelegramBotService service;

    public static void main(String[] args) {
        service = new TelegramBotService();

        testGetUpdates();
        testSendMessage();
    }

    private static void testGetUpdates() {
        System.out.println("테스트: getUpdates()");
        try {
            List<Update> updates = service.getUpdates(0);
            if (updates != null && !updates.isEmpty()) {
                System.out.println("성공: 업데이트를 받았습니다.");
                System.out.println("받은 업데이트 수: " + updates.size());
                Update firstUpdate = updates.get(0);
                System.out.println("첫 번째 업데이트 ID: " + firstUpdate.getUpdateId());
                System.out.println("첫 번째 메시지 발송자: " +firstUpdate.getMessage().getChat().getId());
                System.out.println("첫 번째 메시지: " + firstUpdate.getMessage().getText());
            } else {
                System.out.println("실패: 업데이트를 받지 못했습니다.");
            }
        } catch (IOException e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void testSendMessage() {
        System.out.println("테스트: sendMessage()");
        long chatId = 5261784013L; // 실제 채팅 ID로 변경해야 합니다.
        String text = "테스트 메시지입니다.";
        try {
            Message sentMessage = service.sendMessage(chatId, text);
            if (sentMessage != null) {
                System.out.println("성공: 메시지를 보냈습니다.");
                System.out.println("보낸 메시지 ID: " + sentMessage.getMessageId());
                System.out.println("보낸 메시지 내용: " + sentMessage.getText());
            } else {
                System.out.println("실패: 메시지를 보내지 못했습니다.");
            }
        } catch (IOException e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}