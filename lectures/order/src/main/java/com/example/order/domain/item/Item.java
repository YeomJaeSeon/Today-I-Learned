package com.example.order.domain.item;

import com.example.order.common.exception.InvalidParamException;
import com.example.order.common.util.TokenGenerator;
import com.example.order.domain.AbstractEntity;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item extends AbstractEntity {
    private static final String PREFIX_ITEM = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemToken;
    private Long partnerId;
    private String itemName;
    private Long itemPrice;
    @Enumerated(EnumType.STRING)
    private Status statue;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroupList = Lists.newArrayList();

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        PREPARE("판매준비중"), ON_SALES("판매중"), END_OF_SALES("판매 종료");

        private final String description;
    }

    @Builder
    public Item(Long partnerId, String itemName, Long itemPrice) {
        if(partnerId == null) throw new InvalidParamException();
        if(StringUtils.isEmpty(itemName)) throw new InvalidParamException();
        if(itemPrice == null) throw new InvalidParamException();

        this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
        this.partnerId = partnerId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.statue = Status.PREPARE;
    }

    //    별도의 상태를 바꿔주는 명시적인 메서드가 낫다.
//    상태별로 하고싶은것도 다를거고, 코드길이도 줄어들어 가독성도 좋을듯
    public void changePrepare(){
        this.statue = Status.PREPARE;
    }

    public void changeOnSales(){
        this.statue = Status.ON_SALES;
    }
    public void changeEndOfSales(){
        this.statue = Status.END_OF_SALES;
    }
}
