package net.koreate.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.board.config.RootConfig;
import com.zaxxer.hikari.HikariConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	classes = {RootConfig.class}
)
public class RootConfigTest {
	
	@Autowired
	HikariConfig hc;
	
	@Test
	public void hikariTest() {
		System.out.println(hc);
	}
	
}
