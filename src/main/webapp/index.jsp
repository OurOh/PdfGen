<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shop</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<style>
.cart{
  position:absolute;
  z-index:100;
  top:0;
  right:-245px;
  width:260px;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<h1>My SHOP</h1>
<div class="container position-relative">
  <div class="row">
     <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/001.gif" alt="상품1">
          <div class="card-body">
             <h4 class="card-title">상품1</h4>
             <p class="card-text">500,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품1', 50000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
     
          <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/002.gif" alt="상품2">
          <div class="card-body">
             <h4 class="card-title">상품2</h4>
             <p class="card-text">350,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품2', 350000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
     
     
          <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/003.gif" alt="상품3">
          <div class="card-body">
             <h4 class="card-title">상품3</h4>
             <p class="card-text">50,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품3', 50000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
     
     
          <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/004.gif" alt="상품4">
          <div class="card-body">
             <h4 class="card-title">상품4</h4>
             <p class="card-text">30,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품4', 30000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
     
     
          <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/005.gif" alt="상품5">
          <div class="card-body">
             <h4 class="card-title">상품5</h4>
             <p class="card-text">550,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품5', 550000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
     
     
          <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/006.gif" alt="상품6">
          <div class="card-body">
             <h4 class="card-title">상품6</h4>
             <p class="card-text">75,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품6', 75000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
     
     
          <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/007.gif" alt="상품7">
          <div class="card-body">
             <h4 class="card-title">상품7</h4>
             <p class="card-text">20,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품7', 20000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
     
     
          <div class="col-md-3">
       <form class="card">
          <img class="card-img-top" src="images/001.gif" alt="상품8">
          <div class="card-body">
             <h4 class="card-title">상품8</h4>
             <p class="card-text">900,000원</p>
             <div class="text-center">
                <button type="button" class="btn btn-primary" onClick="addToCart('상품8', 900000)">카트에넣기</button>
             </div>
          </div>
       </form>
     </div>
  </div>
  <div class="cart text-center">
     <form class="cart-in"></form>
  </div>
</div>
<form action="order" method="post" id="orderform">

</form>

<script>
//전역으로 cart 변수 만듬 (여기에 담을 거임), 여러개 이니 배열
let cart = [];

function addToCart(pdname, pdprice){
	   let cartObj = {
	      "pdname" : pdname,
	      "pdprice" : pdprice
	   }
	   //배열 cart 에 새로 받은 상품을 push
	   cart.push(cartObj);
	   updateCart();
	}

	function updateCart(){

	   let totalPrice = 0;
	   let cartContent = '<ul class="list-group">';
	   cart.forEach(function(item, index){
	      if(item.pdname && typeof item.pdprice === 'number') {
	      totalPrice += item.pdprice;      
	      cartContent += "<li class='list-group-item d-flex justify-content-between'>" +
	                     "<div>"+
	                     "<label>이름</label>"+
	                     item.pdname +
	           "</div>"+
	           "<div>"+
	             "<label>가격</label>"+
	              item.pdprice.toLocaleString() +" 원"+ 
	           "</div>"+
	         "</li> " +
	         "<input type='hidden' name='pdname[]' value='"+item.pdname+"' />"+
	         "<input type='hidden' name='pdprice[]' value='"+item.pdprice+"' />";      
	      }else{
	         console.error('무언가 에러' , item);
	      }   
	   });   
	   
	   cartContent += '<p>총 가격 : '+totalPrice.toLocaleString()+'원</p>';
	   cartContent += '</ul>';
	   cartContent +='<button type="button" id="orderbtn" class="btn btn-success mt-3">주문하기</button>';
	     console.log(cartContent);
	    $('.cart-in').html(cartContent);
	}

	$(function(){
	   
	   //scroll 이벤트 셋팅
	   $(window).scroll(function(){
	      //1. scroll 위치 값 구하기 
	      let scrollTop = $(window).scrollTop();
	      //.cart에 top값으로 적용
	      $(".cart").css("top", scrollTop + "px");
	   });
	   
	   $(document).on('click', "#orderbtn", function(){
	      let productName = [];
	      let productPrice = [];
	      let totalPrice = 0;
	      $('input[name="pdname[]"]').each(function(){
	         productName.push($(this).val());
	      });
	      $('input[name="pdprice[]"]').each(function(){
	         productPrice.push(Number($(this).val()));
	      });

	      //가격의 합
	      totalPrice = productPrice.reduce((acc, currentVal) => acc + currentVal, 0);      
	      console.log("총가격 :" + totalPrice);
	        
	      //ajax 요청
	      $.jax({
	    	url: 'order',
	    	type:'post',
	    	data:{
	    		'pdname[]' : productName,
	    		'pdprice[]' : productPrice,
	    		'totalPrice' : totalPrce
	    	},
	    	success: function(data) {
	    		//서버로부터 pdf파일 받아서 다운로드 처리하기
	    		//Binary Large Object -- 이미지, 사운드, 비디오 등등 멀티미디어 데이터를 다룰 때 사용
	    		let blob = new Blob([data], {type: 'application/pdf'});
	    		let link = document.createElement('a');
	    		link.href = window.URL.createObjectURL(blob);
	    		link.download = "order.pdf";
	    		link.click();
	    	}, error: function(error){
	    		console.error('에러'+ error);
	    	}
	      })
	      
	        
	        
	        
	   });
	   
	});
</script>
</body>
</html>






