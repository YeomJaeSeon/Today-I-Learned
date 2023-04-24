package com.example.order.interfaces.partner;

import com.example.order.application.partner.PartnerFacade;
import com.example.order.common.response.CommonResponse;
import com.example.order.domain.partner.PartnerInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerApiController {
    private final PartnerFacade partnerFacade;

    @PostMapping
    public CommonResponse registerPartner(@RequestBody @Valid PartnerDto.RegisterRequest request){
        var command = request.toCommand();
        var partnerInfo = partnerFacade.registerPartner(command);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    @GetMapping("/{partnerToken}")
    public CommonResponse getPartnerInfo(@PathVariable String partnerToken){
        var partnerInfo = partnerFacade.getPartnerInfo(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("enable/{partnerToken}")
    public CommonResponse enablePartner(@PathVariable String partnerToken){
        var partnerInfo = partnerFacade.enablePartner(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("disable/{partnerToken}")
    public CommonResponse disablePartner(@PathVariable String partnerToken){
        var partnerInfo = partnerFacade.disablePartner(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }
}
