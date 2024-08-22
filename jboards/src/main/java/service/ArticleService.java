package service;

import java.util.List;

import dao.ArticleDAO;
import dto.ArticleDTO;
import dto.PageGroupDTO;

public enum ArticleService {
	
	INSTANCE;
	
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	// 페이지 시작번호 (limit 용)
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10; //1페이지는 0
	}
	
	//현재 페이지 번호 구하기
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
			
		}
		return currentPage;
	}
	//현재 페이지 그룹 구하기
	public PageGroupDTO getCurrentPageGroup(int currentPage) {
		 int currentPageGroup = (int) Math.ceil(currentPage / 10.0); //11페이지 = 2그룹
		 int pageGroupStart = (currentPageGroup - 1) * 10 + 1; // ex : 2그룹의 시작번호
		 int pageGroupEnd = currentPageGroup * 10; //해당그룹의 끝 번호
		 
		 return new PageGroupDTO(pageGroupStart, pageGroupEnd);
	}
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	public List<ArticleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	public void updateArticle(String no) {
		dao.updateArticle(no);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}

}
