package com.example.order.application.partner;

import com.example.order.domain.notification.NotificationService;
import com.example.order.domain.partner.PartnerCommand;
import com.example.order.domain.partner.PartnerInfo;
import com.example.order.domain.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    public PartnerInfo registerPartner(PartnerCommand command){
        var partnerInfo = partnerService.registerPartner(command);
        notificationService.sendEmail(partnerInfo.getEmail(), "title", "description");
        return partnerInfo;
    }

    public PartnerInfo getPartnerInfo(String partnerToken){
        return partnerService.getPartnerInfo(partnerToken);
    }

    public PartnerInfo enablePartner(String partnerToken){
        return partnerService.enablePartner(partnerToken);
    }

    public PartnerInfo disablePartner(String partnerToken){
        return partnerService.disablePartner(partnerToken);
    }
}
