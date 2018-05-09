package com.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cm.entities.Book;

import com.service.IBookService;
import com.serviceimpl.BookServiceImpl;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBookService bookService = new BookServiceImpl();
	private final Integer recordsPerPage = 5;
	private Integer pageNum = 1;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("came here");
		System.out.println(request.getParameter("action"));
		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("pageNum")==null)
			httpSession.setAttribute("pageNum", pageNum);
		else{
			if("next".equals(request.getParameter("action"))) {
				pageNum = (Integer)httpSession.getAttribute("pageNum");
				pageNum++;
			}
			else {
				pageNum = (Integer)httpSession.getAttribute("pageNum");
				pageNum--;
				if(pageNum<=0)
					pageNum=1;
			}
		}
		try {
			httpSession.setAttribute("bookList", bookService.findAll(pageNum,recordsPerPage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(((List)(httpSession.getAttribute("bookList"))).size());
		request.getRequestDispatcher("DisplayBook.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post here");
		List<Book> bookList = null;
		if(request.getParameter("action").equals("delete")){
			System.out.println("came inside if ========= "+request.getParameter("action"));
			String[] bookIds = request.getParameterValues("bookId");
			System.out.println(bookIds.length);
			try {
				for(int i=0;i<bookIds.length;i++) {
					Book book = bookService.getBook(Integer.parseInt(bookIds[i]));
					bookService.deleteBook(book.getBookId());
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			response.sendRedirect("Book");
		}else if(request.getParameter("action").equals("edit")) {
			System.out.println("came inside if ========= "+request.getParameter("action"));
			String bookId = request.getParameter("bookid");
			System.out.println(bookId);
			try {
				Book book = bookService.getBook(Integer.parseInt(bookId));
				System.out.println(book.getBookName()+"\t"+book.getBookId()+"\t"+book.getBookPrice()+"\t"+book.getIsbn());
				request.setAttribute("bookName", book.getBookName());
				request.setAttribute("bookPrice", book.getBookPrice());
				request.setAttribute("isbn", book.getIsbn());
				request.setAttribute("bookId", book.getBookId());
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			request.getRequestDispatcher("editBook.jsp").forward(request, response);
			
		}else if("editBookDetails".equals(request.getParameter("action"))) {
			System.out.println("came inside else if ====== "+request.getParameter("action"));
			System.out.println(request.getParameter("bookId"));
		}else if("exportData".equals(request.getParameter("action"))) {
			System.out.println("came inside else if exportData ====== "+request.getParameter("action"));
			String fileName = bookService.exportData();
			downloadHelper(fileName,request,response);
		}else if(request.getParameter("action").equals("add")) {
			System.out.println("add book details");
			System.out.println("came inside if ========= "+request.getParameter("action"));
			System.out.println(request.getParameter("isbn")+"\t"+
			request.getParameter("bookName")+"\t"+
			request.getParameter("bookPrice"));
			Book book = new Book();
			book.setBookName(request.getParameter("bookName"));
			book.setIsbn(new Integer(request.getParameter("isbn")));
			book.setBookPrice(new Double(request.getParameter("bookPrice")));
			bookService.save(book);
			response.sendRedirect("/WebApp/Book");
			
			
		}
		
	}
	
	private void downloadHelper(String fileName,HttpServletRequest request, HttpServletResponse response) {
		try {
			BufferedOutputStream output=null;
			Map<String,String> contentType=new HashMap<String,String>();
			contentType.put("doc", "application/msword");
			contentType.put("xml", "text/xml");
			contentType.put("txt", "text/plain");
			contentType.put("xls", "application/vnd.ms-excel");
			contentType.put("pdf","application/pdf");
			byte[] buf=new byte[2048];
			ServletOutputStream sos=response.getOutputStream();
			String contenttype=fileName.substring(fileName.lastIndexOf('.')+1);
			System.out.println(contenttype);
			if(contenttype==null) 
				contenttype="application/x-unknown";
			response.setContentType(contenttype);
			response.setHeader("content-disposition", "attachment;filename=\""+fileName+"\"");
			InputStream input=new BufferedInputStream(new FileInputStream(fileName));
			output=new BufferedOutputStream(sos);
			int strmsize=input.available();
			System.out.println("available is "+strmsize);
			System.out.println("entering into while loop");
			while((strmsize=input.read(buf,0,buf.length))>-1) {
				//is.read(buf, 0, strmsize);
				output.write(buf,0,strmsize);
			}
			output.flush();
			output.close();
			input.close();
			sos.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
