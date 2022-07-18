package com.example.sessioncookie.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private int id;
    private String name;
    private String[] routes;
}
