package vn.techmaster.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Hotel {
    private int id;
    private String name;
    private String location;
    private double rating;
    private double price;
    private String image;
}
