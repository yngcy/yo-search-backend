package com.yocy.yosearch.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.ss.formula.functions.T;

/**
 * 数据源接口
 * @author <a href="https://github.com/youngccy">YounGCY</a>
 * @description
 */
public interface DataSource<T> {

    /**
     * 搜索
     * @param searchText
     * @param current
     * @param pageSize
     * @return
     */
    Page<T> doSearch(String searchText, long current, long pageSize);
}
