package com.yocy.yosearch.model.dto.picture;

import com.yocy.yosearch.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {
    /**
     * 搜索词
     */
    private String searchText;
    
    private static final long serialVersionUID = 1L;
}
