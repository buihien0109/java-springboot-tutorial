package vn.techmaster.jpatutorial.id.id_composite_id_class;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
Note :
The composite primary key class must be public.
It must have a no-arg constructor.
It must define the equals() and hashCode() methods.
It must be Serializable.
*/
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserId implements Serializable {
    private int id;
    private String name;
}
