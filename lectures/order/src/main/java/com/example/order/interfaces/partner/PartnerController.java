package com.example.order.interfaces.partner;

import com.example.order.application.partner.PartnerFacade;
import com.example.order.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerController {
    private final PartnerFacade partnerFacade;

    @PostMapping("/")
    public CommonResponse registerPartner(@RequestBody @Valid PartnerDto.RegisterRequest dto){
        var partnerCommand = dto.toCommand();
        var partnerInfo = partnerFacade.registerPartner(partnerCommand);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    @GetMapping("/{partnerToken}")
    public CommonResponse getPartner(@PathVariable @Valid String partnerToken){
        var partnerInfo = partnerFacade.getPartnerInfo(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/enable/{partnerToken}")
    public CommonResponse enablePartner(@PathVariable @Valid String partnerToken){
        var partnerInfo = partnerFacade.enablePartner(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/disable/{partnerToken}")
    public CommonResponse disablePartner(@PathVariable @Valid String partnerToken){
        var partnerInfo = partnerFacade.disablePartner(partnerToken);
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

}
