package bean;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Employee {
    private Long id;
    @Excel(name = "用户名称")

    private String username;

    @Excel(name = "邮件",width = 20)
    private String email;


    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
