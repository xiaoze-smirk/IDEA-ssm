package edu.fjnu.smd.utils;

import java.util.Collection;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public class Page {

    private Integer pageNo;    //当前页号          *
    private Integer pageSize;  //每页记录条数  *
    private Boolean nextPage;  //是否有下一页
    private Boolean prePage;   //是否有上一页
    private Long totalRecNum;  //总共有多少条记录  *
    private Integer totalPageNum;//总共多少页
    private Collection pageContent; //该页的数据(记录明细) *
    private Integer startIndex; //记录开始位置
    private Integer endIndex;   //记录结束位置

    public Page() {
        super();
        pageNo=1;
        pageSize=3;
    }

    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getNextPage() {
        return pageNo<getTotalPageNum()?true:false;
    }

    public Boolean getPrePage() {
        return pageNo>1?true:false;
    }

    public Long getTotalRecNum() {
        return totalRecNum;
    }
    public void setTotalRecNum(Long totalRecNum) {
        this.totalRecNum = totalRecNum;
    }

    public Integer getTotalPageNum() {
        return totalRecNum%pageSize>0?(int)(totalRecNum/pageSize+1):(int)(totalRecNum/pageSize);
    }

    public Collection getPageContent() {
        return pageContent;
    }

    public void setPageContent(Collection pageContent) {
        this.pageContent = pageContent;
    }

    public int getStartIndex()
    {
        return pageSize*(pageNo-1);  // pageNo:1   0    pageNo:2  3
    }


    //10
    //4*3  = 9-12
    public int getEndIndex()
    {
        int idx = (pageSize*pageNo>this.totalRecNum)? (int)(this.totalRecNum.longValue()):(pageSize*pageNo);
        return idx-1;
    }

}

