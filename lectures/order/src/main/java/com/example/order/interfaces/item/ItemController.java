package com.example.order.interfaces.item;

import com.example.order.application.item.ItemFacade;
import com.example.order.common.response.CommonResponse;
import com.example.order.domain.item.ItemCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    @PostMapping
    public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest dto){
        String partnerToken = dto.getPartnerToken();
        ItemCommand.RegisterItemRequest itemCommand = itemDtoMapper.of(dto);
        String itemToken = itemFacade.registerItem(itemCommand, partnerToken);
        ItemDto.RegisterResponse response = itemDtoMapper.of(itemToken);
        return CommonResponse.success(response);
    }

    @PostMapping("/change-on-sales")
    public CommonResponse changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        var itemToken = request.getItemToken();
        itemFacade.changeOnSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    @PostMapping("/change-end-of-sales")
    public CommonResponse changeEndOfSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {
        var itemToken = request.getItemToken();
        itemFacade.changeEndOfSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    @GetMapping("/{itemToken}")
    public CommonResponse retrieve(@PathVariable("itemToken") String itemToken) {
        var itemInfo = itemFacade.retrieveItemInfo(itemToken);
        var response = itemDtoMapper.of(itemInfo);
        return CommonResponse.success(response);
    }
}
