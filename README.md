# is-lab-1

Spring

<img width="256" height="256" alt="image" src="https://github.com/user-attachments/assets/bb1be401-d101-4ff5-b400-4c0e5521cdbe" />

## Entities and requirements

```java
public class Worker {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Organization organization; //Поле не может быть null
    private double salary; //Значение поля должно быть больше 0
    private Double rating; //Поле не может быть null, Значение поля должно быть больше 0
    private Position position; //Поле не может быть null
    private Status status; //Поле не может быть null
    private Person person; //Поле не может быть null
}
public class Coordinates {
    private long x; //Максимальное значение поля: 227
    private Double y; //Поле не может быть null
}
public class Organization {
    private Address officialAddress; //Поле может быть null
    private int annualTurnover; //Значение поля должно быть больше 0
    private int employeesCount; //Значение поля должно быть больше 0
    private String fullName; //Длина строки не должна быть больше 1638, Поле может быть null
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null
}
public class Person {
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Location location; //Поле может быть null
    private Integer height; //Поле может быть null, Значение поля должно быть больше 0
    private Country nationality; //Поле не может быть null
}
public class Address {
    private String street; //Длина строки не должна быть больше 182, Поле не может быть null
    private Location town; //Поле может быть null
}
public class Location {
    private double x;
    private long y;
    private Double z; //Поле не может быть null
    private String name; //Поле не может быть null
}
public enum Position {
    ENGINEER,
    LEAD_DEVELOPER,
    MANAGER_OF_CLEANING;
}
public enum Status {
    FIRED,
    HIRED,
    RECOMMENDED_FOR_PROMOTION,
    REGULAR,
    PROBATION;
}
public enum OrganizationType {
    COMMERCIAL,
    GOVERNMENT,
    OPEN_JOINT_STOCK_COMPANY;
}
public enum Color {
    BLUE,
    ORANGE,
    WHITE,
    BROWN;
}
public enum Country {
    UNITED_KINGDOM,
    SPAIN,
    SOUTH_KOREA;
}
```
