package com.yong.membership.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MembershipErrorResult {
    DUPLICATED_MEMBERSHIP_REGISTER(HttpStatus.BAD_REQUEST, "Duplicate Membership Register Requset");

    private final HttpStatus httpStatus;
    private final String message;



}