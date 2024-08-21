package service;



import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.FileDAO;
import dto.FileDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public enum FileService {
	
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private FileDAO dao = FileDAO.getinstance();
	
	
	
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	public FileDTO selectFile(int fno) {
		return dao.selectFile(fno);
	}
	public List<FileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	public void deleteFile(int fno) {
		dao.deleteFile(fno);
	}


	
	
	//파일 업로드 메소드
	public List<FileDTO> fileUpload(HttpServletRequest req) {
		
		List<FileDTO> files = new ArrayList<FileDTO>();
		
		ServletContext ctx = req.getServletContext(); //어플리케이션 내장객체를 바로 참조할 수 없어서
		String uploadPath = ctx.getRealPath("/uploads");
		logger.debug(uploadPath);
		
		try {
		Collection <Part> parts = req.getParts(); //part : 첨부파일 정보 객체 /컬렉션 : 리스트랑 비슷 , 첨부파일 객체 가져오기
		
			for (Part part : parts) { //폼에있는 입력갯수 만큼 반복
				
				//파일명 추출
				String ofileName = part.getSubmittedFileName();
				
				//파일을 첨부했으면
				if(ofileName != null && !ofileName.isEmpty()) {
					
					
					
					//고유 파일명 생성
					int idx = ofileName.lastIndexOf(".");
					String ext = ofileName.substring(idx);
					
					String sfileName = UUID.randomUUID().toString() + ext;
					
					logger.debug("ofileName : " + ofileName + "sfileName : " + sfileName);
					
					
					
					//파일저장
					part.write(uploadPath + File.separator + sfileName); //uploads 폴더에 파일저장
					
					
					//fileDTO 생성
					FileDTO fileDTO = new FileDTO();
					fileDTO.setOname(ofileName);
					fileDTO.setSname(sfileName);
					files.add(fileDTO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return files;
	}
	//파일 다운로드
	public void fileDownload() {
		
	}

}
