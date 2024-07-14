package chatbot2.dao;

import java.sql.SQLException;
import java.util.List;
import chatbot2.model.Message;

public interface WordChainDAO {
    List<Message> findAll(long userId) throws SQLException;
    void add(long userId, String word) throws SQLException;
    void deleteAllByUserId(long userId) throws SQLException;
}