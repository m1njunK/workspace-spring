package net.koreate.board;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.board.config.RootConfig;
import com.bitc.board.config.ServletContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {RootConfig.class, ServletContextConfig.class}
)
@WebAppConfiguration		// spring ioc container == bean 관리
public class BoardControllerTest {
	
	// root spring container
	@Autowired
	ApplicationContext context;
	
	// spring container
	@Autowired
	WebApplicationContext wc;
	
	// 가상의 DispatcherServlet
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		System.out.println("Before 실행");
		mvc = MockMvcBuilders.webAppContextSetup(wc).build();
		System.out.println("Before 종료");
	}
	
	@Test
	public void testRead() throws Exception {
		System.out.println("START TEST");
		// perform 수행 - 요청 작업을 수행
		// 매개변수로 요청에 대한 설정 정보를 생성 하여 전달
		ModelMap modelMap = mvc.perform(
				MockMvcRequestBuilders.get("/board/readPage")
				.param("bno", "1")
				.param("page","1")
				.param("perPageNum","10")
		).andReturn().getModelAndView().getModelMap();
		System.out.println(modelMap);
		System.out.println("END TEST");
	}
	
	@Test
	public void insertBoardTest() throws Exception{
		ResultActions ra = 
		mvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "test title")
				.param("content", "test content")
				.param("writer", "kimpangirl")
		);
		
		MvcResult result = ra.andReturn();
		FlashMap flash = result.getFlashMap();
		ModelAndView mav = result.getModelAndView();
		System.err.println(mav);
		System.err.println("flash : " + flash.get("result"));
	}
	
}
