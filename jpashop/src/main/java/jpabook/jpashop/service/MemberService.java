package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional(readOnly = true)      
//데이터 변경이 있으면 반드시 이 어노테이션이 필요 (동기화 문제)
public class MemberService {
    //final 키워드를 통해 컴파일 시점에 오류를 체크할 수 있음
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원 가입 기능
    @Transactional  //join은 데이터를 변경시키므로 readonly를 false로 설정
    public Long join(Member member){
        //중복 이름 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //회원 전체 조회 기능
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    
    //회원 하나만 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
