package com.fxcity.home;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sft.dao.PressDAO;
import com.sft.dto.Paging;
import com.sft.dto.Press;
import com.sft.service.PressService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DataSourceTest {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSession sqlSessionl;
	
	private String namespace = "press";
	private String namespace2 = "revenue";
	
	
	@Test
	public void tttt() {
		System.out.println(sqlSessionl.selectList(namespace2 + ".seq"));
	}
	@Test
	public void getPressList2() {
		System.out.println(sqlSessionl.selectList(namespace2 + ".selectList"));
	}
	
	
	
	
	@Test
	public void press_select_one() {
		Press press=new Press();
		press.setNum(1);
		System.out.println(sqlSessionl.selectOne(namespace + ".selectOne", press));
	}
	
	
	@Test
	public void testConnection() throws Exception {
		System.out.println("sqlSessionFactory : "+sqlSessionFactory);
		
	}
	
	
	@Test
	public void getPressList() {
		Paging paing=new Paging();
		
		int page=1;
		int pageSize=10;
		
		int v_pageSize=(page-1)*pageSize;
		int v_pageOffset=(pageSize-1);
		
		paing.setPageSize(v_pageSize);
		paing.setPageOffset(v_pageOffset);
		System.out.println(sqlSessionl.selectList(namespace + ".selectList",paing));
	}
	
	@Test
	public void getPressList22() {
		System.out.println(sqlSessionl.selectOne(namespace + ".selectTotalCount"));
	}
	
	@Test
	public void insert() {
		Press press=new Press();

		for(int i=101;i<200;i++) {
			try {
				press.setTitle(i+"번째글");
				press.setTxusrnm("작성자");
				press.setReg_date("2019-09-09");
				press.setContent(i+"번째게시글");
				System.out.println(sqlSessionl.insert(namespace+".create", press));
			} catch (Exception e) {
				System.out.println(e);
				break;
			}
		}
		
		
	}
	
	@Test
	public void update() {
		Press press=new Press();
		press.setTitle("수정수정");
		press.setTxusrnm("김남교");
		press.setReg_date("2019-09-09");
		press.setContent("어머나");
		press.setNum(8);
		
		try {
			System.out.println(sqlSessionl.update("update", press));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void delete() {
		Press press=new Press();
		press.setNum(9);
		int result = sqlSessionl.delete("delete",press);
		System.out.println(result);
	}

}