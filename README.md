### pom.xml
```xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit -->
    <dependency>
        <groupId>com.squareup.retrofit2</groupId>
        <artifactId>retrofit</artifactId>
        <version>2.9.0</version>
    </dependency>
    <!--
    https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson -->
    <dependency>
        <groupId>com.squareup.retrofit2</groupId>
        <artifactId>converter-gson</artifactId>
        <version>2.9.0</version>
    </dependency>
</dependencies>
```
### DDL
```sql
-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS chatbot_db;

-- 생성된 데이터베이스 사용
USE chatbot_db;

-- 끝말잇기 게임을 위한 테이블 생성
CREATE TABLE IF NOT EXISTS word_chain (
    msg_id INT AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(50) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```