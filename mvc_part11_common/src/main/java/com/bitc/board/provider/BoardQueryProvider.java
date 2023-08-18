package com.bitc.board.provider;

import org.apache.ibatis.jdbc.SQL;

import com.bitc.board.vo.BoardVO;
import com.bitc.common.util.SearchCriteria;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;

public class BoardQueryProvider {
	
	public String registTest(BoardVO board) {
		SQL sql = new SQL();
		sql.INSERT_INTO("re_tbl_board");
		sql.INTO_COLUMNS("title","content","writer","uno");
		sql.INTO_VALUES("#{title}, #{content}, #{writer}, #{uno}");
		String query = sql.toString();
		return query;
	}
	
	public String searchSelcetSql(SearchCriteria cri) {
		SQL sql = new SQL();
		sql.SELECT("*");
		sql.FROM("re_tbl_board");
		sql.ORDER_BY("origin DESC, seq ASC");
		sql.LIMIT("#{startRow},#{perPageNum}");
		
		String titleQuery = "title LIKE CONCAT('%','"+cri.getKeyword()+"','%')";
		String contentQuery = "content LIKE CONCAT('%',#{keyword},'%')";
		String writerQuery = "writer LIKE CONCAT('%',#{keyword},'%')";
		
		String type = cri.getSearchType();
		
		if(type != null && !type.trim().equals("") && !type.trim().equals("n")) {
			if(type.contains("t")) {
				sql.OR().WHERE(titleQuery);
			}
			if(type.contains("c")) {
				sql.OR().WHERE(contentQuery);
			}
			if(type.contains("w")) {
				sql.OR().WHERE(writerQuery);
			}
		}
		String query = sql.toString();
		return query;
	}
	
	public String searchSelectCount(SearchCriteria cri) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("re_tbl_board");
				getSearchWhere(cri, this);
			}
		}.toString();
	}
	
	// 조회수 증가
	public String updateCnt(int bno) {
		return new SQL().UPDATE("re_tbl_board")
				.SET("viewcnt = viewcnt + 1")
				.WHERE("bno = #{bno}")
				.toString();
	}
	
	public void getSearchWhere(SearchCriteria cri, SQL sql) {
		
		String titleQuery = "title LIKE CONCAT('%','"+cri.getKeyword()+"','%')";
		String contentQuery = "content LIKE CONCAT('%',#{keyword},'%')";
		String writerQuery = "writer LIKE CONCAT('%',#{keyword},'%')";
		
		String type = cri.getSearchType();
		
		if(type != null && !type.trim().equals("") && !type.trim().equals("n")) {
			if(type.contains("t")) {
				sql.OR().WHERE(titleQuery);
			}
			if(type.contains("c")) {
				sql.OR().WHERE(contentQuery);
			}
			if(type.contains("w")) {
				sql.OR().WHERE(writerQuery);
			}
		}
	}
	
}
