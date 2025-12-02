package iovi.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private Long id;

    private String name;

    private String email;

    private Timestamp createdAt;
}
