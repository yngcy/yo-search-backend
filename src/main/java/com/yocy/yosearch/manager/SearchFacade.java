package com.yocy.yosearch.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yocy.yosearch.common.ErrorCode;
import com.yocy.yosearch.datasource.*;
import com.yocy.yosearch.exception.BusinessException;
import com.yocy.yosearch.exception.ThrowUtils;
import com.yocy.yosearch.model.dto.post.PostQueryRequest;
import com.yocy.yosearch.model.dto.search.SearchRequest;
import com.yocy.yosearch.model.dto.user.UserQueryRequest;
import com.yocy.yosearch.model.entity.Picture;
import com.yocy.yosearch.model.enums.SearchTypeEnum;
import com.yocy.yosearch.model.vo.PostVO;
import com.yocy.yosearch.model.vo.SearchVO;
import com.yocy.yosearch.model.vo.UserVO;
import com.yocy.yosearch.service.PictureService;
import com.yocy.yosearch.service.PostService;
import com.yocy.yosearch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.jsoup.internal.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
@Component
@Slf4j
public class SearchFacade {
    
    @Resource
    private UserDataSource userDataSource;
    
    @Resource
    private PostDataSource postDataSource;
    
    @Resource
    private PictureDataSource pictureDataSource;

    @Resource
    private DataSourceRegistry dataSourceRegistry;

    public SearchVO searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        ThrowUtils.throwIf(StringUtil.isBlank(type), ErrorCode.PARAMS_ERROR);
        String searchText = searchRequest.getSearchText();
        long current = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();
        SearchVO searchVO = new SearchVO();

        if (searchTypeEnum == null) {
            CompletableFuture<Page<UserVO>> userTask = CompletableFuture.supplyAsync(() -> {
                Page<UserVO> userVOPage = userDataSource.doSearch(searchText, current, pageSize);
                return userVOPage;
            });

            CompletableFuture<Page<Picture>> pictureTask = CompletableFuture.supplyAsync(() -> {
                Page<Picture> picturePage = pictureDataSource.doSearch(searchText, 1, 10);
                return picturePage;
            });

            CompletableFuture<Page<PostVO>> postTask = CompletableFuture.supplyAsync(() -> {
                PostQueryRequest postQueryRequest = new PostQueryRequest();
                postQueryRequest.setSearchText(searchText);
                Page<PostVO> postVOPage = postDataSource.doSearch(searchText, current, pageSize);
                return postVOPage;
            });

            CompletableFuture.allOf(userTask, postTask, pictureTask).join();

            try {
                Page<UserVO> userVOPage = userTask.get();
                Page<PostVO> postVOPage = postTask.get();
                Page<Picture> picturePage = pictureTask.get();

                searchVO.setUserList(userVOPage.getRecords());
                searchVO.setPostList(postVOPage.getRecords());
                searchVO.setPictureList(picturePage.getRecords());
                
            } catch (Exception e) {
                log.error("查询异常", e);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询异常");
            } finally {
                return searchVO;
            }
        } else {
            
            DataSource<?> dataSource = dataSourceRegistry.getDataSourceByType(type);
            Page page = dataSource.doSearch(searchText, current, pageSize);
            searchVO.setDataList(page.getRecords());
            
            return searchVO;
        }

    }
}
