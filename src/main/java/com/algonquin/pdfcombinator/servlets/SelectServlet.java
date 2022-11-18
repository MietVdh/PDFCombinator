package com.algonquin.pdfcombinator.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

import com.algonquin.pdfcombinator.services.PDFUtilities;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	
//	private List<File> uploadedFiles = new ArrayList<>();
	private List<PDDocument> uploadedPdfs = new ArrayList<>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;

	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("In SelectServlet doGet()");
		// Get "files" attribute from forwarded request
		session = request.getSession();
		System.out.println("request.getAttribute(pdfs): " + session.getAttribute("pdfs").toString());

		uploadedPdfs = (List<PDDocument>)session.getAttribute("pdfs");
		
		
		request.getRequestDispatcher("/html/select.jsp").forward(request, response);
		
	}
	
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In SelectServlet doPost()");
		
		PDDocument resultPDF;
		List<PDPage> resultPages = new ArrayList<>();
		
		session = request.getSession();
		
		uploadedPdfs = (List<PDDocument>)session.getAttribute("pdfs");
		System.out.println("session.getAttribute(pdfs): " + session.getAttribute("pdfs").toString());
		
		System.out.println("Size of list of PDFs: " + uploadedPdfs.size());
		
		// Retrieve correct pdf
		
		
		String newFileName = request.getParameter("file-name");
		
		if (newFileName == null || newFileName.equals("")) {
			newFileName = "result";
		}
		
		
		for (int i=1; i<=uploadedPdfs.size()*2; i++) {
			String fileSelect = "select-file-" + i;
			String pageSelect = "select-page-" + i;
			if (request.getParameter(fileSelect) != null && !request.getParameter(fileSelect).equals("")) {
				addSelectedPdfPages(uploadedPdfs, resultPages, request.getParameter(fileSelect), request.getParameter(pageSelect));
				System.out.println(fileSelect + ": " + request.getParameter(fileSelect));
				System.out.println(pageSelect + ": " + request.getParameter(pageSelect));
				System.out.println("number of resultpages: " + resultPages.size());
			} else {
				System.out.println("No file selected for " + fileSelect);
			}
			
		}
		
		resultPDF = PDFUtilities.createCombinedPDF(resultPages);
		System.out.println("Result PDF pages: " + resultPDF.getNumberOfPages());
		
		File resultFile = File.createTempFile(newFileName, ".pdf");


		resultPDF.save(resultFile);
		
		session.setAttribute("resultFile", resultFile);
		
		
		request.getRequestDispatcher("/html/download.jsp").forward(request, response);
	}
	
	
	private void addSelectedPdfPages(List<PDDocument> pdfs, List<PDPage> resultPages, String fileId, String pageNumbers) {
		
		
		System.out.println("In addSelectedPdfPages()");
		System.out.println("File ID: " + fileId);
		for (PDDocument pdf : pdfs) {
			System.out.println("PDF ID: " + pdf.getDocumentId());		
			if (pdf.getDocumentId().toString().equals(fileId)) {
				PDFUtilities.addPdfPages(resultPages, pdf, pageNumbers);
				break;
			}
			
		}	
	}

}