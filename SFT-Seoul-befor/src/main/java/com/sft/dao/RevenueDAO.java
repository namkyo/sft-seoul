package com.sft.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sft.dto.Revenue;
import com.sft.dto.RevenueList;
import com.sft.dto.Revenue_files;

@Repository
public class RevenueDAO {

	@Autowired
	private SqlSession sqlSessionl;

	private String namespace = "revenue";

	public List<RevenueList> selectList() {
		return sqlSessionl.selectList(namespace + ".selectList");
	}

	public Revenue selectOne(int num) {
		Revenue revenue=new Revenue();
		revenue.setNum(num);
		return sqlSessionl.selectOne(namespace + ".selectOne", revenue);
	}
	
	public int seq() {
		return sqlSessionl.selectOne(namespace + ".seq");
	}
	
	public List<Revenue_files> selectOneFiles(int num) {
		Revenue_files revenue_files=new Revenue_files();
		revenue_files.setUpper_num(num);
		return sqlSessionl.selectList(namespace + ".selectOne_files", revenue_files);
	}
	
	
	public int insert(Revenue revenue) {
		return sqlSessionl.insert(namespace + ".create", revenue);
	}
	
	public int insert_file(Revenue_files revenue_files) {
		return sqlSessionl.insert(namespace + ".create_file", revenue_files);
	}
	
	public int update(Revenue revenue) {
		return sqlSessionl.update(namespace + ".update", revenue);
	}
	public int delete(Revenue revenue) {
		return sqlSessionl.delete(namespace + ".delete", revenue);
	}
	public int delete_file(Revenue_files revenue_files) {
		return sqlSessionl.delete(namespace + ".delete_file", revenue_files);
	}

}
