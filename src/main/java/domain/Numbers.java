package domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="NUMBERS" ,
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"num"}))

public class Numbers implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String num;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
