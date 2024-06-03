package com.yong.membership.service;

import com.yong.membership.entity.Membership;
import com.yong.membership.entity.MembershipResponse;
import com.yong.membership.entity.MembershipType;
import com.yong.membership.exception.MembershipErrorResult;
import com.yong.membership.exception.MembershipException;
import com.yong.membership.repository.MembershipRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @InjectMocks
    private MembershipService target;

    @Mock
    private MembershipRepository membershipRepository;

    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;

    @Test
    public void 멤버십등록실패_이미존재() {
        //given
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, membershipType, point));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

//    @Test
//    public void 멤버십등록성공() {
//        //given
//        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
//        doReturn(membership()).when(membershipRepository).save(any(Membership.class));
//        //when
//        Membership result = target.addMembership(userId, membershipType, point);
//        //then
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isNotNull();
//        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
//
//        //verify
//        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
//        verify(membershipRepository, times(1)).save(any(Membership.class));
//    }

    @Test
    public void 멤버십등록성공_DTO() {
        //given
        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
        doReturn(membership()).when(membershipRepository).save(any(Membership.class));
        //when
        MembershipResponse result = target.addMembership(userId, membershipType, point);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);

        //verify
        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
        verify(membershipRepository, times(1)).save(any(Membership.class));
    }

    private Membership membership() {
        return Membership.builder()
                .id(-1L)
                .userId(userId)
                .point(point)
                .membershipType(MembershipType.NAVER)
                .build();
    }
}
