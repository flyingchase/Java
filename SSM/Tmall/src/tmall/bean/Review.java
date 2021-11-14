package tmall.bean;

import java.util.Date;

public class Review {
    private int id;
    // 表示评价内容 对应数据库字段 content
    private String content;
    // 评价时间
    private Date creatDate;
    // 数据库字段 uid 外键约束 user 表的 id
    // 评价和用户是多对一
    private User user;
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
