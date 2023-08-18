package com.bitc.common.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class SearchPageMaker extends PageMaker{
	
	public SearchPageMaker() {
		super(new SearchCriteria(),0);
	}
	
	@Override
	public String mkQueryStr(int page) {
		SearchCriteria sCri = (SearchCriteria)cri;
		UriComponents uriComponentsents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", sCri.getSearchType())
				.queryParam("keyword", sCri.getKeyword())
				.build();
		String query = uriComponentsents.toUriString();
		return query;
	}
}
