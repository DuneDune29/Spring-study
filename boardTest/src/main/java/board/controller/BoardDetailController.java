package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDao;
import board.dto.BoardDto;


@Controller
public class BoardDetailController {
private BoardDao boardDao;

 public void setBoardDao(BoardDao boardDao) {
	this.boardDao = boardDao;
}
	
 @RequestMapping(value = "/board_detail.do",

			method = RequestMethod.GET)
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		int iSeq=ServletRequestUtils.getIntParameter(request, "seq");
		System.out.println(iSeq);
		BoardDto dto =boardDao.findBySeq(iSeq);
		
		mav.setViewName("detail");
		mav.addObject("board",dto);
		return mav;
	}
	}


