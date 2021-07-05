package com.ch.ch10.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch.ch10.model.Board;
import com.ch.ch10.service.BoardService;
import com.ch.ch10.service.PagingBean;
@Controller
public class BoardController {
	@Autowired
	private BoardService bs;
	@RequestMapping("/insertForm/num/{num}/pageNum/{pageNum}")
	public String insertForm(@PathVariable int num,@PathVariable String pageNum, Model model) {
		int ref =0, re_level=0, re_step=0;
		if (num != 0) { // 답변글
			Board board = bs.select(num);
			ref = board.getRef();
			re_step  = board.getRe_step();
			re_level = board.getRe_level(); 
		}
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("ref", ref);
		model.addAttribute("re_level", re_level);
		model.addAttribute("re_step", re_step);
		return "insertForm";
	}
	@RequestMapping("/insert")
	public String insert(Board board, String pageNum, Model model, 
			HttpServletRequest request) {
		// ip를 추적할 필요가 있는 경우
		System.out.println("pageNum="+pageNum);
		board.setIp(request.getRemoteAddr());
		int number = bs.getMaxNum(); // 게시글 글번호 생성
		if (board.getNum() != 0) { // 답변글
			// 글을 읽고 ref가 같고 re_step이 읽은 글의 re_step보다 크면 그글의 re_step + 1
			bs.updateStep(board);
			board.setRe_level(board.getRe_level()+1);
			board.setRe_step(board.getRe_step()+1);
			// 답변글의 ref는 읽은 글의 ref를 그대로 사용
		} else board.setRef(number); // 답변글이 아닐 때는 num과 같고
		board.setNum(number);
		int result = bs.insert(board);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "insert";
	}
	@RequestMapping("/view/num/{num}/pageNum/{pageNum}")
	public String view(@PathVariable int num,@PathVariable String pageNum, Model model) {
		bs.updateReadCount(num); 
		Board board = bs.select(num);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		return "view";
	}
	@RequestMapping("/updateForm/num/{num}/pageNum/{pageNum}")
	public String updateForm(@PathVariable int num,@PathVariable String pageNum, Model model) {
		Board board = bs.select(num);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		return "updateForm";
	}
	@RequestMapping("/update")
	public String update(Board board, String pageNum, Model model) {
		int result = bs.update(board);
		model.addAttribute("result", result);
//		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		return "update";
	}
	@RequestMapping("/deleteForm/num/{num}/pageNum/{pageNum}")
	public String deleteForm(@PathVariable int num,@PathVariable String pageNum, Model model) {
		Board board = bs.select(num);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		return "deleteForm";
	}
	@RequestMapping("/delete")
	public String delete(int num, String pageNum, Model model) {
		int result = bs.delete(num);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "delete";
	}
	@RequestMapping("/list")
	public String init() {
		return "redirect:/list/pageNum/1";
	}
	@RequestMapping("/list/pageNum/{pageNum}")		
	public String list(Board board,@PathVariable String pageNum, Model model) {
		if (pageNum == null || pageNum.equals("")) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int rowPerPage = 10;  // 한 화면에 보여주는 게시글 갯수
		int total = bs.getTotal(board);
//		int total = bs.getTotal();
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		board.setStartRow(startRow);
		board.setEndRow(endRow);
		List<Board> list = bs.list(board);
		/* List<Board> list = bs.list(startRow, endRow); */
		int num = total - startRow + 1;
		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
		String[] title = {"작성자","제목","내용","제목+내용"};
//		매개변수로 넘어온 데이터를 다시 전달할 때는 model.addAttribute() 생략 가능
//		model.addAttribute("board", board);
		model.addAttribute("title", title);
		model.addAttribute("num", num);
		model.addAttribute("pb", pb);
		model.addAttribute("list", list);
		return "list";
	}
}