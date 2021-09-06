package com.lafin.housekeeper.service;

import com.lafin.housekeeper.configuration.security.JwtTokenProvider;
import com.lafin.housekeeper.dto.request.MemberJoinRequest;
import com.lafin.housekeeper.entity.Member;
import com.lafin.housekeeper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    public Member join(MemberJoinRequest memberJoinRequest) {
        var member = new Member();
        member.setEmail(memberJoinRequest.getEmail());
        member.setName(memberJoinRequest.getName());
        member.setPassword(passwordEncoder.encode(memberJoinRequest.getPassword()));
        member.setRoles(Collections.singletonList("ROLE_USER"));

        return memberRepository.save(member);
    }

    public String getAccessToken(String email, String password) {
        var member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("회원 정보를 찾을 수 없습니다.");
        }

        return jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
    }

}
