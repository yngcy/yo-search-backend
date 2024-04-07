package com.yocy.yosearch.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yocy.yosearch.model.dto.user.UserQueryRequest;
import com.yocy.yosearch.model.vo.UserVO;
import com.yocy.yosearch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现
 *
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 */
@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {
    
    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long current, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(current);
        userQueryRequest.setPageSize(pageSize);

        Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);
        
        return userVOPage;
    }
}
