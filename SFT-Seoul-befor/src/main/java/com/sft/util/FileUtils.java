package com.sft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


/**
 * <pre>
 * 시스템명   : JTSB
 * 업무분류   : 공통
 * 클래스명   : FileUtils
 * 클래스개요 : 파일에 대한 유틸클래스
 * 수정내역   :
 *   수정자	  수정일자			수정내역
 *  -------	----------- ------------------------
 *   최규현	2020.01.12 	 최초작성
 * <pre>
 * 
 * @version 2020.01.12
 * @author 최규현
 */
public class FileUtils {

	private final static Logger log = LoggerFactory.getLogger(FileUtils.class);
	
	// 파일사이즈 1K
	static final long BUFFER_SIZE     = 1024L;
	// 파일구분자
	static final char FILE_SEPARATOR  = File.separatorChar;
	
	// 윈도우시스템 파일 접근권한
	static final char ACCESS_READ     = 'R';    // 읽기전용
	static final char ACCESS_SYS     = 'S';    // 시스템
	static final char ACCESS_HIDE     = 'H';    // 숨김
	
	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	private static FileOutputStream fos;

	private FileUtils() {}
	
	/**
	 * <p>파일업로드를 처리한다.</p>
	 * 
	 * @param MultipartFile file, String CUST_ID
	 * @return String realFileName(실제 저장된 파일명)
	 */	
	public static void uploadFile(String filePath, MultipartFile file) throws Exception {
		
		String fileName = FILE_SEPARATOR + file.getOriginalFilename();

		try{
			fos = new FileOutputStream(filePath + fileName);
			byte fileData[] = file.getBytes();
            fos.write(fileData); 
            
        }catch(Exception e){
        	log.error("▷▶▷▶▷ FileUtil uploadFile Fail!! ◁◀◁◀◁"); log.error("upload Exception", e);
            throw e;
            
        }finally{
            if(fos != null){
                try{ 
                	fos.close();
                }catch(Exception e){
                	log.error("▷▶▷▶▷ FileUtil uploadFile FileOutputStream close Fail!! ◁◀◁◀◁");
                	throw e;
                }
            }
        }
	}
	
	/**
	 * <p>파일업로드를 처리한다.</p>
	 * 
	 * @param MultipartFile file, String CUST_ID
	 * @return String realFileName(실제 저장된 파일명)
	 */	
	public static void uploadFile(String filePath, MultipartFile file, String fileName) throws Exception {
		
		try{
			fos = new FileOutputStream(filePath + FILE_SEPARATOR + fileName);
			byte fileData[] = file.getBytes();
            fos.write(fileData); 
            
        }catch(Exception e){
        	log.error("▷▶▷▶▷ FileUtil uploadFile Fail!! ◁◀◁◀◁"); log.error("upload Exception", e);
            throw e;
            
        }finally{
            if(fos != null){
                try{ 
                	fos.close();
                }catch(Exception e){
                	log.error("▷▶▷▶▷ FileUtil uploadFile FileOutputStream close Fail!! ◁◀◁◀◁");
                	throw e;
                }
            }
        }
	}
	
	/**
	 * <p>폴더 생성메소드</p>
	 * <p>해당폴더가 없으면 생성한다.</p>
	 * @param String filePath
	 * @return boolean
	 */
	public static boolean makeFolder(String filePath){
		
		File checkFolder = new File(filePath);
		
		if( !(checkFolder.exists()) ){
			return checkFolder.mkdir(); //폴더생성
		}else{
			return true;
		}
	}
	
	/**
	 * <p>파일 중복여부를 반환한다.</p>
	 * 
	 * @param File fileName(DB에 저장된 파일명)
	 * @return boolean
	 */	
	public static boolean overlapCheck(File file){
		return (file.exists() && file.isFile());
	}
	
	/**
	 * <p>파일삭제를 처리한다.</p>
	 * 
	 * @param String fileName(DB에 저장된 파일명)
	 * @return 
	 */	
	public static void deleteFile(String fileName){
		File DelFile = new File(fileName);
		DelFile.delete();
	}
	
	/**
	 * <p>디렉토리 존재여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.isExistDirectory("C:/") = true
	 * FileUtil.isExistDirectory("abcd") = false
	 * </pre>
	 * 
	 * @param targetDirPath 존재여부를 확인할 디렉토리의 절대경로
	 * @return 디렉토리 존재 여부
	 */
	public static boolean isExistDirectory(String targetDirPath) throws Exception {

		// 인자값 유효하지 않은 경우 공백 리턴
		if (targetDirPath==null || targetDirPath.equals("")){
			return false;
		}

		boolean result = false;
		File f = null;
		try
		{   
			f = new File (targetDirPath);
			if(f.exists() && f.isDirectory()){
				result = true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리 존재여부를 확인한다. (하위디렉토리 확인용)</p>
	 * 
	 * <pre>
	 * FileUtil.getExistDirectory("C:/", "test", 1) = {"C:/test/"}
	 * FileUtil.getExistDirectory("C:/", "test", 2) = {"C:/test/", "C:/abc/test/"}
	 * </pre>
	 * 
	 * @param baseDirPath   존재여부를 확인할 디렉토리명의 기준경로
	 * @param targetDirPath 확인할 대상 디렉토리. baseDirPath 하위에서 존재여부를 확인한다.
	 * @param cnt           확인할 디렉토리 갯수 (0보다 큰값이 입력되어야 한다. -1 입력시 21474846까지 찾도록  지원함 )
	 * @return 존재하는 디렉토리 경로 리스트
	 */
	public static List<String> getExistDirectory(String baseDirPath, String targetDirPath, int cnt) throws Exception {

		// 인자값 유효하지 않은 경우 빈 ArrayList 리턴
		if (baseDirPath==null || baseDirPath.equals("") || targetDirPath==null || targetDirPath.equals("") || cnt==0){
			return new ArrayList<String>();
		}
		int dirCnt = 0;
		if(cnt < 0 ) dirCnt = 21474846;
		else dirCnt = cnt;

		// 찾은 결과를 전달할 ArrayList
		List<String> result = new ArrayList<String>();
		// 하위의 결과를 임시 보관할 ArrayList
		List<String> subResult = new ArrayList<String>();
		// 현재경로(baseDirPath)에서 발견된 targetDirPath 갯수
		int dirFindCnt = 0;
		boolean isExist = false;
		String[] dirList = null; 
		String subDirPath = "";
		File f = null;

		try
		{   
			f = new File (baseDirPath);
			isExist = f.exists();

			if(isExist){
				dirList = f.list();
			}
			for (int i = 0; dirList != null && i < dirList.length; i++) {
				//log.debug("dirList["+i+"]:"+dirList[i] +"--->"+baseDirPath+"/"+dirList[i]);
				subDirPath = baseDirPath+"/"+dirList[i];
				//log.debug("_"+targetDirPath+"_");
				//log.debug("_"+dirList[i]+"_");

				f = new File(subDirPath);

				//현재경로(baseDirPath)에서 검색
				if(targetDirPath.equals(dirList[i])){
					// 중간에 발견하면 반복체크는 종료한다.(결과요청 갯수에 도달한 경우에 한해) - 이곳에서 종료되면 이후 하위에서 체크할 필요가 없다.
					if(new File(baseDirPath+"/"+dirList[i]).isDirectory()){
						dirFindCnt++;
						result.add(baseDirPath+"/"+dirList[i]);
						if(dirFindCnt == dirCnt)
							break;
					}
				}

				//현재경로(baseDirPath)에서 발견된 하위 경로에서 반복하여 재귀적으로 검색
				int subCnt = dirCnt-dirFindCnt;
				if(f.isDirectory()){
					//log.debug("f.isDirectory():"+f.isDirectory());
					subResult = getExistDirectory(subDirPath,targetDirPath, subCnt);
					// 하위에서 발견된 디렉토리 갯수를 현재까지 찾은 디렉토리갯수에 추가한다.
					dirFindCnt = dirFindCnt + subResult.size();
					// 하위에서 모두 발견된 경우 반복 체크는 종료한다.
					if(dirCnt<=dirFindCnt){ 
						for(int j =0; j< subResult.size(); j++){
							result.add((String)subResult.get(j));
						}

						break;
					}else{
						for(int j =0; j< subResult.size(); j++){
							result.add((String)subResult.get(j));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리 존재여부를 반환한다. (생성일자를 조건으로 조건구간내 포함되는지 확인)</p>
	 * 
	 * <pre>
	 * FileUtil.isExistDirectory("C:/","20110101","20111231") = true
	 * FileUtil.isExistDirectory("C:/","20100101","20101231") = false
	 * </pre>
	 * 
	 * @param targetDirPath 존재여부를 확인할 디렉토리의 절대경로
	 * @param fromDate  생성일자 조건에 해당하는 시작일자(yyyyMMdd)
	 * @param toDate    생성일자 조건에 해당하는 종료일자(yyyyMMdd)
	 * @return 디렉토리 존재 여부
	 */
	public static boolean isExistDirectory(String targetDirPath, String fromDate, String toDate) throws Exception {

		// 인자값 유효하지 않은 경우 공백 리턴
		if (targetDirPath==null || targetDirPath.equals("") || fromDate==null || fromDate.equals("") || toDate==null || toDate.equals("")){
			return false;
		}

		boolean result = false;
		String lastModifyedDate = "";        
		File f = null;

		try
		{
			f = new File (targetDirPath);
			lastModifyedDate = getModifiedDate(f);
			//log.debug("getLastModifiedDateFromFile(f):"+lastModifyedDate);
			if(Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate) 
					&& Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate) ){
				result = true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리(파일)의 최종 수정일자를 반환한다. (기본로케일 java.util.Locale.KOREA 기준)</p>
	 * 
	 * <pre>
	 * FileUtil.getModifiedDateFromFile(File객체) = "20101102"
	 * </pre>
	 * 
	 * @param file 파일객체
	 * @return 마지막 수정일자 문자열
	 */
	public static String getModifiedDate(File file){

		String result = "";
		try{
			if(file.exists()){
				long date  = file.lastModified();
				java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd",java.util.Locale.KOREA);
				result = dateFormat.format(new java.util.Date(date));
			}else{
				result = "";
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리(파일)의 최종 수정일자를 반환한다. (기본로케일 java.util.Locale.KOREA 기준)</p>
	 * 
	 * <pre>
	 * FileUtil.getLastModifiedDateFromFile("c:/test") = "20101102"
	 * </pre>
	 * 
	 * @param filePath 파일경로
	 * @return 마지막 수정일자 문자열
	 */
	public static String getModifiedDate(String filePath){

		File f = null;
		String result = "";
		try
		{
			f = new File(filePath);
			result = getModifiedDate(f);
		}catch (Exception e ){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>조건구간내에 생성된 디렉토리 목록을 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.getDirectoryListFromLastModifiedDate("c:/test","20101102","20101104") = {...}
	 * </pre>
	 * 
	 * @param filePath 하위 디렉토리를 확인할 경로
	 * @param fromDate 조건시작일
	 * @param toDate   조건 종료일
	 * @return 조건 구간내에 생성된 디렉토리 목록
	 */
	public static List<String> getDirectoryListFromModifiedDate(String baseDirPath , String fromDate, String toDate){

		// 인자값 유효하지 않은 경우 빈 ArrayList 리턴
		if (baseDirPath==null || baseDirPath.equals("") || fromDate==null || fromDate.equals("") || toDate==null || toDate.equals("")){
			return new ArrayList<String>();
		}

		File f = null;
		File childFile = null;
		String[] subDirList;
		String subDirPath = "";
		List<String> childResult = null;
		List<String> result = new ArrayList<String>();

		try
		{
			f = new File(baseDirPath);
			subDirList  = f.list();
			for(int i=0; i<subDirList.length; i++){

				subDirPath = baseDirPath+"/"+subDirList[i];
				childFile = new File(subDirPath); 
				if(childFile.isDirectory()){
					//childResult = getLastDirectoryForModifiedDate(subDirPath , fromDate, toDate);
					String lastModifyedDate = getModifiedDate(childFile);
					if(Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate)
							&& Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate) ){
						result.add(baseDirPath+"/"+subDirList[i]);
					}
					childResult = getDirectoryListFromModifiedDate(baseDirPath+"/"+subDirList[i] , fromDate, toDate);
					// 하위디렉토리의 결과를 추가한다.
					for(int j =0; j< childResult.size(); j++){
						result.add((String)childResult.get(j));
					}
				}
			}
		}catch (Exception e ){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리(파일)의 읽기권한을 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.canRead("c:/test") = true
	 * </pre>
	 * 
	 * @param filePath 디렉토리(파일) 경로
	 * @return 읽기권한 여부
	 */
	public static boolean canRead(String filePath){

		// 인자값 유효하지 않은 경우 빈 false 리턴
		if (filePath==null || filePath.equals("") ){
			return false;
		}

		File f = null;
		boolean result = false;
		try
		{
			f = new File(filePath);
			if(f.exists()){
				result = f.canRead();    
			}
		}catch (Exception e ){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리(파일)의 쓰기권한을 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.canWrite("c:/test") = true
	 * </pre>
	 * 
	 * @param filePath 디렉토리(파일) 경로
	 * @return 쓰기권한 여부
	 */
	public static boolean canWrite(String filePath){

		// 인자값 유효하지 않은 경우 빈 false 리턴
		if (filePath==null || filePath.equals("") ){
			return false;
		}

		File f = null;
		boolean result = false;
		try
		{
			f = new File(filePath);
			if(f.exists()){
				result = f.canWrite();    
			}
		}catch (Exception e ){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리(파일)의 이름을 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.getName("c:/test") = "test"
	 * </pre>
	 * 
	 * @param filePath 디렉토리(파일) 경로
	 * @return 디렉토리(파일) 이름
	 */
	public static String getName(String filePath){

		// 인자값 유효하지 않은 경우 빈 false 리턴
		if (filePath==null || filePath.equals("") ){
			return "";
		}

		File f = null;
		String result = "";
		try
		{
			f = new File(filePath);
			if(f.exists()){
				result = f.getName();    
			}
		}catch (Exception e ){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리(파일)를 삭제한다.</p>
	 * 
	 * <pre>
	 * FileUtil.deletePath("c:/test") = "c:/test"
	 * </pre>
	 * 
	 * @param filePath 삭제할 디렉토리(파일) 경로
	 * @return 삭제한 디렉토리(파일) 경로
	 */
	public  static String deletePath(String filePath){
		File file = new File(filePath);
		String result = "";
		try{
			if(file.exists()){
				result = file.getAbsolutePath();
				if(!file.delete()){
					result="";
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>디렉토리에 파일이 존재하는지 여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.isExistFileName("c:/test", "info.txt") = true
	 * </pre>
	 * 
	 * @param dirPath 디렉토리 경로
	 * @param file 존재여부를 확인할 파일명
	 * @return 파일존재 유무
	 */
	public static boolean isExistFileName(String dirPath, String file) throws Exception {

		// 파일 존재 여부
		boolean result = false;

		// 디렉토리 오픈
		String drctry = dirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcDrctry = new File(drctry);

		// 디렉토리이면서, 존재하면
		if (srcDrctry.exists() && srcDrctry.isDirectory()) {

			// 디렉토리 안 목록을 조회한다. (파일명)
			File [] fileArray = srcDrctry.listFiles();
			List<String> list = getPathListByName(fileArray, file);
			if (list != null && list.size() > 0) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * <p>디렉토리에 특정 확장자를 가진 파일이 존재하는지 여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.isExistDirByExt("c:/test", ".txt") = true
	 * </pre>
	 * 
	 * @param dir 디렉토리
	 * @param extention 확장자명(.txt 형태 입력)
	 * @return 존재유무
	 */
	public static boolean isExistFileInDirByExt(String dir, String extention) throws Exception {

		// 파일 존재 여부
		boolean result = false;

		// 디렉토리 오픈
		String drctry = dir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcDrctry = new File(drctry);

		// 디렉토리이면서, 존재하면
		if (srcDrctry.exists() && srcDrctry.isDirectory()) {

			// 디렉토리 안 목록을 조회한다. (확장자별)
			File [] fileArray = srcDrctry.listFiles();
			List<String> list = getPathListByExt(fileArray, extention);
			if (list != null && list.size() > 0) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * <p>디렉토리에 수정기간내에 존재하는 파일이 존재하는지 여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.isExistFileByUpdateDate("c:/test", "20110101", "20110305") = true
	 * </pre>
	 * 
	 * @param fromUpdDate 수정일자 From(yyyyMMdd)
	 * @param updtTo   수정일자 To(yyyyMMdd)
	 * @return 존재여부
	 * @exception Exception
	 */
	public static boolean isExistFileByUpdateDate(String dir, String fromUpdDate, String updtTo) throws Exception {

		// 파일 존재 여부
		boolean result = false;

		// 디렉토리 오픈
		String drctry = dir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcDrctry = new File(drctry);

		// 디렉토리이면서, 존재하면
		if (srcDrctry.exists() && srcDrctry.isDirectory()) {

			// 디렉토리 안 목록을 조회한다. (수정기간별)
			File [] fileArray = srcDrctry.listFiles();
			List<String> list = getPathListByUpdateDate(fileArray, fromUpdDate, updtTo);
			if (list != null && list.size() > 0) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * <p>디렉토리에 사이즈내에 파일이 존재하는지 여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.isExistFileBySize("c:/test", 10, 100) = true
	 * </pre>
	 * 
	 * @param  sizeFrom 사이즈From (KB)
	 * @param  sizeTo 사이즈To (KB)
	 * @return 존재여부
	 * @exception Exception
	 */
	public static boolean isExistFileBySize(String dirPath, long sizeFrom, long sizeTo) throws Exception {

		// 파일 존재 여부
		boolean result = false;

		// 디렉토리 오픈
		String drctry = dirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcDrctry = new File(drctry);

		// 디렉토리이면서, 존재하면
		if (srcDrctry.exists() && srcDrctry.isDirectory()) {

			// 디렉토리 안 목록을 조회한다. (사이즈별)
			File [] fileArray = srcDrctry.listFiles();
			List<String> list = getPathListBySize(fileArray, sizeFrom, sizeTo);
			if (list != null && list.size() > 0) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * <p>파일객체 배열에서 파일의 절대경로 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param fileArray 파일객체 배열
	 * @return 파일의 절대경로 리스트
	 * @exception Exception
	 */
	public static List<String> getPathListOfFiles(File[] fileArray) throws Exception {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File [] tmpArray = fileArray[i].listFiles();
				list.addAll(getPathListOfFiles(tmpArray));
				// 파일이면 담는다.
			} else {
				list.add(fileArray[i].getAbsolutePath());
			}
		}

		return list;
	}

	/**
	 * <p>파일객체 배열에서 파일이름이 동일한 파일의 절대경로 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param fileArray 파일객체 배열
	 * @param file 검색할 파일명
	 * @return 파일의 절대경로 리스트
	 * @exception Exception
	 */
	public static List<String> getPathListByName(File[] fileArray, String file) throws Exception {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File [] tmpArray = fileArray[i].listFiles();
				list.addAll(getPathListByName(tmpArray, file));
				// 파일이면 파일명이 같은지 비교한다.
			} else {
				if (fileArray[i].getName().equals(file)) {
					list.add(fileArray[i].getAbsolutePath());
				}
			}
		}

		return list;
	}

	/**
	 * <p>파일객체 배열에서 파일확장자가 동일한 파일의 절대경로 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param fileArray 파일객체 배열
	 * @param ext 검색할 파일확장자
	 * @return 파일의 절대경로 리스트
	 * @exception Exception
	 */
	public static List<String> getPathListByExt(File[] fileArray, String ext) throws Exception {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File [] tmpArray = fileArray[i].listFiles();
				list.addAll(getPathListByExt(tmpArray, ext));
				// 파일이면 확장자명이 들어있는지 비교한다.
			} else {
				if (fileArray[i].getName().indexOf(ext) != -1) {
					list.add(fileArray[i].getAbsolutePath());
				}
			}
		}

		return list;
	}

	/**
	 * <p>해당 수정일자 기간내에 수정된 파일을 찾아서 절대경로 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param fileArray   파일객체 배열
	 * @param formUpdDate 검색할 파일 수정일(~부터) yyyyMMdd
	 * @param toUpdDate   검색할 파일 수정일(~까지) yyyyMMdd
	 * @return 파일의 절대경로 리스트
	 * @exception Exception
	 */
	public static List<String> getPathListByUpdateDate(File[] fileArray, String formUpdDate, String toUpdDate) throws Exception {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File [] tmpArray = fileArray[i].listFiles();
				list.addAll(getPathListByUpdateDate(tmpArray, formUpdDate, toUpdDate));
				// 파일이면 수정기간내에 존재하는지 비교한다.
			} else {
				// 파일의 최종수정일자 조회
				long date  = fileArray[i].lastModified();
				java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd",java.util.Locale.KOREA);
				String lastUpdtDate = dateFormat.format(new java.util.Date(date));
				// 수정기간 내에 존재하는지 확인
				if(Integer.parseInt(lastUpdtDate) >= Integer.parseInt(formUpdDate) 
						&& Integer.parseInt(lastUpdtDate) <= Integer.parseInt(toUpdDate) ){
					list.add(fileArray[i].getAbsolutePath());
				}
			}
		}

		return list;
	}

	/**
	 * <p>해당 파일 사이즈내에 속하는 파일을 찾아서 절대경로 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param fileArray   파일객체 배열
	 * @param fromSize 검색할 파일 사이즈(~부터) (KB)
	 * @param toSize   검색할 파일 사이즈(~까지) (KB)
	 * @return 파일의 절대경로 리스트
	 * @exception Exception
	 */
	public static List<String> getPathListBySize(File[] fileArray, long fromSize, long toSize) throws Exception {

		List<String>  list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File [] tmpArray = fileArray[i].listFiles();
				list.addAll(getPathListBySize(tmpArray, fromSize, toSize));
				// 파일이면, 사이즈내에 존재하는지 비교한다.
			} else {
				// 파일의 사이즈 조회
				long size  = fileArray[i].length();
				// 사이즈 내에 존재하는지 확인
				if(size >= (fromSize*BUFFER_SIZE) && size <= (toSize*BUFFER_SIZE)){
					list.add(fileArray[i].getAbsolutePath());
				}
			}
		}

		return list;
	}

	/**
	 * <p>디렉토리를 생성한다.</p>
	 * 
	 * <pre>
	 * FileUtil.createDirectory("c:/test") = "c:/test"
	 * </pre>
	 * 
	 * @param dirPath 생성할 디렉토리 경로
	 * @return 생성한 디렉토리 경로
	 */
	public  static String createDirectory(String dirPath){

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (dirPath==null || dirPath.equals("")){
			return "";
		}

		File file = new File(dirPath);
		String result = "";
		try{
			// 없으면 생성
			if(file.exists()){
				// 혹시 존재해도 파일이면 생성 - 생성되지 않는다.(아래는 실질적으로는 진행되지 않음)
				if(file.isFile()){
					//new File(file.getParent()).mkdirs();
					if (file.mkdirs()){
						result = file.getAbsolutePath();    
					}
				}else{
					result = file.getAbsolutePath(); 
				}
			}else{
				// 존해하지 않으면 생성 
				if (file.mkdirs()){
					result = file.getAbsolutePath();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>파일을 생성한다.</p>
	 * 
	 * <pre>
	 * FileUtil.createFile("c:/test/abc") = "c:/test/abc"
	 * </pre>
	 * 
	 * @param dirPath 생성할 파일 경로
	 * @return 생성한 파일 경로
	 */
	public  static String createFile(String filePath){

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (filePath==null || filePath.equals("")){
			return "";
		}

		File file = new File(filePath);
		String result = "";
		try{
			if(file.exists()){
				result = filePath;
			}else{
				// 존재하지 않으면 생성함
				new File(file.getParent()).mkdirs();
				if(file.createNewFile()){
					result = file.getAbsolutePath();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>하위의 파일 및 디렉토리와 함께 디렉토리를 삭제한다.</p>
	 * 
	 * <pre>
	 * FileUtil.deleteDirectory("c:/test/abc") = "c:/test/abc"
	 * </pre>
	 * 
	 * @param dirPath 삭제하고자 하는디렉토리의 절대경로
	 * @return 성공하면  삭제된 절대경로, 아니면 ""
	 */
	public  static String deleteDirectory(String dirPath){

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (dirPath==null || dirPath.equals("")){
			return "";
		}
		String result="";
		File file = new File(dirPath);
		if(file.isDirectory()){
			String[] fileList = file.list();
			//소속된 파일을 모두 삭제
			for(int i =0 ; i<fileList.length; i++){

				//log.debug("fileList["+i+"] : "+ dirDeletePath +"/"+fileList[i]);
				File f = new File(dirPath+"/"+fileList[i]) ;
				if(f.isFile()){
					//디렉토리에 속한 파일들을 모두 삭제한다.
					f.delete();
				}else{
					//디렉토리에 속한 하위 디렉토리들에 대한 삭제 명령을 재귀적으로 호출시킨다.
					deleteDirectory(dirPath+"/"+fileList[i]);
				}
			}
			// 디렉토리에 속한 파일들과 하위 디렉토리가 삭제되었으면 디렉토리 자신을 삭제한다.
			result = deletePath(dirPath);
		}else{
			result = "";
		}

		return result;
	}

	/**
	 * <p>파일을 삭제한다.</p>
	 * 
	 * <pre>
	 * FileUtil.deleteFile("c:/test/abc") = "c:/test/abc"
	 * </pre>
	 * 
	 * @param filePath 삭제하고자 하는파일의 절대경로
	 * @return 성공하면  삭제된 절대경로, 아니면 ""
	 */
	public  static String deleteFileOK(String filePath){

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (filePath==null || filePath.equals("")){
			return "";
		}
		String result="";
		File file = new File(filePath);
		if(file.isFile()){
			result = deletePath(filePath);
		}else{
			result = "";
		}

		return result;
	}

	/**
	 * <p>폴더내에서 찾고자하는 수정날짜를 가진 파일의 절대경로 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.getFileListByDate("c:/test","20111011") = {"c:/test/abc.txt","c:/test/bbb.txt"}
	 * </pre>
	 * 
	 * @param dirPath    디렉토리경로 문자열
	 * @param updateDate 수정일자 문자열(yyyyMMdd)
	 * @return 파일 절대경로 리스트 
	 */
	public static List<String> getFileListByDate(String dirPath, String updateDate) throws Exception {

		// 결과 목록
		List<String> list = null;

		// 디렉토리 오픈
		String drctry1 = dirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(drctry1);

		// 디렉토리이며, 존재하면 최종수정일자가 같은 파일목록 조회 시작
		if (file.exists() && file.isDirectory()) {
			File [] fileArray = file.listFiles();
			list = getFilesByDate(fileArray, updateDate);
		}

		return list;
	}

	/**
	 * <p>폴더내에서 찾고자하는 수정날짜 범위내에 있는 파일의 절대경로 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.getFileListByDate("c:/test","20111011" ,"20111201") = {"c:/test/abc.txt","c:/test/bbb.txt"}
	 * </pre>
	 * 
	 * @param dirPath  디렉토리경로 문자열
	 * @param fromDate 수정일자 문자열(~부터) (yyyyMMdd)
	 * @param toDate   수정일자 문자열(~까지) (yyyyMMdd)
	 * @return 파일 절대경로 리스트 
	 */
	public static List<String> getFileListByUpdateDate(String dirPath, String fromDate, String toDate) throws Exception {

		// 결과 목록
		List<String> list = null;

		// 디렉토리 오픈
		String drctry1 = dirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(drctry1);

		// 디렉토리이며, 최종수정기간내  존재하는 파일목록 조회 시작
		if (file.exists() && file.isDirectory()) {
			File [] fileArray = file.listFiles();
			list = getPathListByUpdateDate(fileArray, fromDate, toDate);
		}

		return list;
	}

	/**
	 * <p>파일목록에서 특정 수정날짜인 파일의 절대경로 문자열 리스트를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.getFilesByDate({"C:/test/aaa.txt", "C:/test/bbb.txt"}, "20100102") = {"C:/test/bbb.txt"} 
	 * </pre>
	 * 
	 * @param fileArray  파일 객체 리스트
	 * @param updateDate 수정일자 문자열 (yyyMMdd)
	 * @return 파일 절대경로 문자열 리스트
	 * @exception Exception
	 */
	public static List<String> getFilesByDate(File[] fileArray, String updateDate) throws Exception {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File [] tmpArray = fileArray[i].listFiles();
				list.addAll(getFilesByDate(tmpArray, updateDate));
				// 파일이면 파일명이 같은지 비교한다.
			} else {
				// 파일의 최종수정일자 조회
				long date  = fileArray[i].lastModified();
				java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd",java.util.Locale.KOREA);
				String lastUpdtDate = dateFormat.format(new java.util.Date(date));
				if(Integer.parseInt(lastUpdtDate) == Integer.parseInt(updateDate)){
					list.add(fileArray[i].getAbsolutePath());
				}
			}
		}

		return list;
	}

	/**
	 * <p>두 개의 파일의 파일사이즈가 동일한지 여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.compareFileSize({"C:/test/aaa.txt", "C:/test/bbb.txt") = true
	 * </pre>
	 * 
	 * @param compFile1  파일경로 문자열
	 * @param compFile2  파일경로 문자열
	 * @return 두 개의 파일의 파일사이즈가 동일한지 여부
	 * @exception Exception
	 */
	public static boolean compareFileSize(String filepath1, String filepath2) throws Exception {

		// 파일 동일 여부
		boolean result = false;

		// 파일 오픈
		String cmprFile11 = filepath1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String cmprFile22 = filepath2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file1 = new File(cmprFile11);
		File file2 = new File(cmprFile22);

		// 파일이며, 존재하면 파일 사이즈 비교
		if (file1.exists() && file2.exists()
				&& file1.isFile() && file2.isFile()) {

			// 파일1 사이즈
			long size1 = file1.length();

			// 파일2 사이즈
			long size2 = file2.length();

			// 사이즈 비교
			if (size1 == size2) {
				result = true;
			}

		}

		return result;
	}

	/**
	 * <p>두 개의 파일의 수정일자가 동일한지 여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.compareFileUpdateDate({"C:/test/aaa.txt", "C:/test/bbb.txt") = true
	 * </pre>
	 * 
	 * @param compFile1  파일경로 문자열
	 * @param compFile2  파일경로 문자열
	 * @return 두 개의 파일의 수정일자가 동일한지 여부
	 * @exception Exception
	 */
	public static boolean compareFileUpdateDate(String cmprFile1, String cmprFile2) throws Exception {

		// 파일 동일 여부
		boolean result = false;

		// 파일 오픈
		String cmprFile11 = cmprFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String cmprFile22 = cmprFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file1 = new File(cmprFile11);
		File file2 = new File(cmprFile22);

		// 파일이며, 존재하면 파일 수정일자 비교
		if (file1.exists() && file2.exists()
				&& file1.isFile() && file2.isFile()) {

			// 파일1 수정일자
			long date1  = file1.lastModified();
			java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("yyyyMMdd",java.util.Locale.KOREA);
			String lastUpdtDate1 = dateFormat1.format(new java.util.Date(date1));

			// 파일2 수정일자
			long date2  = file2.lastModified();
			java.text.SimpleDateFormat dateFormat2 = new java.text.SimpleDateFormat("yyyyMMdd",java.util.Locale.KOREA);
			String lastUpdtDate2 = dateFormat2.format(new java.util.Date(date2));

			// 수정일자 비교
			if (lastUpdtDate1.equals(lastUpdtDate2)) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * <p>두 개의 파일의 내용이 동일한지 여부를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.compareFileContents({"C:/test/aaa.txt", "C:/test/bbb.txt") = true
	 * </pre>
	 * 
	 * @param compFile1  파일경로 문자열
	 * @param compFile2  파일경로 문자열
	 * @return 두 개의 파일의 내용이 동일한지 여부
	 * @exception Exception
	 */
	public static boolean compareFileContents(String compFile1, String compFile2) throws Exception {

		// 파일 동일 여부
		boolean result = false;

		// 파일 오픈
		String cmprFile11 = compFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		String cmprFile22 = compFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file1 = new File(cmprFile11);
		File file2 = new File(cmprFile22);

		BufferedReader br1 = null;
		try {
			// 파일이며, 존재하면 파일 내용 비교
			if (file1.exists() && file2.exists()
					&& file1.isFile() && file2.isFile()) {

				List<String> cmprText1 = new ArrayList<String>();
				List<String> cmprText2 = new ArrayList<String>();

				// 파일1 텍스트 내용
				br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
				String line1 = "";
				while ((line1 = br1.readLine()) != null) {
					if (line1.length() < MAX_STR_LEN) cmprText1.add(line1);
				}

				// 파일2 텍스트 내용
				@SuppressWarnings("resource")
				BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
				String line2 = "";
				while ((line2 = br2.readLine()) != null) {
					if (line2.length() <= MAX_STR_LEN) cmprText2.add(line2);
				}

				// 내용 비교
				boolean isWrong = false;
				for (int i = 0; i < cmprText1.size(); i++) {
					if(!isWrong){ //   if(isWrong != true){
						String text1 = (String)cmprText1.get(i);
						String text2 = (String)cmprText2.get(i);

						if (!text1.equals(text2)) {
							isWrong = true;
						}
					}
				}

				if (!isWrong) {
					result = true;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (br1 != null) br1.close();
		}

		return result;
	}

	/**
	 * <p>파일을 복사하여 타켓 파일에 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyFile({"C:/test/aaa.txt", "C:/test/bbb.txt") = true
	 * </pre>
	 * 
	 * @param sourceFile 원본파일
	 * @param targetFile 타겟파일
	 * @return 복사성공 여부
	 * @exception Exception
	 */
	public static boolean copyFile(String sourceFile, String targetFile) throws Exception {

		// 복사여부
		boolean result = false;        

		// 원본 파일
		String src = sourceFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(src);

		// 타켓 파일
		String tar = targetFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);

		try {
			// 원본 파일이 존재하는지 확인한다.
			if (srcFile.exists()) {                

				// 복사될 target 파일 생성
				tar = createFile(tar);
				//log.debug("tar:"+tar);
				File tarFile = new File(tar);
				//log.debug("tarFile:"+tarFile.getAbsolutePath());
				// 복사
				result = copyFile(srcFile, tarFile);

			}//    else {
			// 원본 파일이 존재하지 않습니다.
			//log.debug("+++ 원본 파일이 존재하지 않습니다.");
			// }

		} catch (IOException ex){
			ex.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>파일들을 타켓 디렉토리에 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyFiles({"C:/test/aaa.txt", "C:/test/bbb.txt", "C:/test2") = true
	 * </pre>
	 * 
	 * @param sourceFiles 복사할 파일경로 배열
	 * @param targetDir 타겟 디렉토리
	 * @return 성공여부
	 * @exception Exception
	 */
	public static boolean copyFiles(String[] sourceFiles, String targetDir) throws Exception {

		// 복사여부
		boolean result = true;        

		// 복사 이전에 복사할 파일들의 경로가 올바른지 확인한다.
		for (int i = 0; i < sourceFiles.length; i++) {
			String src = sourceFiles[i].replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
			File chkFile = new File(src);
			if (!chkFile.exists()) {
				//log.debug("+++ 원본 파일이 존재하지 않습니다.");
				return result;
			}
		}

		String tar = targetDir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);

		// 복사를 시작한다.
		for (int j = 0; j < sourceFiles.length; j++) {

			if(result){ //result != false

				// 타겟파일이름 명명
				File chkFile = new File(sourceFiles[j]);
				String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();

				try
				{   
					// 복사될 target 파일 생성
					tarTemp = createFile(tarTemp);
					File tarFile = new File(tarTemp);

					// 복사
					result = copyFile(chkFile, tarFile);

				} catch (IOException ex){
					ex.printStackTrace();
				}

			}

		} // end for

		return result;
	}

	/**
	 * <p>디렉토리 내에 있는 파일중 특정 확장자인 파일들을 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyFilesByExt("C:/test", ".txt", "C:/test2") = true
	 * </pre>
	 * 
	 * @param sourceDir 디렉토리 경로
	 * @param ext 확장자(.txt 형태 입력)
	 * @param targetDir 타겟 디렉토리
	 * @return 성공여부
	 * @exception Exception
	 */
	public static boolean copyFilesByExt(String sourceDir, String ext, String targetDir) throws Exception {

		// 복사여부
		boolean result = true;        

		// 원본 파일
		String src = sourceDir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(src);

		// 원본 디렉토리가 존재하는지 확인한다.
		if (srcFile.exists() && srcFile.isDirectory()) {

			String tar = targetDir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);

			// 원본 디렉토리 안에서 확장자가 일치하는 파일목록을 가져온다.
			File [] fileArray = srcFile.listFiles();
			List<String> list = getPathListByExt(fileArray, ext);

			// 복사를 시작한다.
			for (int i = 0; i < list.size(); i++) {
				if(result){ //f(result != false){
					// 원본파일 절대경로
					String abspath = (String)list.get(i);

					// 타겟파일이름 명명
					File chkFile = new File(abspath);
					String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();

					try
					{   
						// 복사될 target 파일 생성
						tarTemp = createFile(tarTemp);
						File tarFile = new File(tarTemp);

						// 복사
						result = copyFile(chkFile, tarFile);

					} catch (IOException ex){
						ex.printStackTrace();
					}
				}

			} // end for

		}// else {
		// 원본 파일이 존재하지 않습니다.
		//log.debug("+++ 원본 디렉토리가 존재하지 않습니다.");
		//}

		return result;
	}

	/**
	 * <p>디렉토리 내에 있는 파일중 수정기간내에 속한 파일들을 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyFilesByUpdateDate("C:/test", "20110101", "20110201", "C:/test2") = true
	 * </pre>
	 * 
	 * @param sourceDir 디렉토리 경로
	 * @param fromDate  수정시작일자(yyyyMMdd)
	 * @param toDate    수정종료일자(yyyyMMdd)
	 * @param targetDir 타겟 디렉토리
	 * @return 성공여부
	 * @exception Exception
	 */
	public static boolean copyFilesByUpdateDate(String sourceDir, String fromDate, String toDate, String targetDir) throws Exception {

		// 복사여부
		boolean result = true;        

		// 원본 파일
		String src = sourceDir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(src);

		// 원본 디렉토리가 존재하는지 확인한다.
		if (srcFile.exists() && srcFile.isDirectory()) {

			String tar = targetDir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);

			// 원본 디렉토리 안에서 수정기간내 존재하는 파일목록을 가져온다.
			File [] fileArray = srcFile.listFiles();
			List<String> list = getPathListByUpdateDate(fileArray, fromDate, toDate);

			// 복사를 시작한다.
			for (int i = 0; i < list.size() ; i++) {

				if(result){ //f(result != false){

					// 원본파일 절대경로
					String abspath = (String)list.get(i);

					// 타겟파일이름 명명
					File chkFile = new File(abspath);
					String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();

					try
					{   
						// 복사될 target 파일 생성
						tarTemp = createFile(tarTemp);
						File tarFile = new File(tarTemp);

						// 복사
						result = copyFile(chkFile, tarFile);

					} catch (IOException ex){
						ex.printStackTrace();
					}

				}

			} // end for

		} //else {
		// 원본 파일이 존재하지 않습니다.
		//log.debug("+++ 원본 디렉토리가 존재하지 않습니다.");
		//}

		return result;
	}

	/**
	 * <p>디렉토리 내에 있는 파일중 사이즈내에 속한 파일들을 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyFilesBySize("C:/test", 1, 10, "C:/test2") = true
	 * </pre>
	 * 
	 * @param sourceDir 디렉토리 경로
	 * @param fromSize  최소사이즈(KB)
	 * @param toSize    최대사이즈(KB)
	 * @param targetDir 타겟 디렉토리
	 * @return 성공여부
	 * @exception Exception
	 */
	public static boolean copyFilesBySize(String sourceDir, long fromSize, long toSize, String targetDir) throws Exception {

		// 복사여부
		boolean result = true;        

		// 원본 파일
		String src = sourceDir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(src);

		// 원본 디렉토리가 존재하는지 확인한다.
		if (srcFile.exists() && srcFile.isDirectory()) {

			String tar = targetDir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);

			// 원본 디렉토리 안에서 사이즈내 존재하는 파일목록을 가져온다.
			File [] fileArray = srcFile.listFiles();
			List<String> list = getPathListBySize(fileArray, fromSize, toSize);

			// 복사를 시작한다.
			for (int i = 0; i < list.size(); i++) {

				if(result){ //result != false
					// 원본파일 절대경로
					String abspath = (String)list.get(i);

					// 타겟파일이름 명명
					File chkFile = new File(abspath);
					String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();

					try
					{   
						// 복사될 target 파일 생성
						tarTemp = createFile(tarTemp);
						File tarFile = new File(tarTemp);

						// 복사
						result = copyFile(chkFile, tarFile);
						if(result){break;}

					} catch (IOException ex){
						ex.printStackTrace();
					}

				}

			} // end for

		}// else {
		// 원본 파일이 존재하지 않습니다.
		//log.debug("+++ 원본 디렉토리가 존재하지 않습니다.");
		//}

		return result;
	}

	/**
	 * <p>파일을 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyFile("C:/test/aaa","C:/test/aaa2") = true
	 * </pre>
	 * 
	 * @param srcFile 복사할 파일 객체
	 * @param tarFile 복사하여 새로 생성될 파일 객체
	 * @return 성공여부
	 * @exception Exception
	 */
	public static boolean copyFile(File srcFile, File tarFile) throws Exception {

		// 결과
		boolean result = false;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 복사
			fis = new FileInputStream(srcFile); 

			//예외상황에 따른 처리 추가함. -> 만약 tarFile 이 디렉토리명인 경우 디렉토리 밑으로 새로 파일을 생성해서 복사한다.. like DOS
			File tarFile1 = tarFile;
			if(tarFile1.isDirectory()){
				tarFile1 = new File(tarFile1.getAbsolutePath()+"/"+srcFile.getName());
			}
			fos = new FileOutputStream(tarFile1); 
			byte [] buffer = new byte[(int)BUFFER_SIZE];
			int i = 0;
			if (fis != null && fos != null) {
				while ((i = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, i);
				}
			}

			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fis != null) fis.close();
			if (fos != null) fos.close();
		}


		return result;
	}

	/**
	 * <p>디렉토리를 삭제한다. 두 개의 수정일자 사이에 수정된 디렉토리인지를 확인한 후 삭제한다.</p>
	 * 
	 * <pre>
	 * FileUtil.deleteDirectory("C:/test/aaa","20111101","20111202") = "C:/test/aaa"
	 * </pre>
	 * 
	 * @param dirPath  디렉토리(파일) 경로
	 * @param fromDate 디렉토리의 수정일자(~부터) yyyyMMdd
	 * @param toDate   디렉토리의 수정일자(~까지) yyyyMMdd
	 * @return 성공시 삭제된 디렉토리 경로, 실패시 ""
	 * @exception Exception
	 */
	public  static String deleteDirectory(String dirPath, String fromDate, String toDate){

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (dirPath==null || dirPath.equals("")||fromDate==null || fromDate.equals("")||toDate==null || toDate.equals("")){
			return "";
		}

		// 찾은 결과를 전달할 ArrayList
		String result = "";
		File file = new File(dirPath);

		// 추가된 삭제조건 옵션에 합당한지 확인
		boolean isInCondition = false;
		String lastModifyedDate = getModifiedDate(file);
		//log.debug("lastModifyedDate:"+lastModifyedDate);
		try{
			if(Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate) 
					&& Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate) ){
				isInCondition = true;
			}

			// 삭제조건에 부합되면 디렉토리 삭제조치함
			if(file.isDirectory() && isInCondition){      
				result = deleteDirectory(dirPath);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>파일(디렉토리)의 부모 디렉토리 절대경로를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.getParentPath("c:/test/test1") = "c:/test"
	 * </pre>
	 * 
	 * @param path 디렉토리(파일) 경로
	 * @return 부모의 디렉토리 경로
	 * @exception Exception
	 */
	public static String getParentPath(String path) throws Exception {

		String drctryName = "";
		String src = path.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);

		try
		{   
			File srcFile = new File(src);
			if (srcFile.exists()) {
				drctryName = srcFile.getParent();
			}// else {
			//log.debug("+++ 지정된 위치가 올바르지 않습니다.");
			//}

		} catch (Exception ex){
			ex.printStackTrace();
		} 

		return drctryName;
	}

	/**
	 * <p>디렉토리를 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyDirectory("c:/test", "c:/test2") = true
	 * </pre>
	 * 
	 * @param sourceDirPath 복사 대상 디렉토리 경로
	 * @param targetDirPath 복사 타겟 디렉토리 경로
	 * @return 복사 성공여부
	 * @exception Exception
	 */
	public static boolean copyDirectory(String sourceDirPath, String targetDirPath) throws Exception {

		// 인자값 유효하지 않은 경우 공백 리턴
		if (sourceDirPath==null || sourceDirPath.equals("") || targetDirPath==null || targetDirPath.equals("")){
			return false;
		}
		boolean result = false;
		File f = null;
		try
		{   
			f = new File (sourceDirPath);
			// 원본이 유효해야 진행한다.
			if(f.exists() && f.isDirectory()){

				//타겟으로 설정한 경로가 유효한지 확인(중간경로에 파일명 이 포함되어있으면 유효하지 못하므로 진행안함.
				String targetDirPath1 = createDirectory(targetDirPath);
				if(targetDirPath1.equals("")){
					result = false;
				}else{
					File targetDir = new File(targetDirPath1);
					targetDir.mkdirs();
					// 디렉토리에 속한 파일들을 복사한다.
					String [] originalFileList = f.list();
					if(originalFileList.length>0){
						for(int i = 0; i<originalFileList.length ; i++){
							File subF = new File (sourceDirPath+FILE_SEPARATOR+originalFileList[i]);
							if(subF.isFile()){
								//하위목록이 파일이면 파일복사실행 -> 실패 발생하는 경우 복사를 중단한다.
								result = copyFile(sourceDirPath+FILE_SEPARATOR+originalFileList[i],
										targetDir.getAbsolutePath()+FILE_SEPARATOR+originalFileList[i]);
							}else{
								//하위목록이 디렉토리이면 복사를 재귀적으로 호출한다.
								result = copyDirectory(sourceDirPath+"/"+originalFileList[i], targetDirPath1+"/"+originalFileList[i]);
							}
						}
					}else{
						result = true;
					}
				}
			}else{
				// 원본자체가 유효하지 않은 경우는 false 리턴하고 종료
				result = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리를 복사한다. 수정일자의 조건에 해당하는 파일만 복사한다.</p>
	 * 
	 * <pre>
	 * FileUtil.copyDirectory("c:/test", "c:/test2", "20100101", "20100301" ) = true
	 * </pre>
	 * 
	 * @param sourceDirPath 복사 대상 디렉토리 경로
	 * @param targetDirPath 복사 타겟 디렉토리 경로
	 * @param fromDate 수정일자(~부터)
	 * @param toDate   수정일자(~까지)
	 * @return 복사 성공여부
	 * @exception Exception
	 */
	public static boolean copyDirectory(String sourceDirPath, String targetDirPath, String fromDate, String toDate) throws Exception {

		// 인자값 유효하지 않은 경우 공백 리턴
		if (sourceDirPath==null || sourceDirPath.equals("") || targetDirPath==null || targetDirPath.equals("")
				|| fromDate==null || fromDate.equals("")|| toDate==null || toDate.equals("")){
			return false;
		}
		boolean result = false;
		File f = null;
		try
		{              
			f = new File (sourceDirPath);            
			boolean isInCondition = false;
			String lastModifyedDate = getModifiedDate(f);
			if(Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate) 
					&& Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate) ){
				isInCondition = true;
			}

			// 원본이 유효하고 조건에 부합되야 진행한다.
			if(f.exists() && f.isDirectory() && isInCondition){

				//타겟으로 설정한 경로가 유효한지 확인(중간경로에 파일명 이 포함되어있으면 유효하지 못하므로 진행안함.
				String targetDirPath1 = createDirectory(targetDirPath);
				if(targetDirPath1.equals("")){
					result = false;
				}else{
					File targetDir = new File(targetDirPath1);
					targetDir.mkdirs();
					// 디렉토리에 속한 파일들을 복사한다.
					String [] originalFileList = f.list();
					if(originalFileList.length>0){
						for(int i = 0; i<originalFileList.length ; i++){
							File subF = new File (sourceDirPath+FILE_SEPARATOR+originalFileList[i]);
							if(subF.isFile()){
								//하위목록이 파일이면 파일복사실행 -> 실패 발생하는 경우 복사를 중단한다.
								result = copyFile(sourceDirPath+FILE_SEPARATOR+originalFileList[i],
										targetDir.getAbsolutePath()+FILE_SEPARATOR+originalFileList[i]);
							}else{
								//하위목록이 디렉토리이면 복사를 재귀적으로 호출한다.
								//하위목록에 해당하는 폴더에 대해서는 생성일자 검사를 하지 않는다.(현재 폴더가 복사대상이면 현재폴더의 하위는 제외없이 복사함)
								result = copyDirectory(sourceDirPath+"/"+originalFileList[i], targetDirPath1+"/"+originalFileList[i]);
							}
						}
					}else{
						result=true;
					}
				}

			}else{
				// 원본자체가 유효하지 않은 경우는 false 리턴하고 종료
				result = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>디렉토리의 사이즈를 반환한다.</p>
	 * 
	 * <pre>
	 * FileUtil.getDirectorySize("c:/test") = 12
	 * </pre>
	 * 
	 * @param dirPath 디렉토리 경로
	 * @return 디렉토리 사이즈(KB)
	 * @exception Exception
	 */
	public static long getDirectorySize(String dirPath) throws Exception {

		File f =  new File (dirPath);
		if(!f.exists()){
			return 0;
		}
		if(f.isFile()) {
			return f.length();
		}

		File[] list = f.listFiles();
		long size = 0;
		long fileSize = 0;

		for (int i = 0; i < list.length; i++) {

			if (list[i].isDirectory()) {
				// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
				fileSize = getDirectorySize(list[i].getAbsolutePath());
			} else {
				// 파일의 사이즈 조회
				fileSize = list[i].length();
			}
			size = size + fileSize;
		}
		return size;
	}
	
	/**
	 * <p>BLOB 형식의 이미지 데이터를 byte 문자열 형식으로 변환한다. </p>
	 * 
	 * <pre>
	 * FileUtil.convertImageData(BLOB imagefile)
	 * </pre>
	 * 
	 * @param imageFile BLOB 형태의 이미지 데이터
	 * @return byte 배열
	 * @exception Exception
	 
	public static byte[] convertImageData(Object imageFile){
		Blob blobPHOT_FILE = (Blob) imageFile;
    	try {
			if(blobPHOT_FILE!=null && blobPHOT_FILE.isEmptyLob()==false){
				byte [] buf = new byte[blobPHOT_FILE.getBufferSize()];
				BufferedInputStream bis = new BufferedInputStream(blobPHOT_FILE.getBinaryStream(), blobPHOT_FILE.getBufferSize());
				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				//바이트 복사
				int count;
				while((count = bis.read(buf, 0, blobPHOT_FILE.getBufferSize())) != -1)
				{
					baos.write(buf, 0, count);
				}
				bis.close();
				
				return baos.toByteArray();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/	
}
