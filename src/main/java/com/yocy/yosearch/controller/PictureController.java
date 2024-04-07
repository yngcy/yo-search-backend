package com.yocy.yosearch.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yocy.yosearch.common.BaseResponse;
import com.yocy.yosearch.common.ErrorCode;
import com.yocy.yosearch.common.ResultUtils;
import com.yocy.yosearch.exception.ThrowUtils;
import com.yocy.yosearch.model.dto.picture.PictureQueryRequest;
import com.yocy.yosearch.model.entity.Picture;
import com.yocy.yosearch.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片接口
 *
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {
    

    @Resource
    private PictureService pictureService;

    private final static Gson GSON = new Gson();
    

    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                           HttpServletRequest request) {
        
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        String searchText = pictureQueryRequest.getSearchText();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);
        return ResultUtils.success(picturePage);
    }

    

}
