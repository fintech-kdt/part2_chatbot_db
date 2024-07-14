package chatbot2.test;

import chatbot2.util.DatabaseConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        testDatabaseConnection();
        testConnectionClose();
    }

    private static void testDatabaseConnection() {
        Connection connection = null;
        try {
            connection = DatabaseConnectionUtil.getConnection();
            if (connection != null && !connection.isClosed()) {
                System.out.println("데이터베이스 연결 성공!");
            } else {
                System.out.println("데이터베이스 연결 실패.");
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 중 예외 발생: " + e.getMessage());
        } finally {
            DatabaseConnectionUtil.closeConnection(connection);
        }
    }

    private static void testConnectionClose() {
        Connection connection = null;
        try {
            connection = DatabaseConnectionUtil.getConnection();
            DatabaseConnectionUtil.closeConnection(connection);
            if (connection.isClosed()) {
                System.out.println("데이터베이스 연결 종료 성공!");
            } else {
                System.out.println("데이터베이스 연결 종료 실패.");
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 종료 중 예외 발생: " + e.getMessage());
        }
    }
}