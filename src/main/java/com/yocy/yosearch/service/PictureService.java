package com.yocy.yosearch.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yocy.yosearch.model.entity.Picture;

/**
 * 图片服务
 *
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 */
public interface PictureService {

    Page<Picture> searchPicture(String searchText, long current, long pageSize);
}
