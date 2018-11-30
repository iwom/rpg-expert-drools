package sample;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {
    String name;
    String message;
    boolean manager;
    Department department;
}
