package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMenber(Long Id) {
        return memberRepository.findById(Id);
    }
}
