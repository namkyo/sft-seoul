package com.sft.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sft.domin.SearchCriteria;
import com.sft.dto.Revenue;
import com.sft.dto.RevenueList;
import com.sft.dto.Revenue_files;

@Repository
public class RevenueDAO {

	@Autowired
	private SqlSession sqlSessionl;

	private String namespace = "revenue";

	public List<RevenueList> selectList(SearchCriteria cri) {
		return sqlSessionl.selectList(namespace + ".selectList",cri);
	}

	public Revenue selectOne(int num) {
		Revenue revenue=new Revenue();
		revenue.setNum(num);
		return sqlSessionl.selectOne(namespace + ".selectOne", revenue);
	}
	
	
	public int insert(Revenue revenue) {
		return sqlSessionl.insert(namespace + ".create", revenue);
	}
	
	public int update(Revenue revenue) {
		return sqlSessionl.update(namespace + ".update", revenue);
	}
	public int delete(Revenue revenue) {
		return sqlSessionl.delete(namespace + ".delete", revenue);
	}
	

}
