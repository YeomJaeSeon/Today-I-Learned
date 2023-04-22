package com.example.order.domain.partner;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PartnerInfo {
    private Long id;
    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;
    private Partner.Status status;

    public PartnerInfo(Partner partner) {
        this.id = partner.getId();
        this.partnerToken = getPartnerToken();
        this.partnerName = partner.getPartnerName();
        this.businessNo = partner.getBusinessNo();
        this.email = partner.getEmail();
        this.status = partner.getStatus();
    }
}
