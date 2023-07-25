package com.bitc.di.vo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
// @Service			// 좋지 않다
public class TestBoardVO {
	
	private int num;
	private String title;
	private String content;
	
}
