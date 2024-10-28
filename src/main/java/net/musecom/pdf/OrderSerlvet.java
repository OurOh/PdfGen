package net.musecom.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Servlet implementation class OrderSerlvet
 */
@WebServlet("/order")
public class OrderSerlvet extends HttpServlet {
	
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ ��ǰ�̸� ���� ��������
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		String[] produceNames = parameterMap.get("pdname[]");
		String[] productPrices = parameterMap.get("pdprice[]");
		String totalPrice = request.getParameter("totalPrice");
		
		StringBuilder orderDetails = new StringBuilder();
		
		if(productNames != null 
				&& productPrices != null 
					&& productNames.length == productPrices.length) {
			
			for(int i =0; i< productNames.length; i++) {
				String productName = productNames[i];
				String productPrice = productPrices[i];
				orderDetails.append("��ǰ�� : ").append(productName).append("\n");
				orderDetails.append("���� : ").append()
			}
		}
		
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachement; filename = order.pdf");
		OutputStream out = response.getOutputStream();
		
		Document document = new Document();
		try {
			
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(new Paragraph("����"));
			document.add(new Paragraph("--------------"))
			document.add(new Paragraph(orderDetails.toString()));
			document.add(new Paragraph("--------------"))
			document.add(new Paragraph("�� ����:" + totalPrice + "��"));
			document.add(new Paragraph("���� �����մϴ�."));
			
			
		}catch(DocumentException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}









