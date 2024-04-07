package com.yocy.yosearch.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 图片
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
@Data
public class Picture implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String title;
    
    private String url;
}
