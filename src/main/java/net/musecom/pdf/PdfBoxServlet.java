package net.musecom.pdf;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


@WebServlet("/orderbox")
public class PdfBoxServlet extends HttpServlet {
       

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      //������ ��ǰ�̸� ���� ��������
      Map<String, String[]> parameterMap = request.getParameterMap();
      
      String[] productNames = parameterMap.get("pdname[]");
      String[] productPrices = parameterMap.get("pdprice[]");
      String totalPrice = request.getParameter("totalPrice");
      
      //PDF ���� ���� ���
      String pdfDirPath = getServletContext().getRealPath("/pdfgen");
      File pdfDir = new File(pdfDirPath);
      if(!pdfDir.exists()) {
         pdfDir.mkdirs();  //������ ������ �����Ѵ�.
      }
      
      String pdfPath = pdfDirPath + File.separator + "order.pdf";
      
      PDDocument document = new PDDocument();
      PDPage page = new PDPage();
      document.addPage(page);
      
      PDPageContentStream cs = null;
      
      try {
         //��Ʈ ����
         contentStream = new PDPageContentStream(document, page);
         PDType0Font font;
         try {
            String fontPath = getServletContext().getRealPath("/res/fonts/HMKMOLD.TTF");
            font = PDType0Font.load(document, new File(fontPath));
            System.out.println("��Ʈ �ε� ���� " + fontPath);
         }catch(IOException e) {
            e.printStackTrace();
            System.out.println("��Ʈ �ε� ����");
            font = PDType0Font.load(document, new File(getServletContext().getRealPath("/res/fonts/YBLO05.TTF")));
         }
         
         //Ÿ��Ʋ
         cs.beginText();
         cs.setFont(font, 20);
         cs.newLineAtOffset(50, 750);
         cs.showText("�������ϴ�.");
         cs.endText();
         
         cs.beginText();
         cs.setFont(font, 20);
         cs.newLineAtOffset(50, 750);
         cs.showText("-----------------");
         cs.endText();
         
      }catch(IOException e) {
         e.printStackTrace();
      }finally {
    	  if(cs != null) cs.close();
    	  document.save(pdfPath);
    	  document.close();
      }
      response.setContentType("application/json");
      response.getWriter().write("{\"filePath)
      
   }


   
   
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
