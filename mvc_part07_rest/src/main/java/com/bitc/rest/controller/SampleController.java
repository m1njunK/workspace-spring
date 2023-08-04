package com.bitc.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.rest.vo.SampleVO;

@Controller
public class SampleController {
	
	@GetMapping("/testJSON")
	public String toJSON(Model model) {
		// key 값 지정안하면 class명 첫글자만 소문자로 바껴서 지정됨 String -> string
		model.addAttribute("Hello KPG");
		return "JSON";
	}
	
	@GetMapping("/getSample")
	public String sendSampleVO(SampleVO vo, Model model) {
		System.out.println(vo);
		model.addAttribute("sample",vo);
		return "JSON";
	}
	
	@GetMapping("getSampleList")
	@ResponseBody
	public List<SampleVO> getSampleList(){
		System.out.println("getSampleList");
		List<SampleVO> sampleList = new ArrayList<>();
		for(int i = 1; i < 11; i++) {
			SampleVO vo = new SampleVO();
			vo.setName("KPG-"+i+"호");
			vo.setAge(100+i);
			sampleList.add(vo);
		}
		return sampleList;
	}
	
	@PutMapping("/getSample2")
	@ResponseBody
	public List<SampleVO> getSample2(@RequestBody SampleVO vo){
		List<SampleVO> list = new ArrayList<>();
		list.add(vo);
		for(int i = 1; i < 11; i++) {
			SampleVO s = new SampleVO();
			s.setName("KPG-"+i);
			s.setAge(100+i);
			list.add(s);
		}
		return list;
	}
}