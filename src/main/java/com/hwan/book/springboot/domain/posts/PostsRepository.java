package com.hwan.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
public interface PostsRepository extends JpaRepository<Posts, Long> {
}

// 보통 Dao 라고 불리는 DB Layer 접근자임, JPA에선 Repository라고 부르며, 인터페이스로 생성함
// 인터페이스를 생성 후, JpaRepository<엔티티 클래스, PK의 타입> 상속하면 기본적인 CRUD 메소드가 자동적으로 생성됨

// 엔티티 클래스와 엔티티 Repository는 함께 위치해야한다는 점임
// 엔티티 클래스는 기본 Repository 없이는 제대로 역할을 할 수 없음
// 그래서 함께 움직여야 하므로 도메인 패키지에서 함께 관리함

// 이제 테스트 코드를 작성해서 기능을 검증하겠음