package com.hwan.book.springboot.web.dto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
// assert 말고 assertj 쓴 이유
// 장점 : CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음
// junit의 asserthat을 쓰게되면 is()와 같이 CoreMatchers 라이브러리가 필요함
// 자동완성이 확실하게 지원됨
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        // assertj 라는 테스트 검증 라이브러리의 검증 메서드
        // 검증하고 싶은 대상을 메서드 인자로 받음
        // 메소드 체이닝이 지원되어서 isequalto와 같이 메소드를 이어서 사용가능

        // isEqualTo : assertj의 동등 비교 메서드 that 에 있는 값과
        // isEqualTo의 값을 비교해서 같을 때만 성공
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
