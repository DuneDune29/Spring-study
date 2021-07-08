package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	
	public static void main(String[] args) {
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		BoardVO vo = new BoardVO();
		
//		// insert 
//		vo.setTitle("동도로동동");
//		vo.setWriter("홍길동");
//		vo.setContent("도로도로동동 홍길동");
//		boardService.insertBoard(vo);
//		
//		// update
//		vo.setTitle("업데이트");
//		vo.setContent("배고팡");
//		vo.setSeq(3);
//		boardService.updateBoard(vo);
//		
//		// delete
//		vo.setSeq(3);
//		boardService.deleteBoard(vo);
		
		// get
		vo.setSeq(4);
		System.out.println(boardService.getBoard(vo));
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println("---> " + board.toString());
		}
		container.close();
	}
}
