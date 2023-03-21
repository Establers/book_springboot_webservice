package com.hwan.book.springboot.domain.posts;
// save findAll 기능 테스트

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // junit 에서 단위 테스트가 끝날 때 마다 수행되는 메소드를 지정
    // 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기위해 사용함
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아있어 다음 테스트 실행 시
    // 테스트가 실패할 수 있음
    public void cleanup(){
        postsRepository.deleteAll();
    }
    // test
    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // posts 테이블에 insert, update 쿼리를 실행함
                .title(title)
                .content(content)
                .author("establers@naver.com")
                .build());
        // id 값이 있따면 update, 없다면 insert 쿼리가 실행됨

        // when
        List<Posts> postsList = postsRepository.findAll(); // posts 테이블에 있는
        // 모든 데이터를 조회해오는 메소드

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

        // 쿼리 로그를 ON OFF 할 수 있는 기능이 있음
        // application.properties, applicattion.yml 등..
        // 한줄의 코드로 설정할 수 있고 권장됨
        // src/main/resources에 추가하면 됨
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="
        +posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
