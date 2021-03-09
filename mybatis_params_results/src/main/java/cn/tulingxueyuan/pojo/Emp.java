package cn.tulingxueyuan.pojo;

import java.time.LocalDate;
import java.util.Date;

public class Emp {
    private Integer id;
    private String username;
    private LocalDate create_date;



    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", create_date=" + create_date +
                '}';
    }
    public LocalDate getCreate_date() {
        return create_date;
    }
    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
