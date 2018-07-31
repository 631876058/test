package com.reward.now.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reward.now.data.NowData;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/index")
	@ResponseBody
	public List<Map<String,Object>> show() {
		try {
			NowData.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NowData.NOW_DATA;
	}
}
