package com.algonquin.pdfcombinator.servlets;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
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
	
//	private static File uploadsFolder = new File("uploads");
	private String message;
	
//	private List<File> uploadedFiles = new ArrayList<>();
	private List<PDDocument> uploadedPdfs = new ArrayList<>();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In UploadServlet doGet()");
		String page = getHTMLString(request.getServletContext().getRealPath("/html/upload.html"));
		response.getWriter().write(page);
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
		
//		request.setAttribute("files", uploadedFiles);
		request.setAttribute("pdfs", uploadedPdfs);
		
		HttpSession session = request.getSession();
		session.setAttribute("pdfs", uploadedPdfs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/html/select.jsp");
		
		dispatcher.forward(request, response);
	
		
//		String page = getHTMLString(request.getServletContext().getRealPath("/html/select.html"));
//		response.getWriter().write(page);
	}
	
	
	public String getHTMLString(String filePath) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(filePath));
    	String line = "";
    	StringBuffer buffer = new StringBuffer();
    	while ((line=reader.readLine())!= null) {
    		buffer.append(line);
    	}
    	
    	reader.close();
    	String page = buffer.toString();
    	
//    	page = MessageFormat.format(page);
    	
    	return page;
    }
	
	private void uploadFile(String filename, String filepart, HttpServletRequest request) throws IOException, ServletException {
		
		/* Possibly unnecessary?
	

		// Path where files will be stored
		Path path = Files.createDirectories(Paths.get("C:/tmp/pdfcombinator/uploads"));
		
//		String path = System.getProperty("user.home") + File.separator + "/temp/uploads"; 
		// Source: https://stackoverflow.com/questions/21059085/how-can-i-create-a-file-in-the-current-users-home-directory-using-java/21059316#21059316
		
		// Create folder if it doesn't already exist
		Files.createDirectories(path);
		File uploadsFolder = new File(path.toString());
		
		*/
				
	    
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
	 				
	    
	    InputStream fileContent = filePart.getInputStream();
	    
	    
	    
	    // If user submitted filename, rename file
	    if (submittedFileName == null || submittedFileName.equals("")) {
	    	fileName = filePart.getSubmittedFileName();
	    } else {
	    	fileName = submittedFileName + ".pdf";
	    }
	    
	    
	    PDDocument uploadedPdf = Loader.loadPDF(fileContent);
	    PDDocumentInformation info = new PDDocumentInformation();
		info.setTitle(fileName);
	    uploadedPdf.setDocumentInformation(info);
	    uploadedPdf.setDocumentId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE); 
	    uploadedPdfs.add(uploadedPdf);
	    System.out.println("PDF " + uploadedPdf.getDocumentInformation().getTitle()  + " has been added to list of uploaded PDFs");
	    
	    System.out.println("Uploadfile - List of uploaded PDFs now contains " + uploadedPdfs.size() + " documents");
	    
	    
	    
	    
	    /* Possibly unnecessary?
	    File file = new File(uploadsFolder, fileName);
	    try {
	    	if (!file.exists()) {
	    		Files.createFile(file.toPath());
	    	}   	
		    Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    
	    System.out.println("File uploaded: " + fileName);
	    
	    uploadedFiles.add(file);

		*/
	}

}
