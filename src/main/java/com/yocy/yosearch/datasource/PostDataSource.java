package com.yocy.yosearch.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yocy.yosearch.model.dto.post.PostQueryRequest;
import com.yocy.yosearch.model.entity.Post;
import com.yocy.yosearch.model.vo.PostVO;
import com.yocy.yosearch.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务实现
 *
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO>  {
    
    @Resource
    private PostService postService;
    
    @Override
    public Page<PostVO> doSearch(String searchText, long current, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(current);
        postQueryRequest.setPageSize(pageSize);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return postService.getPostVOPage(postPage, request);
    }
    
}




