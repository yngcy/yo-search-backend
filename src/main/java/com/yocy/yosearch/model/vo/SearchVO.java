package com.yocy.yosearch.model.vo;

import com.yocy.yosearch.model.entity.Picture;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 聚合搜索类
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
@Data
public class SearchVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<UserVO> userList;
    
    private List<PostVO> postList;
    
    private List<Picture> pictureList;
    
    private List<?> dataList;
}
