package com.algonquin.pdfcombinator.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFUtilities {

	// Get the requested page numbers from the input field 
	// In same format as you would specify pages to be printed, 
	// e.g. "1-4, 7, 9-12" would output [1, 2, 3, 4, 7, 9, 10, 11, 12]
	public static int[] extractPageNums(String s) {
		int[] pageNums;
		// RegEx to match only numbers with commas and/or dashes
		if (s.matches("^\\d+(?:-\\d+)?(?:,\\h*\\d+(?:-\\d+)?)*$")) {
			// Split on comma (and optional space)
			String[] arr = s.split(",[ ]*");
			List<String> lst = new ArrayList<String>();
			// Add each number to list of page numbers
			for (int i= 0; i < arr.length; i++) {
				String e = arr[i];
				// If range of pages
				if (e.matches("^\\d+-\\d+$")) {
					lst.add(e.split("-")[0]);
					if (Integer.parseInt(e.split("-")[1]) - Integer.parseInt(e.split("-")[0]) > 0) {
						for (int j = 1; j <= (Integer.parseInt(e.split("-")[1]) - Integer.parseInt(e.split("-")[0])); j++) {
							lst.add(Integer.toString(Integer.parseInt(e.split("-")[0]) + j));
						}
					}	
				} else {
					lst.add(e);
				}
			}
			
			pageNums = new int[lst.size()];
			for (int i = 0; i< lst.size(); i++) {
				pageNums[i] = Integer.parseInt(lst.get(i));
			}
		// No page numbers entered	
		} else if (s.equals("")) {
			pageNums = new int[0];
			System.out.println("No page numbers were entered");
		// Invalid string entered	
		} else {
			System.out.println(s + " is an invalid page number string");
			pageNums = null;
		}
		return pageNums;	
	}	
		
	// Returns a list of pdf pages specified, in the correct order	
	public ArrayList<PDPage> extractPdfPages(File file, String pageNumbers) {
		
		// Initialize variables
		ArrayList<PDPage> pdfPages = null;
		PDDocument pdf;
		int[] pageNums;
			
		if (file != null) {
			try {
				// Load PDF 
				pdf = Loader.loadPDF(file);
				
				// Get correct page numbers
				pageNums = extractPageNums(pageNumbers);
				
				// Add all requested PDF pages to ArrayList
				for (int pageNum : pageNums ) {
					// Make sure page number is valid
					if (pageNum <= pdf.getNumberOfPages()) {
						// Adjust page number to zero-based
						PDPage page = pdf.getPage(pageNum-1);
						try {
							pdfPages.add(page);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}	
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}
		
		return pdfPages;
	}
	
	
	// Adds specified PDF pages to existing array
	public static void addPdfPages(List<PDPage> pdfPages, PDDocument pdf, String pageNumbers) {
		int[] pageNums;
		
		// Get correct page numbers
		pageNums = extractPageNums(pageNumbers);
		
		// if no pagenumbers entered, include entire document
		if (pageNums.length == 0) {
			for (int i = 0; i < pdf.getNumberOfPages(); i++) {
				pdfPages.add(pdf.getPage(i));
			}
		} else {
			// Add all requested PDF pages to ArrayList
			for (int pageNum : pageNums ) {
				// Make sure page number is valid
				if (pageNum <= pdf.getNumberOfPages()) {
					// Adjust page number to zero-based
					PDPage page = pdf.getPage(pageNum-1);
					try {
						pdfPages.add(page);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}	
			}
		}
		
		
		// For debugging: print pagenumbers
		for (int j : pageNums) {
			System.out.println("Page requested: " + j);
		}
		
		
	}
	
	
	// Returns a list of pdf pages specified, in the correct order	
		public ArrayList<PDPage> extractPdfPages(PDDocument pdf, String pageNumbers) {
			
			// Initialize variables
			ArrayList<PDPage> pdfPages = null;
			int[] pageNums;
				
			// Get correct page numbers
			pageNums = extractPageNums(pageNumbers);
			
			// Add all requested PDF pages to ArrayList
			for (int pageNum : pageNums ) {
				// Make sure page number is valid
				if (pageNum <= pdf.getNumberOfPages()) {
					// Adjust page number to zero-based
					PDPage page = pdf.getPage(pageNum-1);
					try {
						pdfPages.add(page);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}	
			}
			return pdfPages;
		}
		
	
	// Create combined PDF document from separate PDF pages
	public static PDDocument createCombinedPDF(List<PDPage> pdfList) {
		PDDocument document = new PDDocument();
		for (PDPage page : pdfList) {
			document.addPage(page);
		}
		return document;
	}
	
	public PDDocument createPdfFromPageNums(PDDocument pdf, String pageNums) {
		PDDocument resultPDF;
		resultPDF = createCombinedPDF(extractPdfPages(pdf, pageNums));	
		
		return resultPDF;
	}
	
}
