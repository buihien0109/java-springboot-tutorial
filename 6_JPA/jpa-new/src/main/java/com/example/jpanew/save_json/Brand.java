package com.example.jpanew.save_json;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Brand {
    private Integer id;
    private String name;
    private String logo;
}
