package com.agesun.mybatis.bean;


import com.agesun.mybatis.util.PageData;

import java.util.List;

public class AdvList {
    private Integer total;

    private List<PageData> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PageData> getData() {
        return data;
    }

    public void setData(List<PageData> data) {
        this.data = data;
    }

}
