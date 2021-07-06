package com.ch.ch10.dao;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.ch10.model.ReplyBoard;
@Repository
public class ReplyBoardDaoImpl implements ReplyBoardDao {
	@Autowired
	private SqlSessionTemplate sst;
	public List<ReplyBoard> list(int bno) {
		return sst.selectList("rbdns.list", bno);
	}
	public void delete(ReplyBoard rb) {
		sst.update("rbdns.delete", rb.getRno());		
	}
	public void insert(ReplyBoard rb) {
		sst.insert("rbdns.insert", rb);			
	}
	public void update(ReplyBoard rb) {
		sst.update("rbdns.update", rb);	
	}
}