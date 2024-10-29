package net.musecom.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;


@WebServlet("/order")
public class OrderServlet extends HttpServlet {


   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      //폼에서 제품이를 가격 가져오기
      Map<String, String[]> parameterMap = request.getParameterMap();
      
      String[] productNames = parameterMap.get("pdname[]");
      String[] productPrices = parameterMap.get("pdprice[]");
      String totalPrice = request.getParameter("totalPrice");
      
      StringBuilder orderDetails = new StringBuilder();
      
      if(productNames != null 
             && productPrices != null 
                  && productNames.length == productPrices.length) {
         
         for(int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            String productPrice = productPrices[i];
            orderDetails.append("상품명 : ").append(productName).append("\n");
            orderDetails.append("가격 : ").append(productPrice).append("원 \n");
         }
      }
      
      if(orderDetails.length() == 0) {
         response.sendError(HttpServletResponse.SC_BAD_REQUEST, "상품 정보가 올바르지 않음.");
         return;
      }
      
      // System.out.println(orderDetails.toString());
      //pdf 빌지 출력
      
      /*
      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition", "attachement; filename=order.pdf");
      OutputStream out = response.getOutputStream();
      */
      
      //PDF 파일 저장 경로
      String pdfDirPath = getServletContext().getRealPath("/pdfgen");
      File pdfDir = new File(pdfDirPath);
      if(!pdfDir.exists()) {
         pdfDir.mkdirs();  //폴더가 없으면 생성한다.
      }
      
      String pdfPath = pdfDirPath + File.separator + "order.pdf";
      
      Document document = new Document();
      try {
         
         PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
         document.open();
         
         
         
      // 한글폰트 설정
         Font font;
         try {
             // summernote.ttf 파일 경로로 수정
             String fontPath = getServletContext().getRealPath("/res/fonts/summernote.ttf");
             BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
             font = new Font(bf, 16, Font.NORMAL, BaseColor.BLACK);
             System.out.println("폰트로드에 성공했습니다. " + fontPath);
         } catch(IOException e) {
             font = new Font(Font.FontFamily.TIMES_ROMAN, 16);
             System.out.println("폰트 로드 실패");
         }
         // 타이틀 폰트
         Font titleFont = new Font(font);
         titleFont.setColor(BaseColor.PINK);
         document.add(new Paragraph("빌~~~ 지", titleFont));
         document.add(new Paragraph("--------------------------", font));
         document.add(new Paragraph(orderDetails.toString(), font));
         document.add(new Paragraph("--------------------------", font));
         document.add(new Paragraph("총 가격 : " + totalPrice + "원", font));
         document.add(new Paragraph("호갱님 감사합니다. ㅋㅋ ", font));

         
      }catch(DocumentException e) {
         e.printStackTrace();
      }finally {
         document.close();
         //out.close();
         System.out.println("pdf파일이 생성되었습니다. 🤷‍");
      } 
      //파일경로 보내기
      response.setContentType("application/json");
      response.getWriter().write("{\"filePath\" : \"pdfgen/order.pdf\"}");
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }
   
   

}
