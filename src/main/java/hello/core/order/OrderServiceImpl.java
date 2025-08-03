package hello.core.order;

import com.sun.source.tree.UsesTree;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 새로운 할인 정책 시 메서드 변경
    private final MemberRepository memberRepository; // 생성자로 스프링 빈 생성자 final을 시용하여 불변함, 생성자에서 누락시 컴파일 오류
    private final DiscountPolicy discountPolicy;

    // AppConfig로 MemoryMemberRepository, FixDiscountPolicy 할당
    //@Autowired // 생성자가 1개 인 경우 @Autowired 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // lombok 사용으로 주석처리
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
