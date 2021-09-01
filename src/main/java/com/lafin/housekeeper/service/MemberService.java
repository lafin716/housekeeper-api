package com.lafin.housekeeper.service;

import com.lafin.housekeeper.dto.request.MemberJoinRequest;
import com.lafin.housekeeper.entity.Member;
import com.lafin.housekeeper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> list() {
        return memberRepository.findAll();
    }

    public Member join(MemberJoinRequest memberJoinRequest) {
        var member = new Member();
        member.setEmail(memberJoinRequest.getEmail());
        member.setName(memberJoinRequest.getName());
        member.setPassword(memberJoinRequest.getPassword());

        return memberRepository.save(member);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow();
    }
}
