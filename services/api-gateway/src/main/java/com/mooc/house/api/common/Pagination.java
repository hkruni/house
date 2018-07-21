package com.mooc.house.api.common;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 查询结果的分页信息
 * @author hukai
 */
public class Pagination {
	
	public static final int DEFAULT_LIMIT      = 10;
	public static final int DEFAULT_PAGE       = 1;
	public static final int DEFAULT_OFFSET     = 0;
	public static final int DEFAULT_PAGE_SIZE  = 10;
	
	private int queryCount;//本次查询返回的数据条数
	private int pageNum;//当前页
	private int pageSize;//页面大小
	private long totalCount;//总的数据条数
	//存放所有的页码，ex:总的页数10，当前返回第8页，pages为1,2,3,4,5,6,7,8,9,10
	private List<Integer> pages =Lists.newArrayList();
	
	
	public Pagination(Integer pageSize,Integer pageNum,Long totalCount,Integer queryCount){
		this.totalCount = totalCount;
		this.pageSize   = pageSize;
		this.pageNum   = pageNum;
		this.queryCount = queryCount;
		
		for (int i = 1; i <= pageNum; i++) {
	       pages.add(i);
        }
        long  pageCount = totalCount / pageSize + ((totalCount % pageSize == 0) ? 0 : 1);//总得页数
        if (pageCount > pageNum) {
          for (int i = pageNum +1 ; i <= pageCount; i++) {
            pages.add(i);
          }
        }    
            
	}
	

	public Integer getQueryCount() {
		return queryCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


  @Override
  public String toString() {
    return "Pagination [queryCount=" + queryCount + ", pageNum=" + pageNum + ", pageSize="
        + pageSize + ", totalCount=" + totalCount + ", pages=" + pages + "]";
  }
	
	

}
