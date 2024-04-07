package com.yocy.yosearch.controller;

import com.yocy.yosearch.common.BaseResponse;
import com.yocy.yosearch.common.ResultUtils;
import com.yocy.yosearch.manager.SearchFacade;
import com.yocy.yosearch.model.dto.search.SearchRequest;
import com.yocy.yosearch.model.vo.SearchVO;
import com.yocy.yosearch.service.PictureService;
import com.yocy.yosearch.service.PostService;
import com.yocy.yosearch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private PictureService pictureService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }
}
