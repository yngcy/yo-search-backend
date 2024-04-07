package com.yocy.yosearch.datasource;

import com.yocy.yosearch.model.enums.SearchTypeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
@Component
public class DataSourceRegistry {

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PostDataSource postDataSource;

    @Resource
    private PictureDataSource pictureDataSource;

    private Map<String, DataSource<T>> typeDataSourceMap;

    @PostConstruct
    public void init() {
        typeDataSourceMap = new HashMap() {
            {
                put(SearchTypeEnum.POST.getValue(), postDataSource);
                put(SearchTypeEnum.USER.getValue(), userDataSource);
                put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
            }
        };
    }


    public DataSource getDataSourceByType(String type) {
        if (typeDataSourceMap == null) return null;
        return typeDataSourceMap.get(type);
    }
}
