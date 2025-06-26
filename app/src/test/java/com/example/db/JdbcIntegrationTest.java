package com.example.db;

import static org.junit.jupiter.api.Assertions.*;

import com.example.config.JdbcConfig;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class JdbcIntegrationTest {
  @Autowired private JdbcTemplate jdbcTemplate;

  @Test
  void jdbcTemplateIsAutowired() {
    assertNotNull(jdbcTemplate);
  }

  @BeforeEach
  void setup() {
    jdbcTemplate.execute("DROP TABLE IF EXISTS users");
    jdbcTemplate.execute(
        """
            CREATE TABLE users (
                id INT PRIMARY KEY,
                name VARCHAR(50)
            )
        """);
  }

  @Test
  void insertAndQueryUser() {
    jdbcTemplate.update("INSERT INTO users (id, name) VALUES (?, ?)", 1, "Alice");
    String name =
        jdbcTemplate
            .query(
                "SELECT name FROM users WHERE id = 1",
                (resultSet, rowNum) -> resultSet.getString("name"))
            .getFirst();
    assertEquals("Alice", name);
  }

  @Test
  void insertMultipleAndListAll() {
    jdbcTemplate.update("INSERT INTO users (id, name) VALUES (?, ?)", 1, "Alice");
    jdbcTemplate.update("INSERT INTO users (id, name) VALUES (?, ?)", 2, "Bob");

    List<String> names =
        jdbcTemplate.query("SELECT name FROM users", (rs, rowNum) -> rs.getString("name"));

    assertEquals(2, names.size());
    assertTrue(names.contains("Alice"));
    assertTrue(names.contains("Bob"));
  }

  @Test
  void deleteUserAndVerify() {
    jdbcTemplate.update("INSERT INTO users (id, name) VALUES (?, ?)", 1, "Alice");
    int deleted = jdbcTemplate.update("DELETE FROM users WHERE id = ?", 1);

    assertEquals(1, deleted);

    Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
    assertEquals(0, count);
  }

  @Test
  void tableShouldBeEmptyInitially() {
    Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
    assertEquals(0, count);
  }
}
