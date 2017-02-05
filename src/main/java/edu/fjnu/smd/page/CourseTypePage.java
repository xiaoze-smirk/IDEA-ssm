package edu.fjnu.smd.page;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public class CourseTypePage {
    private int allpage;
    private int pageindex;
    private int rows;



    public CourseTypePage() {

        this.allpage = 0;
        this.pageindex = 1;
        this.rows = 3;
    }

    public int getAllpage() {
        return allpage;
    }
    public void setAllpage(int allpage) {
        this.allpage = allpage;
    }
    public int getPageindex() {
        return pageindex;
    }
    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }


}
