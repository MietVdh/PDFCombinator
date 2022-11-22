package com.algonquin.pdfcombinator.servlets;

//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

@MultipartConfig
@WebServlet("/upload")
public class UploadPdfServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	private List<PDDocument> uploadedPdfs = new ArrayList<>();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In UploadServlet doGet()");
		request.getRequestDispatcher("/html/upload.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In UploadServlet doPost()");
		
		// Check if any files have been uploaded; if not, display error message
		if (!ServletFileUpload.isMultipartContent(request)) {
			message = "No files were selected for upload";
			System.out.println(message);
			return;
		}
		

		// Upload files and add them to list of uploaded files
		uploadFile("file1name", "file1", request);
		System.out.println("DoPost - After uploading file1, pdfs contains " + uploadedPdfs.size() + " docs");
		
		
		uploadFile("file2name", "file2", request);
		System.out.println("DoPost - After uploading file2, pdfs contains " + uploadedPdfs.size() + " docs");

		
		uploadFile("file3name", "file3", request);
		System.out.println("DoPost - After uploading file3, pdfs contains " + uploadedPdfs.size() + " docs");

		
		
		request.setAttribute("pdfs", uploadedPdfs);
		
		HttpSession session = request.getSession();
		session.setAttribute("pdfs", uploadedPdfs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/html/select.jsp");
		
		dispatcher.forward(request, response);
	
	}

	
	private void uploadFile(String filename, String filepart, HttpServletRequest request) throws IOException, ServletException {
		
		System.out.println("*********In uploadFile");
		
		// Get file submitted by user
	    Part filePart = request.getPart(filepart); 
	    
	    // If no file uploaded, return
	    if (filePart.getSize() == 0) {
	    	return;
	    }
	    
	    // If file is not of correct type, return
	    if (!filePart.getContentType().equals("application/pdf")) {
	    	message = "Wrong file type";
	    	return;
	    }
	    
	    // Get user-submitted filename, if any
 		String submittedFileName = request.getParameter(filename); 
 		String fileName;
	    	    
	    // If user submitted filename, rename file
	    if (submittedFileName == null || submittedFileName.equals("")) {
	    	fileName = filePart.getSubmittedFileName();
	    } else {
	    	fileName = submittedFileName + ".pdf";
	    }
	        
	    // Load PDF
	    InputStream fileContent = filePart.getInputStream();
	    
	    PDDocument uploadedPdf = Loader.loadPDF(fileContent);
	    
	    PDDocumentInformation info = new PDDocumentInformation();
		info.setTitle(fileName);
		
	    uploadedPdf.setDocumentInformation(info);
	    uploadedPdf.setDocumentId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE); 
	    
	    // Add PDF to uploadedPdfs
	    uploadedPdfs.add(uploadedPdf);
	    System.out.println("PDF " + uploadedPdf.getDocumentInformation().getTitle()  + " has been added to list of uploaded PDFs");
	    
	    System.out.println("Uploadfile - List of uploaded PDFs now contains " + uploadedPdfs.size() + " documents");
	    
	    return;

	}

}
