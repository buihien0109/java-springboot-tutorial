package com.example.mutilpledatabasemongo.domain.post;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private String title;
}
