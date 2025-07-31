package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        //ac.getBean("beanB", BeanB.class); // @MyExcludeComponent이기에 컴포넌트 스캔 대상에서 빠짐

        assertThrows(
                NoSuchBeanDefinitionException.class, // 스캔대상에서 빠져서 예외처리
                () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            // includeFilters : 스프링 빈에 등록 됨
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), // type = FilterType.ANNOTATION ==> 생략 가능(default)
            // excludeFilters : 스프링 빈에 등록 안됨
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
