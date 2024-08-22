package service;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public enum FileService {
	
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private FileDAO dao = FileDAO.getinstance();
	
	
	
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	public FileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	public List<FileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	public void updateFileDownloadCount(String fno) {
		dao.updateFileDownloadCount(fno);
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
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp){
		
		//공유참조된 값 가져오기
		FileDTO fileDto = (FileDTO) req.getAttribute("fileDto");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			//response 헤더정보 수정
			resp.setContentType("application/octet-stream"); //파일다운로드에 사용
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDto.getOname(), "utf-8")); //응답헤더에 파일 첨부 정보를 설정 
			resp.setHeader("Content-Transfer-Encoding", "binary"); //데이터가 바이너리 형식으로 전송
			resp.setHeader("Pragma", "no-cache"); //브라우저 캐싱을 방지하기 위해 캐시를 사용하지 않도록 함
			resp.setHeader("Cache-Control", "private"); // 응답이 캐시되지 않도록 설정
	
			//파일 내용 스트림 처리
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/uploads"); //해당 폴더 경로를 얻음
			File file = new File(path + File.separator + fileDto.getSname()); //경로 + / + 파일명 
			
			bis = new BufferedInputStream(new FileInputStream(file)); //파일을 읽기위한 생성
			bos = new BufferedOutputStream(resp.getOutputStream()); //응답의 출력을 위한 생성
			
			bis.transferTo(bos);
			bos.flush();
		
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
