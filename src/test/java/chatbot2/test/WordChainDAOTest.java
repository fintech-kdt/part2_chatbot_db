package chatbot2.test;

import chatbot2.dao.WordChainDAO;
import chatbot2.dao.WordChainDAOImpl;
import chatbot2.model.Message;

import java.sql.SQLException;
import java.util.List;

public class WordChainDAOTest {

    private static WordChainDAO dao;

    public static void main(String[] args) {
        dao = new WordChainDAOImpl();

        testAddAndFindAll();
    }

    private static void testAddAndFindAll() {
        System.out.println("테스트: add() 및 findAll() 메서드");

        long userId = 123456L; // 테스트용 사용자 ID
        String[] testWords = {"안녕하세요", "끝말잇기", "기러기", "기차"};

        try {
            // 테스트 데이터 추가
            for (String word : testWords) {
                dao.add(userId, word);
                System.out.println("단어 추가: " + word);
            }

            // 추가된 데이터 조회
            List<Message> messages = dao.findAll(userId);

            if (messages != null && !messages.isEmpty()) {
                System.out.println("성공: 메시지를 불러왔습니다.");
                System.out.println("불러온 메시지 수: " + messages.size());
                
                System.out.println("메시지 목록:");
                for (Message msg : messages) {
                    System.out.println("ID: " + msg.getMessageId() + ", 단어: " + msg.getText());
                }

                // 추가한 단어들이 모두 존재하는지 확인
                boolean allWordsFound = true;
                for (String word : testWords) {
                    boolean found = messages.stream().anyMatch(msg -> msg.getText().equals(word));
                    if (!found) {
                        System.out.println("오류: 단어 '" + word + "'를 찾을 수 없습니다.");
                        allWordsFound = false;
                    }
                }

                if (allWordsFound) {
                    System.out.println("성공: 모든 테스트 단어를 찾았습니다.");
                }
            } else {
                System.out.println("실패: 메시지를 불러오지 못했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
