package com.algonquin.pdfcombinator.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.algonquin.pdfcombinator.services.PDFUtilities;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	
	private List<PDDocument> uploadedPdfs;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	PDFUtilities PDFutil = new PDFUtilities();

	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("In SelectServlet doGet()");
		HttpSession session = request.getSession();
		if (session.getAttribute("pdfs") != null) {
			uploadedPdfs = (List<PDDocument>) session.getAttribute("pdfs");
		} else {
			uploadedPdfs = new ArrayList<PDDocument>();
			request.getRequestDispatcher("/html/upload.jsp").forward(request, response);
		}
		
		
		request.getRequestDispatcher("/html/select.jsp").forward(request, response);
		
	}
	
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In SelectServlet doPost()");
		
		PDDocument resultPDF = new PDDocument();
		
		session = request.getSession();
		
		uploadedPdfs = (ArrayList<PDDocument>)session.getAttribute("pdfs");
		
		System.out.println("Size of list of PDFs: " + uploadedPdfs.size());
			
		String newFileName = request.getParameter("file-name");
		
		if (newFileName == null || newFileName.equals("")) {
			newFileName = "result";
		}
		
		for (int i=1; i<=uploadedPdfs.size()*2 && i < 7; i++) {
			String fileSelect = "select-file-" + i;
			String pageSelect = "select-page-" + i;
			if (request.getParameter(fileSelect) != null && !request.getParameter(fileSelect).equals("")) {
				PDDocument pdf = getSelectedPdf(uploadedPdfs, request.getParameter(fileSelect));
				PDFutil.combinePdfPages(pdf, request.getParameter(pageSelect), resultPDF);
			} else {
				System.out.println("No file selected for " + fileSelect);
			}
			
		}
		
		System.out.println("Result PDF pages: " + resultPDF.getNumberOfPages());
		
		File resultFile = File.createTempFile(newFileName + "-", ".pdf");
		String resultPath = resultFile.getAbsolutePath();
		System.out.println("File path: " + resultPath);
		
		resultPDF.save(resultFile);	
		resultPDF.close();
	
		File tempFolder = (File) getServletContext().getAttribute(ServletContext.TEMPDIR);
		File tempFile = new File(tempFolder, newFileName + ".pdf");
		
		Files.copy(Paths.get(resultPath), Paths.get(tempFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
							
		session.setAttribute("resultFile", tempFile);
		session.setAttribute("filePath", tempFile.getAbsolutePath());
		
		System.out.println("File path: " + tempFile.getAbsolutePath());
		
		request.getRequestDispatcher("/html/download.jsp").forward(request, response);
	}
	
	private PDDocument getSelectedPdf(List<PDDocument> pdfs, String fileId) {
		
		System.out.println("In getSelectedPdf() in SelectServlet");
		System.out.println("File ID: " + fileId);
		
		for (PDDocument pdf : pdfs) {
			System.out.println("PDF ID: " + pdf.getDocumentId());
			if (pdf.getDocumentId().toString().equals(fileId)) {
				return pdf;
			}
		}
		return null;
		
	}



}