package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); // AppConfig를 통한 호출
//        MemberService memberService = new MemberServiceImpl();

        // @Bean을 붙인 메서드를 스프링 컨테이너에 넣어서 관리함
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMenber = memberService.findMenber(1L);
        System.out.println("new member : " + member.getName());
        System.out.println("find member : " + findMenber.getName());
    }
}
