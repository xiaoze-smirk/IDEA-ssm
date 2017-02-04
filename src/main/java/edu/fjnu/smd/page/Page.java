package edu.fjnu.smd.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaozemaliya on 2017/2/4.
 */
/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> {
    private int pageNo = 1;			//页码，默认是第一页
    private int pageSize = 3;		//每页显示的记录数，默认是3
    private int totalRecord;		//总记录数
    private int totalPage;			//总页数
    private List<T> results;		//对应的当前页记录
    private Map<String, Object> params = new HashMap<String, Object>();		//其他的参数我们把它分装成一个Map对象

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
        this.setTotalPage(totalPage);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize).append(", results=").append(results).append(", totalPage=").append(totalPage).append(", totalRecord=").append(totalRecord).append("]");
        return builder.toString();
    }

    /* 页面链接 */
    public String pageLinks(String url) {
        int endPage = this.totalRecord/pageSize +1;

        StringBuffer sBuf = new StringBuffer();

        sBuf.append("<input type=\"hidden\" name=\"pageNo\" value=\"").append(this.pageNo).append("\">");       //分页参数：当前页隐藏域

        sBuf.append("<span class=\"noprint\" style=\"padding:5px;\">");
        sBuf.append("&nbsp;共").append(this.totalRecord).append("条,当前显示第");

        if(pageNo*this.pageSize<this.totalRecord) {

            sBuf.append((pageNo - 1) * this.pageSize + 1).append("-").append(pageNo * this.pageSize);

        }else if(this.totalRecord-(pageNo-1)*this.pageSize==1){

            sBuf.append(this.totalRecord);

        }else{
            sBuf.append((pageNo - 1) * this.pageSize + 1).append("-").append(this.totalRecord);
        }

        sBuf.append("条,第").append(this.pageNo).append("/").append(endPage).append("页&nbsp;").append("|");
        if(pageNo>1){
            sBuf.append("<a href=\"").append(url).append("?pageNo=1");
            sBuf.append("\">[首页]");
            sBuf.append("</a>&nbsp;");

            sBuf.append("<a href=\"").append(url).append("?pageNo=");
            if(pageNo<=1){
                sBuf.append(1);
            }else{
                sBuf.append(pageNo-1);
            }
            sBuf.append("\">[上一页]");
            sBuf.append("</a>&nbsp;");
        }

        if(pageNo!=endPage){
            sBuf.append("<a href=\"").append(url).append("?pageNo=");
        if(pageNo>=endPage){
            sBuf.append(endPage);
        }else{
            sBuf.append(pageNo+1);
        }
        sBuf.append("\">[下一页]");
        sBuf.append("</a>&nbsp;");

        sBuf.append("<a href=\"").append(url).append("?pageNo=").append(endPage);
        sBuf.append("\">[末页]");
        sBuf.append("</a>&nbsp;");

        sBuf.append("</span>&nbsp;&nbsp;");
    }
    return sBuf.toString();
}

}
