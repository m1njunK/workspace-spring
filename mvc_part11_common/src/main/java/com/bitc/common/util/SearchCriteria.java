package com.bitc.common.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchCriteria extends Criteria{

	private String searchType;
	private String keyword;
	
	public SearchCriteria(int page, int perPageNum, String searchType, String keyword) {
		super(page, perPageNum);
		this.searchType = searchType;
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return super.toString() + " startRow : "+super.getStartRow()+ " - SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
}
