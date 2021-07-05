package com.ch.ch10.service;
import java.util.List;

import com.ch.ch10.model.Board;
public interface BoardService {
	List<Board> list(Board board);
//	List<Board> list(int startRow, int endRow);
	int getTotal(Board board);
//	int getTotal();
	int getMaxNum();
	int insert(Board board);
	void updateReadCount(int num);
	Board select(int num);
	int update(Board board);
	int delete(int num);
	void updateStep(Board board);

}