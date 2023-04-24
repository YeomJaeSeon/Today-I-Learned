package com.example.order.domain.partner;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PartnerCriteria {
    private final String partnerToken;
}
