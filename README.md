# f1korea
f1 커뮤니티 사이트 입니다.

## db 빌드

user테이블
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
