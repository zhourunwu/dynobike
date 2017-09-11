package com.jeeplus.modules.sports.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeeplus.common.persistence.Page;

import javax.xml.bind.annotation.XmlTransient;

public class PageUtil<T> {

    private String searchName;

    private com.jeeplus.common.persistence.Page<T> page;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @JsonIgnore
    @XmlTransient
    public Page<T> getPage() {
        if (page == null){
            page = new com.jeeplus.common.persistence.Page<T>();
        }
        return page;
    }

    public Page<T> setPage(com.jeeplus.common.persistence.Page<T> page) {
        this.page = page;
        return page;
    }

}