package com.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import cm.entities.Book;

import com.dao.IBookDAO;
import com.daoimpl.BookDAOImpl;
import com.service.IBookService;

public class BookServiceImpl implements IBookService {
	
	private IBookDAO bookDAO = new BookDAOImpl();
	
	@Override
	public List<Book> findAll(Integer pageNum,Integer recordsPerPage) throws Exception {
		// TODO Auto-generated method stub
		return bookDAO.findAll(pageNum,recordsPerPage);
	}

	@Override
	public Book getBook(Integer bookId)  throws Exception  {
		// TODO Auto-generated method stub
		return bookDAO.getBook(bookId);
	}

	@Override
	public boolean deleteBook(Integer bookId) throws Exception {
		// TODO Auto-generated method stub
		return bookDAO.deleteBook(bookId);
	}

	@Override
	public boolean save(Book book) {
		// TODO Auto-generated method stub
		return bookDAO.save(book);
		
	}

	@Override
	public String exportData() {
		// TODO Auto-generated method stub
		List<Book> bookList = null;
		int rowNum = 0;
		HSSFRow hssfRow = null;
		HSSFCell hssfCell1 = null;
		HSSFCell hssfCell2 = null;
		HSSFCell hssfCell3 = null;
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		hssfRow = hssfSheet.createRow(0);
		hssfCell1 = hssfRow.createCell(0);
		hssfCell1.setCellValue("ISBN");
		hssfCell2 = hssfRow.createCell(1);
		hssfCell2.setCellValue("BookName");
		hssfCell3 = hssfRow.createCell(2);
		hssfCell3.setCellValue("BookPrice");
		HSSFFont font= hssfWorkbook.createFont();
	    font.setFontHeightInPoints((short)10);
	    font.setFontName("Arial");
	    font.setColor(IndexedColors.WHITE.getIndex());
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    font.setItalic(false);
		HSSFCellStyle style = hssfWorkbook.createCellStyle();
	    style.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    hssfRow.setRowStyle(style);
		try {
			bookList = bookDAO.findAll();
			if(bookList!=null) 
				rowNum = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Book book : bookList) {
			hssfRow = hssfSheet.createRow(rowNum);
			hssfCell1 = hssfRow.createCell(0);
			hssfCell1.setCellValue(book.getIsbn().toString());
			hssfCell2 = hssfRow.createCell(1);
			hssfCell2.setCellValue(book.getBookName());
			hssfCell3 = hssfRow.createCell(2);
			hssfCell3.setCellValue(book.getBookPrice().toString());
			rowNum++;
		}
		String fileName = "E:\\exportedData.xls";
		File file = new File(fileName);
		try {
			file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			hssfWorkbook.write(os);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return fileName;
	}

}
