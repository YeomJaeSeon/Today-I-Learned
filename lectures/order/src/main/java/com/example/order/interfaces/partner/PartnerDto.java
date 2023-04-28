package com.example.order.interfaces.partner;

import com.example.order.domain.partner.Partner;
import com.example.order.domain.partner.PartnerCommand;
import com.example.order.domain.partner.PartnerInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PartnerDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest{
        @NotEmpty(message = "partnerName 필수")
        private String partnerName;
        @NotEmpty(message = "businessNo 필수")
        private String businessNo;
        @Email(message = "email")
        @NotEmpty(message = "email 필수")
        private String email;

        public PartnerCommand toCommand(){
            return PartnerCommand.builder()
                    .partnerName(partnerName)
                    .businessNo(businessNo)
                    .email(email)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterResponse{
        private final String partnerToken;
        private final String partnerName;
        private final String businessNo;
        private final String email;
        private final Partner.Status status;

        public RegisterResponse(PartnerInfo partnerInfo) {
            this.partnerToken = partnerInfo.getPartnerToken();
            this.partnerName = partnerInfo.getPartnerName();
            this.businessNo = partnerInfo.getBusinessNo();
            this.email = partnerInfo.getEmail();
            this.status = partnerInfo.getStatus();
        }
    }
}
