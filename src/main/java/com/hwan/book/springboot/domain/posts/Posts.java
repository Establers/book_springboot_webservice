package com.hwan.book.springboot.domain.posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 롬복어노테션 Getter 메소드를 자동 생성
@NoArgsConstructor // 롬복 어노테이션 (기본 생성자 자동 추가)
@Entity // 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름 매칭함
public class Posts { // 실제 DB 테이블과 매칭될 클래스이며, 보통 Entity 클래스 라고함
    // JPA를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다는, 이 Entity 클래스의
    // 수정을 통해 작업을 함

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
    // 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨.
    private Long id;
    // 웬만하면 엔티티의 PK는 Long타입의 auto를 추천,
    // 주민등록번호와 같이 비즈니스 상 유니크 키나, 여러 키를 조합한 복합키르 PK를 잡을 경우 난감한 상황 발생 p.91
    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타내며
    // 굳이 하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨
    // 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용함
    // 문자열의 경우, VARCHAR(255)가 기본 값인데, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나
    // 그럴 때 사용되긴 함
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}

// Setter 메소드가 없는데 자바빈 규약을 생각하면ㅅ 게터 세터를 무작정 생성하는 경우가 있음
// 이렇게 되면 해당 크랫의 인스턴스 값들이 언제 어디서 변앻야하는 코등사응로 명확하게 구분할 수 없어서 추후에 복잡한
// 상황이 발생할 수 있습니다. 그래서, Entity 클래스에선느 절대 Setter를 만들지 않습니다.
