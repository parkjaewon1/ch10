package com.ch.ch10.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.ch10.dao.BoardDao;
import com.ch.ch10.model.Board;
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao bd;
//	public List<Board> list(int startRow, int endRow) {
//		return bd.list(startRow, endRow);
//	}
	public List<Board> list(Board board) {
		return bd.list(board);
	}
	public int getTotal(Board board) {
		return bd.getTotal(board);
	}
//	public int getTotal() {
//		return bd.getTotal();
//	}
	public int getMaxNum() {
		return bd.getMaxNum();
	}
	public int insert(Board board) {
		return bd.insert(board);
	}
	public void updateReadCount(int num) {
		bd.updateReadCount(num);		
	}
	public Board select(int num) {
		return bd.select(num);
	}
	public int update(Board board) {
		return bd.update(board);
	}
	public int delete(int num) {
		return bd.delete(num);
	}
	public void updateStep(Board board) {
		bd.updateStep(board);		
	}
}