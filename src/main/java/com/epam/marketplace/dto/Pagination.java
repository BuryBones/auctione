package com.epam.marketplace.dto;

import javax.validation.constraints.NotNull;

public class Pagination {

  // sortMode is null!; status is null!; sortBy is null!;

  @NotNull(message = "status is null!")
  private String status = "open";
  @NotNull(message = "sortBy is null!")
  private String sortBy = "stopDate";
  @NotNull(message = "sortMode is null!")
  private String sortMode = "asc";
//  @NotNull(message = "totalPages is null!")
  private int totalPages = 1;
  @NotNull(message = "currentPage is null!")
  private int currentPage = 1;
  @NotNull(message = "pageSize is null!")
  private int pageSize = 5;

  public Pagination() {
  }

  @Override
  public String toString() {
    return "Pagination{" +
        "status='" + status + '\'' +
        ", sortBy='" + sortBy + '\'' +
        ", sortMode='" + sortMode + '\'' +
        ", totalPages=" + totalPages +
        ", currentPage=" + currentPage +
        ", pageSize=" + pageSize +
        '}';
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public String getSortMode() {
    return sortMode;
  }

  public void setSortMode(String sortMode) {
    this.sortMode = sortMode;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
