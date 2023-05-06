package com.example.order.domain.item.optiongroup;

import com.example.order.common.exception.InvalidParamException;
import com.example.order.domain.AbstractEntity;
import com.example.order.domain.item.Item;
import com.example.order.domain.item.option.ItemOption;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_option_groups")
public class ItemOptionGroup extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private Integer ordering;
    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOption> itemOptionList = Lists.newArrayList();

    @Builder
    public ItemOptionGroup(Item item, Integer ordering, String itemOptionGroupName) {
        if(item == null) throw new InvalidParamException("ItemOptionGroup.item");
        if(ordering == null) throw new InvalidParamException("ItemOptionGroup.ordering");
        if(StringUtils.isBlank(itemOptionGroupName)){
            throw new InvalidParamException("ItemOptionGroup.itemOptionGroupName");
        }

        this.item = item;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }
}
