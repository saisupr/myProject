package com.myProject.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
        private List<T> content;
        private int totalPages;
        private long totalElements;
        private boolean last;
        private int pageNumber;
        private int pageSize;
    // ✅ Default constructor required for Jackson
    public PageResponse() {
    }

    // ✅ Constructor to map Page<T>
    public PageResponse(Page<T> pageData) {
        this.content = pageData.getContent();
        this.totalPages = pageData.getTotalPages();
        this.totalElements = pageData.getTotalElements();
        this.last = pageData.isLast();
        this.pageNumber = pageData.getNumber();
        this.pageSize = pageData.getSize();
    }
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
