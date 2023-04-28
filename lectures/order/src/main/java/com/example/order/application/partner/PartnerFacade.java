package com.example.order.application.partner;

import com.example.order.domain.notification.NotificationService;
import com.example.order.domain.partner.PartnerCommand;
import com.example.order.domain.partner.PartnerInfo;
import com.example.order.domain.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerFacade {
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    public PartnerInfo registerPartner(PartnerCommand command){
        var partnerInfo = partnerService.registerPartner(command);
        notificationService.sendEmail(null, null, null);

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
