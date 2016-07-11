package tvz.nppjj.paris.model.dto;

import java.util.List;

/**
 * Created by josip.kovacek on 5.7.2016..
 */
public class PaginationDto<T>{

    private List<T> paginatedList;
    private int currentPage;
    private int pagination;
    private int totalPages;

    public PaginationDto(){}

    public PaginationDto(List<T> paginatedList, int currentPage, int pagination, int totalPages) {
        this.paginatedList = paginatedList;
        this.currentPage = currentPage;
        this.pagination = pagination;
        this.totalPages = totalPages;
    }

    // getters and setters
    public List<T> getPaginatedList() {
        return paginatedList;
    }

    public void setPaginatedList(List<T> paginatedList) {
        this.paginatedList = paginatedList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
