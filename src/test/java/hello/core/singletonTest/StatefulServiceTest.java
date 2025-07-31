package hello.core.singletonTest;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void StatefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 10,000원 주문
        int userAOrder = statefulService1.order("userA", 10000); // 지역변수로 만들어 문제 해결
        // ThreadB : B사용자가 20,000원 주문
        int userBOrder = statefulService1.order("userB", 20000); // ,,

        // ThreadA : 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        //System.out.println("price = " + price); // 싱글톤의 단점
        System.out.println("userAOrder = " + userAOrder);
        System.out.println("userBOrder = " + userBOrder);

        Assertions.assertThat(userBOrder).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}