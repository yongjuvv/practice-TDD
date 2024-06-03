package com.yong.membership.service;

import com.yong.membership.entity.Membership;
import com.yong.membership.entity.MembershipResponse;
import com.yong.membership.entity.MembershipType;
import com.yong.membership.exception.MembershipErrorResult;
import com.yong.membership.exception.MembershipException;
import com.yong.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.yong.membership.exception.MembershipErrorResult.*;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipResponse addMembership(String userId, MembershipType membershipType, Integer point) {
        Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);

        //null이 아닌 경우 중복
        if (result != null) {
            throw new MembershipException(DUPLICATED_MEMBERSHIP_REGISTER);
        }

        Membership membership = Membership.builder()
                .userId(userId)
                .membershipType(membershipType)
                .point(point)
                .build();

        Membership savedMembership = membershipRepository.save(membership);

        return MembershipResponse.builder()
                .membershipType(savedMembership.getMembershipType())
                .id(savedMembership.getId())
                .build();

    }

}
