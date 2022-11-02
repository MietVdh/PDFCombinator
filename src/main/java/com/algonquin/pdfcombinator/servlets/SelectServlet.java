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
//		uploadedFiles = (List<File>) request.getAttribute("files");
		session = request.getSession();
		System.out.println("request.getAttribute(pdfs): " + session.getAttribute("pdfs").toString());
//		uploadedPdfs = (List<PDDocument>)request.getAttribute("pdfs");
		uploadedPdfs = (List<PDDocument>)session.getAttribute("pdfs");
		
		
		/*
		// Add files to list of pdfs
		for (File file : uploadedFiles) {
			System.out.println("SelectServlet - File: " + file.getName());
			PDDocument pdf = Loader.loadPDF(file);
			pdfFiles.add(pdf);
			// Set id 
			pdf.setDocumentId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE); 
			// Set title of document to name of file
			PDDocumentInformation info = new PDDocumentInformation();
			info.setTitle(file.getName());
			pdf.setDocumentInformation(info);
			// Source: https://stackoverflow.com/questions/15184820/how-to-generate-unique-positive-long-using-uuid/41613362#41613362
			System.out.println("PDF id: " + pdf.getDocumentId());
		}
		
		*/
//		request.setAttribute("pdfs", uploadedPdfs);
		request.getRequestDispatcher("/html/select.jsp").forward(request, response);
		
//		String page = getHTMLString(request.getServletContext().getRealPath("/html/select.html"), uploadedPdfs);
//		response.getWriter().write(page);
	}
	
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In SelectServlet doPost()");
		
		
//		String selectedFile;
//		String pageNums;
//		PDDocument selectedPDF;
		PDDocument resultPDF;
		List<PDPage> resultPages = new ArrayList<>();
		
		session = request.getSession();
		
		uploadedPdfs = (List<PDDocument>)session.getAttribute("pdfs");
		System.out.println("session.getAttribute(pdfs): " + session.getAttribute("pdfs").toString());
		
		System.out.println("Size of list of PDFs: " + uploadedPdfs.size());
		
		
//		selectedFile = request.getParameter("file-select-1");
//		pageNums = request.getParameter("page-select-1");
		
		// Retrieve correct pdf
		
		
		String newFileName = request.getParameter("file-name");
		
		if (newFileName == null || newFileName.equals("")) {
			newFileName = "result";
		}
		
		
//		if (newFileName == null || newFileName.equals("")) {
//			newFileName = "resultPdf.pdf";
//		} else if (!newFileName.endsWith(".pdf")) {
//			newFileName = newFileName + ".pdf";
//		}
		
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
		
		/*
		addSelectedPdfPages(uploadedPdfs, resultPages, request.getParameter("select-file-1"), request.getParameter("select-page-1"));
		System.out.println("select-file-1: " + request.getParameter("select-file-1"));
		System.out.println("select-page-1: " + request.getParameter("select-page-1"));
		System.out.println("number of resultpages: " + resultPages.size());
		addSelectedPdfPages(uploadedPdfs, resultPages, request.getParameter("select-file-2"), request.getParameter("select-page-2"));
		System.out.println("select-file-2: " + request.getParameter("select-file-2"));
		System.out.println("select-page-2: " + request.getParameter("select-page-2"));
		System.out.println("number of resultpages: " + resultPages.size());
		addSelectedPdfPages(uploadedPdfs, resultPages, request.getParameter("select-file-3"), request.getParameter("select-page-3"));
		System.out.println("select-file-3: " + request.getParameter("select-file-3"));
		System.out.println("select-page-3: " + request.getParameter("select-page-3"));
		System.out.println("number of resultpages: " + resultPages.size());
		
		*/
		
//		for (PDDocument pdf : uploadedPdfs) {
//			if (pdf.getDocumentId().toString() == selectedFile) {
//				selectedPDF = pdf;
//				PDFUtilities.addPdfPages(resultPages, selectedPDF, pageNums);
//				break;
//			}
//			
//		}

		resultPDF = PDFUtilities.createCombinedPDF(resultPages);
		System.out.println("Result PDF pages: " + resultPDF.getNumberOfPages());
		
		
		/* 
		Path path = Files.createDirectories(Paths.get("/tmp/pdfcombinator/downloads"));
		Files.createDirectories(path);
		File downloadsFolder = new File(path.toString());
		
		File resultFile = new File(downloadsFolder, newFileName);
		 try {
		    	if (!resultFile.exists()) {
		    		Files.createFile(resultFile.toPath());
		    		System.out.println("File created");
		    	} 		    
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		
		*/
		
		File resultFile = File.createTempFile(newFileName, ".pdf");
//		File resultFile = new File()
		
		
		/*
		// Source : https://stackoverflow.com/questions/11772120/sending-outputstream-to-browser-and-let-browser-save-it/11772700#11772700
		response.setHeader("Content-Disposition", "attachment; filename=" + newFileName + ".pdf");
		response.setContentType("application/pdf");
		OutputStream outStream = response.getOutputStream();
		
		
		byte[] buf = new byte[4096];
		int len = -1;

		//Write the file contents to the servlet response
		//Using a buffer of 4kb (configurable). This can be
		//optimized based on web server and app server
		//properties
		while ((len = inStream.read(buf)) != -1) {
		    outStream.write(buf, 0, len);
		}

		
		
		*/
		resultPDF.save(resultFile);
//		resultPDF.save(outStream);
//		session.setAttribute("resultStream", outStream);
//		request.setAttribute("resultFile", resultFile);
		
		session.setAttribute("resultFile", resultFile);
		
//		outStream.flush();
//		outStream.close();
		
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
	
	/*
	
	
	public String getHTMLString(String filePath, List<PDDocument> pdfs) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(filePath));
    	String line = "";
    	StringBuffer buffer = new StringBuffer();
    	while ((line=reader.readLine())!= null) {
    		buffer.append(line);
    	}
    	
    	reader.close();
    	String page = buffer.toString();
    	
    	
    	String formString = "";
    	int fileNum = 0;
    	
    	String fileOptions = "";
    	
    	for (PDDocument pdf : pdfs) {
    		fileOptions += getFileOptionsHTMLString(pdf);
    	}
    	
    	System.out.println("FileOptionsString: " + fileOptions);
    	for (PDDocument pdf : pdfs) {
    		fileNum += 1;
    		formString += getPdfSelectHTMLString(pdf, fileNum, fileOptions);
    		System.out.println("FormString: " + formString);
    	}
    	
    	page = MessageFormat.format(page, formString);
    	
    	return page;
    }
	
	public String getFileOptionsHTMLString(PDDocument pdf) {
		String optionsString = "<option value=\"{0}\">{1}</option>";
		
		String resultString = MessageFormat.format(optionsString, pdf.getDocumentId(), pdf.getDocumentInformation().getTitle());
		
		return resultString;
	}
	
	public String getPdfSelectHTMLString(PDDocument pdf, int number, String selectOptions) throws IOException {
		String HtmlString = "<label for=\"{0}\">File:</label>\r\n"
				+ "<select name=\"{0}\" id=\"{0}\">\r\n"
				+ "{2}\r\n"
				+ "</select>\r\n"
				+ "<label for=\"{1}\">Pages to select: </label>\r\n"
				+ "<input type=\"text\" name=\"{1}\" id=\"{1}\" />\r\n"
				+ "<br>";
		
		String selectFile = "select-file-" + number;
		String selectPages = "select-pages-" + number;

		
		String resultString = MessageFormat.format(HtmlString, selectFile, selectPages, selectOptions);
		
		return resultString;
				
	}	

*/
}