f1korea
========
f1 커뮤니티 사이트 입니다.

 db 빌드
=========
import 생략
Getter, Setter 등 어노테이션 생략



users테이블
----------
```java
@Setter
@Getter
@Entity
@NoArgsConstructor // 기본생성자 생성 Users user = new Users();
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private  String email;

    @Column(name = "password", nullable = false)
    private  String password;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;
}
```

posts 테이블
---------
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "postId")
    private List<Comment> comments = new ArrayList<>();
}
```

comments 테이블
------------

```java
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;
}
```

categories 테이블
-------------

```java
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false)
    private long categoryId;

    @Column(name = "category_name", nullable = false)
    private String name;
}
```

post_category 테이블
-----------------

```java
@Table(name = "posts_categories")
public class PostCategory {

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
```


