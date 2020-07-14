package com.sft.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sft.dto.Paging;
import com.sft.dto.Press;
import com.sft.dto.Revenue;
import com.sft.dto.Revenue_files;
import com.sft.service.PressService;
import com.sft.service.RevenueService;
import com.sft.util.Common;

import lombok.extern.java.Log;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log
public class MainController {

	@Autowired
	private PressService pressService;
	
	@Autowired
	private RevenueService revenueService;
	
	// 홈화면이동
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(Model model, HttpServletRequest req) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("COMPANY_NAME", Common.COMPANY_NAME);
		mv.setViewName("index");
		Common.toPrintIp(req);
		return mv;
	}
	
	// 검색엔진허용
	@RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
	public ModelAndView robots() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/include/robots"); // 뷰의 이름
		String str = "User-agent: *<br>" + "Allow: /";
		mv.addObject("str", str); // 뷰로 보낼 데이터 값
		return mv;
	}
	
	@RequestMapping(value = "/images", method = RequestMethod.GET)
	public ModelAndView image(HttpServletRequest req,HttpSession session) {
		session=req.getSession();
		String url = String.valueOf(req.getParameter("url"));
		String path=url;
		log.info("이미지 불러오기 "+path);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/common/image");
		mav.addObject("image",path);
		return mav;
	}

	// 실제 메뉴 이동로직
	@RequestMapping(value = "/sft", method = RequestMethod.GET)
	public ModelAndView sft(Model model, HttpServletRequest req) {
		ModelAndView mv=new ModelAndView();
		
		String url = String.valueOf(req.getParameter("page"));
		String page = "sft/" + url;

		log.info("=========실제 메뉴 이동로직==========");
		log.info("url : "+url);
		@SuppressWarnings("unchecked")
		Enumeration<String> names=req.getParameterNames();
		while(names.hasMoreElements()) {
			String name = (String)names.nextElement();
			log.info(name + " : " +req.getParameter(name));
		}
		log.info("============================");

		// 소통게시판 조회
		if ("Press".equals(url)) {
			Paging paing=new Paging();
			int pageNum=1;
			int pageSize=10;
			try{
				pageNum=Integer.parseInt(req.getParameter("pageNum"));
			}catch (NumberFormatException e) {
				log.info("페이지미지정");
			}
			int v_pageSize=(pageNum-1)*pageSize;
			int v_pageOffset=(pageSize-1);
			
			paing.setPageSize(v_pageSize);
			paing.setPageOffset(v_pageOffset);
			
			int selectTotalCount=pressService.selectTotalCount();
			
			int startNum=pageNum;
			int endNum=selectTotalCount/pageSize;

			if((endNum-startNum)<10) {
				endNum=startNum+9;
			}
			model.addAttribute("startNum", startNum);
			model.addAttribute("endNum", endNum);
			
			model.addAttribute("pressList", pressService.selectList(paing));
			model.addAttribute("msg", String.valueOf(req.getParameter("msg")));
			
			
		}else if ("Press_detail".equals(url)) {
			int num = Integer.parseInt(String.valueOf(req.getParameter("num")));
			model.addAttribute("pressDetail", pressService.selectOne(num));
		}else if ("Press_delete".equals(url)) {
			Press press = new Press();
			press.setNum(Integer.parseInt(String.valueOf(req.getParameter("num"))));
			pressService.delete(press);
			page = "redirect:sft?page=Press&SFT=admin&msg=success";
		}
		

		//수인인증 조회
		else if("Revenue".equals(url)) {
			model.addAttribute("revenueList", revenueService.selectList());
			model.addAttribute("msg", String.valueOf(req.getParameter("msg")));
		}else if ("Revenue_detail".equals(url)) {
			int num = Integer.parseInt(String.valueOf(req.getParameter("num")));
			model.addAttribute("revenueDetail", revenueService.selectOne(num));
			model.addAttribute("revenueDetailFiles", revenueService.selectOneFile(num));
		}else if ("Revenue_delete".equals(url)) {
			Revenue revenue = new Revenue();
			revenue.setNum(Integer.parseInt(String.valueOf(req.getParameter("num"))));
			revenueService.delete(revenue);
			Revenue_files revenue_files = new Revenue_files();
			revenue_files.setUpper_num(Integer.parseInt(String.valueOf(req.getParameter("num"))));
			revenueService.delete_file(revenue_files);
			page = "redirect:sft?page=Revenue&SFT=admin&msg=success";
		}
		

		
		
		// 어드민권한체크
		String admin = String.valueOf(req.getParameter("FX"));
		if ("admin".equals(admin)) {
			mv.addObject("admin", "Y");
		}


		mv.addObject("COMPANY_NAME", Common.COMPANY_NAME);
		mv.setViewName(page);
		Common.toPrintIp(req);
		return mv;
		
	}

	
	
	
	// 실제 작업 로직
	@RequestMapping(value = "/sft", method = RequestMethod.POST)
	public String fx_go(Model model, HttpServletRequest req) {
		String url = String.valueOf(req.getParameter("page"));
		String page = "/";

		log.info("=========실제 작업 로직==========");
		@SuppressWarnings("unchecked")
		Enumeration<String> names=req.getParameterNames();
		while(names.hasMoreElements()) {
			String name = (String)names.nextElement();
			log.info(name + " : " +req.getParameter(name));
		}
		log.info("==========================");
		if ("Press_insert".equals(url)) {
			Press press = new Press();
			press.setTitle(String.valueOf(req.getParameter("title")));
			press.setTxusrnm(String.valueOf(req.getParameter("txusrnm")));
			press.setReg_date(Common.getthisTime());
			press.setContent(String.valueOf(req.getParameter("content")));
			pressService.insert(press);
			page = "redirect:sft?page=Press&SFT=admin&msg=success";
		}else if ("Press_update".equals(url)) {
			Press press = new Press();
			press.setTitle(String.valueOf(req.getParameter("title")));
			press.setTxusrnm(String.valueOf(req.getParameter("txusrnm")));
			press.setReg_date(Common.getthisTime());
			press.setContent(String.valueOf(req.getParameter("content")));
			press.setNum(Integer.parseInt(String.valueOf(req.getParameter("num"))));
			pressService.update(press);
			page = "redirect:sft?page=Press&SFT=admin&msg=success";
		}
		return page;
	}
	
	// 멀티파일 작업 로직
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String file(Model model, HttpServletRequest req, MultipartHttpServletRequest mreq) {
		String page = "/";
		page = "redirect:sft?page=Revenue&SFT=admin&msg=success";
		// 수익인증 작업
		String murl = String.valueOf(mreq.getParameter("page"));
		if ("Revenue_insert".equals(murl)) {
			log.info("mreq : " + mreq);
			log.info("uploadFile : " + mreq.getFileNames());

			Map<String, List<MultipartFile>> files = mreq.getMultiFileMap();

			List<MultipartFile> list = files.get("in_file");
			int index = 0;

			int num = revenueService.seq();
			String reg_date = Common.getthisDay();
			log.info("num : "+num);
			for (MultipartFile mfile : list) {
				long fileSize = mfile.getSize();
				log.info("mfile.getSize() : " + mfile.getSize());
				if (fileSize > 0.0) {
					// 파일업로드후 적재
					String filename = saveFile(req, mfile);
					Revenue_files revenue_files = new Revenue_files();
					revenue_files.setUpper_num(num);
					revenue_files.setNum(++index);
					revenue_files.setFile_path(filename);
					revenue_files.setReg_date(reg_date);
					log.info("revenue_files : "+revenue_files.toString());
					revenueService.insert_files(revenue_files);
				}
			}

			if (index > 0) {
				Revenue revenue = new Revenue();
				revenue.setNum(num);
				revenue.setTitle(String.valueOf(mreq.getParameter("title")));
				revenue.setTxusrnm(String.valueOf(mreq.getParameter("txusrnm")));
				revenue.setReg_date(Common.getthisTime());
				revenue.setContent(String.valueOf(mreq.getParameter("content")));
				revenueService.insert(revenue);
			}

			page = "redirect:sft?page=Revenue&SFT=admin&msg=success";
		}
		return page;
	}

	// 파일저장
	private String saveFile(HttpServletRequest req, MultipartFile mfile) {
		String thisday = Common.getthisDay();

		String orginPath = "/resources/fileUpload/" + thisday + "/";
		String path = req.getSession().getServletContext().getRealPath(orginPath);
		log.info("path : " + path);

		// 1. 저장폴더생성
		File Folder = new File(path);
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다.
				log.info("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			log.info("이미 폴더가 생성되어 있습니다.");
		}

		//외부파일
		String uploadFileName=mfile.getOriginalFilename();
		//결과저장소
		String thisTimeStr=Common.getthisTimeStr();
		String resultFileName=path+thisTimeStr+"_"+uploadFileName;
		String resultFileName2=orginPath+thisTimeStr+"_"+uploadFileName;
		// 2.  파일저장
		File saveFile = new File(resultFileName);
		try {
			mfile.transferTo(saveFile);
		} catch (IOException e) {
			System.out.println("오류");
			e.printStackTrace();
		}

		return resultFileName2;
	}

}
