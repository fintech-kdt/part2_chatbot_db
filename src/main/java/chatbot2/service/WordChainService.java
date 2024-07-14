package chatbot2.service;

import chatbot2.dao.WordChainDAO;
import chatbot2.dao.WordChainDAOImpl;
import chatbot2.model.Message;

import java.sql.SQLException;
import java.util.List;

public class WordChainService {
    private WordChainDAO dao;

    public WordChainService() {
        this.dao = new WordChainDAOImpl();
    }

    public String processWord(long userId, String word) {
        if ("/start".equalsIgnoreCase(word)) {
            try {
                dao.deleteAllByUserId(userId);
                return "끝말잇기 게임을 새로 시작합니다! 첫 단어를 입력해주세요.";
            } catch (SQLException e) {
                e.printStackTrace();
                return "게임 초기화 중 오류가 발생했습니다: " + e.getMessage();
            }
        }

        try {
            List<Message> recentMessages = dao.findAll(userId);
            
            if (recentMessages.isEmpty() || isValidWordChain(recentMessages.get(0).getText(), word)) {
                dao.add(userId, word);
                return "'" + word + "'가 성공적으로 추가되었습니다. 다음 단어를 입력해주세요.";
            } else {
                return "잘못된 단어입니다. 이전 단어의 마지막 글자로 시작하는 단어를 입력해주세요.\n최근 5개 단어: " + getRecentWords(recentMessages);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "오류가 발생했습니다: " + e.getMessage();
        }
    }

    private boolean isValidWordChain(String lastWord, String newWord) {
        if (lastWord == null || lastWord.isEmpty() || newWord == null || newWord.isEmpty()) {
            return false;
        }
        return lastWord.charAt(lastWord.length() - 1) == newWord.charAt(0);
    }

    private String getRecentWords(List<Message> messages) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(5, messages.size()); i++) {
            sb.append(messages.get(i).getText()).append(", ");
        }
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}