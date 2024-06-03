package com.yong.membership.repository;

import com.yong.membership.entity.Membership;
import com.yong.membership.entity.MembershipType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class MembershipRepositoryTest {
    @Autowired
    private MembershipRepository membershipRepository;


//    @Test
//    public void MembershipRepository가Null이아님() {
//        Assertions.assertThat(membershipRepository).isNotNull();
//    }

    @Test
    public void 멤버십등록() {
        //given
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();
        //when
        final Membership result = membershipRepository.save(membership);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(10000);

    }

    @Test
    public void 멤버십이존재하는지테스트() {
        //given
        Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();
        //when
        membershipRepository.save(membership);
        Membership findMembership = membershipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

        //then
        assertThat(findMembership).isNotNull();
        assertThat(findMembership.getId()).isNotNull();
        assertThat(findMembership.getId()).isEqualTo("userId");
        assertThat(findMembership.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(findMembership.getPoint()).isEqualTo(10000);
    }

    @Test
    public void 멤버십조회_사이즈가0() {
        //given

        //when
        List<Membership> membershipList = membershipRepository.findAllByUserId("userId");
        //then
        assertThat(membershipList).isEmpty();
    }

    @Test
    void 멤버십조회_사이즈가2() {
        //given
        Membership naverMembership = Membership.builder()
                .userId("userId")
                .point(10000)
                .membershipType(MembershipType.NAVER)
                .build();

        Membership kakaoMembership = Membership.builder()
                .userId("userId")
                .point(10000)
                .membershipType(MembershipType.KAKAO)
                .build();

        membershipRepository.save(naverMembership);
        membershipRepository.save(kakaoMembership);
        //when
        List<Membership> membershipList = membershipRepository.findAllByUserId("userId");

        //then
        assertThat(membershipList.size()).isEqualTo(2);
        assertThat(membershipList).contains(naverMembership, kakaoMembership);
    }
}
