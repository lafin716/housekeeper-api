package com.lafin.housekeeper.service;

import com.lafin.housekeeper.dto.request.MemberJoinRequest;
import com.lafin.housekeeper.entity.Member;
import com.lafin.housekeeper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다."));
    }
}
