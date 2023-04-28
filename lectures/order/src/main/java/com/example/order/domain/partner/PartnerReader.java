package com.example.order.domain.partner;

public interface PartnerReader {
    Partner getPartnerByToken(String partnerToken);
}
