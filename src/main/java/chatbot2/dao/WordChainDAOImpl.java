package chatbot2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import chatbot2.model.Message;
import chatbot2.util.DatabaseConnectionUtil;

public class WordChainDAOImpl implements WordChainDAO {

    @Override
    public List<Message> findAll(long userId) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT msg_id, word, user_id FROM word_chain WHERE user_id = ? ORDER BY msg_id DESC";

        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setMessageId(rs.getLong("msg_id"));
                    message.setText(rs.getString("word"));
                    messages.add(message);
                }
            }
        }

        return messages;
    }

    @Override
    public void add(long userId, String word) throws SQLException {
        String sql = "INSERT INTO word_chain (word, user_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, word);
            pstmt.setLong(2, userId);

            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteAllByUserId(long userId) throws SQLException {
        String sql = "DELETE FROM word_chain WHERE user_id = ?";

        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, userId);
            pstmt.executeUpdate();
        }
    }
}