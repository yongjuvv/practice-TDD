package com.yong.membership.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MembershipRequest {

    @NotNull
    @Min(0)
    private Integer point;

    @NotNull
    private MembershipType membershipType;
}
