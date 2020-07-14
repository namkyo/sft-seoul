package com.sft.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sft.domin.SearchCriteria;
import com.sft.dto.Paging;
import com.sft.dto.Press;

@Repository
public class PressDAO {
	private static final Logger logger = LoggerFactory.getLogger(PressDAO.class);
	@Autowired
	private SqlSession sqlSessionl;

	private String namespace = "press";

	public List<Press> selectList(SearchCriteria cri) {
		return sqlSessionl.selectList(namespace + ".selectList",cri);
	}
	
	public int selectTotalCount() {
		return sqlSessionl.selectOne(namespace + ".selectTotalCount");
	}
	
	

	public Press selectOne(int num) {
		Press press=new Press();
		press.setNum(num);
		return sqlSessionl.selectOne(namespace + ".selectOne", press);
	}
	
	
	public int insert(Press press) {
		logger.info(press.toString());
		return sqlSessionl.insert(namespace + ".create", press);
	}
	
	public int update(Press press) {
		return sqlSessionl.update(namespace + ".update", press);
	}
	public int delete(Press press) {
		return sqlSessionl.delete(namespace + ".delete", press);
	}

}
