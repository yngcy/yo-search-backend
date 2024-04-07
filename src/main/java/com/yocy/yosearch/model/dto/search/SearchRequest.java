package com.yocy.yosearch.model.dto.search;

import com.yocy.yosearch.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRequest extends PageRequest implements Serializable {

    /**
     * 搜索文本
     */
    private String searchText;

    /**
     * 类型
     */
    private String type;
    
    private static final long serialVersionUID = 1L;
}
