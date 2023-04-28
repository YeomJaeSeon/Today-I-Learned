package com.example.order.domain.partner;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartnerCommand {
    private final String partnerName;
    private final String businessNo;
    private final String email;

    public Partner toEntity(){
        return Partner.builder()
                .partnerName(partnerName)
                .businessNo(businessNo)
                .email(email)
                .build();
    }
}
