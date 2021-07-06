package com.ch.ch10.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch.ch10.model.Board;
import com.ch.ch10.model.ReplyBoard;
import com.ch.ch10.service.BoardService;
import com.ch.ch10.service.ReplyBoardService;
@Controller
public class ReplyBoardController {
	@Autowired
	private ReplyBoardService rbs;
	@Autowired
	private BoardService bs; // 자식은 부모정보가 필요함
	@RequestMapping("/rDelete")
	public String rDelete(ReplyBoard rb) {
		rbs.delete(rb);
		return "redirect:/replyList/bno/"+rb.getBno();
	}
	@RequestMapping("/rInsert")
	public String rInsert(ReplyBoard rb) {
		rbs.insert(rb);
//		redirect 또는 forward는 JSP가 아닌 Controller 내부의 다른 메서드 호출
		return "redirect:/replyList/bno/"+rb.getBno();
	}
	@RequestMapping("/rUpdate")
	public String rUpdate(ReplyBoard rb) {
		rbs.update(rb);
		return "redirect:/replyList/bno/"+rb.getBno();
	}
	@RequestMapping("/replyList/bno/{bno}")
	public String replyList(@PathVariable int bno, Model model) {
		Board board = bs.select(bno);
		List<ReplyBoard> rbdList = rbs.list(bno);
		model.addAttribute("board", board);
		model.addAttribute("rbdList", rbdList);
		return "replyList";
	}
}