package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.exception.LoginFailureException;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member memberA = new Member();
        memberA.setName("memberA");

        Member memberB = new Member();
        memberB.setName("memberA");

        //when
        memberService.join(memberA);

        //then
        //IllegalStateException을 캐치해야 함
        assertThrows(IllegalStateException.class, ()->{
            memberService.join(memberB);
        });

    }

    @Test
    public void 로그인_성공() throws Exception{
        //given
        Member memberA = new Member();
        memberA.setName("memberA");
        memberA.setPassword("passwordA");

        //when
        memberService.join(memberA);

        //then
        assertEquals(memberA.getId(),
                memberService.login("memberA", "passwordA"),
                "로그인 정보가 올바르면 성공해야 함.");
    }

    @Test
    public void 로그인_실패_없는아이디() throws Exception{
        //given
        Member memberA = new Member();
        memberA.setName("memberA");
        memberA.setPassword("passwordA");

        //when
        memberService.join(memberA);

        //then
        //존재하지 않는 아이디로 로그인 시 실패해야 함
        assertThrows(LoginFailureException.class, ()->{
            memberService.login("worngName", "passwordA");
        });
    }

    @Test
    public void 로그인_실패_틀린_비밀번호() throws Exception{
        //given
        Member memberA = new Member();
        memberA.setName("memberA");
        memberA.setPassword("passwordA");

        //when
        memberService.join(memberA);

        //then
        //존재하지 않는 아이디로 로그인 시 실패해야 함
        assertThrows(LoginFailureException.class, ()->{
            memberService.login("memberA", "wrongPassword");
        });
    }
}