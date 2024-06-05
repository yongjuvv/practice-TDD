package com.yong.membership.controller;

import com.yong.membership.entity.MembershipConstants;
import com.yong.membership.entity.MembershipRequest;
import com.yong.membership.entity.MembershipResponse;
import com.yong.membership.service.MembershipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.yong.membership.entity.MembershipConstants.*;

@RestController
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipResponse> addMembership(
            @RequestHeader(USER_ID_HEADER) String userId,
            @RequestBody @Valid MembershipRequest membershipRequest
            ) {

        MembershipResponse membershipResponse = membershipService.addMembership(userId, membershipRequest.getMembershipType(), membershipRequest.getPoint());
        return ResponseEntity.status(HttpStatus.CREATED).body(membershipResponse);
    }
}

