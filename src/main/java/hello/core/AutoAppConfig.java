package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //  기존 예제코드를 살리기 위해 설정
        // basePackages = "hello.core.member" ==> 탐색 할 패키지의 시작 위치를 지정, 이 패키지를 포함하여 하위 패키지를 모두 탐색
        // basePackageClasses = AutoAppConfig.class ==> 지정한 클래스의 패키지를 탐색 시작 위치로 지정
        // 만약 지정하지 않은 시 @ComponentScan이 붙은 클래스의 패키지가 시작 위치가 된다. 여기에선 "hello.core"
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }
}
