package test.omprakash.section26PracticePras;

import java.util.List;

public class Data {

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;

    //array object = list
    List<DataFolder> dataFolder;
    Support support;

    public Data(){

    }

    public Data(Integer page, Integer per_page,Integer total, Integer total_pages, List<DataFolder> dataFolder, Support support){
        this.page=page;
        this.per_page=per_page;
        this.total=total;
        this.total_pages=total_pages;
        this.dataFolder=dataFolder;
        this.support=support;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public List<DataFolder> getDataFolder() {
        return dataFolder;
    }

    public void setDataFolder(List<DataFolder> dataFolder) {
        this.dataFolder = dataFolder;
    }
}
