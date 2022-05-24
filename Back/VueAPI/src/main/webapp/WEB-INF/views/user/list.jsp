<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <script type="text/javascript">
	$(document).ready(function() {
    
		//회원 목록
	
		$.ajax({
			url:'${root}/admin/user',  
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(users) {
				console.log(users); 
				makeList(users);
			},
			error:function(xhr, status, error){
				console.log("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
			},
			statusCode: {
				500: function() {
					alert("서버에러.");
					// location.href = "/error/e500";
				},
				404: function() {
					alert("페이지없다.");
					// location.href = "/error/e404";
				}
			}	
		});

		
    	$("#searchBtn").click(function(){
			let vkey = $("#skey").val(); 
			let vword = $("#sword").val(); 
			console.log(vkey); 
			console.log(vword); 
			$.ajax({
				url:'${root}/admin/user/'+vkey + '/'+vword,  
				type:'GET',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				success:function(users) {
					console.log(users); 
					makeList(users);
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
    	});
    	
		$("#registerBtn").click(function() {
			let registerinfo = JSON.stringify({
				"phone": $("#phone").val(),
				"name" : $("#name").val(), 
				"id" : $("#id").val(), 
				"pwd" : $("#pwd").val(), 
				"addr" : $("#addr").val()
			   });
			$.ajax({
				url:'${root}/admin/user',  
				type:'POST',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data: registerinfo,
				success:function(users) {
					$("#id").val('');
					$("#name").val('');
					$("#pwd").val('');
					$("#phone").val('');
					$("#addr").val('');
					$("#userRegModal").modal("hide");
					makeList(users);
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
		});
		
		$(document).on("dblclick", "tr.view", function() {
			let vid = $(this).attr("data-id");
			$.ajax({
				url:'${root}/admin/user/' + vid,  
				type:'GET',
				contentType:'application/json;charset=utf-8',
				success:function(user) {
					$("#vid").text(user.id);
					$("#vname").text(user.name);
					$("#vaddr").text(user.addr);
					$("#vphone").text(user.phone);
					$("#userViewModal").modal();
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}				
			});			
		});
		
		// 회워 정보 수정 보기.
		$(document).on("click", ".modiBtn", function() {
			let mid = $(this).parents("tr").attr("data-id");
			$("#view_" + mid).css("display", "none");
			$("#mview_" + mid).css("display", "");
		});
		
		// 회워 정보 수정 실행.
		$(document).on("click", ".modifyBtn", function() {
			let mid = $(this).parents("tr").attr("data-id");
			let modifyinfo = JSON.stringify({
						"id" : mid, 
						"pwd" : $("#pwd" + mid).val(), 
						"phone" : $("#phone" + mid).val(),
						"addr" : $("#addr" + mid).val()
					   });
			$.ajax({
				url:'${root}/admin/user',  
				type:'PUT',
				contentType:'application/json;charset=utf-8',
				dataType:'json',
				data: modifyinfo,
				success:function(users) {
					makeList(users);
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				}
			});
		});
		
		// 회워 정보 수정 취소.
		$(document).on("click", ".cancelBtn", function() {
			let mid = $(this).parents("tr").attr("data-id");
			$("#view_" + mid).css("display", "");
			$("#mview_" + mid).css("display", "none");
		});
		
		// 회워 탈퇴.
		$(document).on("click", ".delBtn", function() {
			if(confirm("정말 삭제?")) {
				let delid = $(this).parents("tr").attr("data-id");
				console.log(delid); 
				$.ajax({
					url:'${root}/admin/user/' + delid,  
					type:'DELETE',
					contentType:'application/json;charset=utf-8',
					dataType:'json',
					success:function(users) {
						makeList(users);
					},
					error:function(xhr,status,msg){
						console.log("상태값 : " + status + " Http에러메시지 : "+msg);
					}
				});
			}
		});
	});
	
	function makeList(users) {
		$("#userlist").empty();
		$(users).each(function(index, user) {
			/*
			let str = "<tr id=\"view_" + user.userId + "\" class=\"view\" data-id=\"" + user.userId + "\">"
			+ "	<td>" + user.userId + "</td>"
			+ "	<td>" + user.userPwd + "</td>"
			+ "	<td>" + user.userName + "</td>"
			+ "	<td>" + user.email + "</td>"
			+ "	<td>" + user.joinDate + "</td>"
			+ "	<td><button type=\"button\" class=\"modiBtn btn btn-outline-primary btn-sm\">수정</button> "
			+ "		<button type=\"button\" class=\"delBtn btn btn-outline-danger btn-sm\">삭제</button></td>"
			+ "</tr>"
			+ "<tr id=\"mview_" + user.userId + "\" data-id=\"" + user.userId + "\" style=\"display: none;\">"
			+ "	<td>" + user.userId + "</td>"
			+ "	<td><input type=\"text\" name=\"userpwd\" id=\"userpwd" + user.userId + "\" value=\"" + user.userPwd + "\"></td>"
			+ "	<td>" + user.userName + "</td>"
			+ "	<td><input type=\"text\" name=\"email\" id=\"email" + user.userId + "\" value=\"" + user.email + "\"></td>"
			+ "	<td>" + user.joinDate + "</td>"
			+ "	<td><button type=\"button\" class=\"modifyBtn btn btn-primary btn-sm\">수정</button> "
			+ "		<button type=\"button\" class=\"cancelBtn btn btn-danger btn-sm\">취소</button></td>"
			+ "</tr>";
			*/
			let str = `
					<tr id="view_${'${user.id}'}" class="view" data-id="${'${user.id}'}">
					<td>${'${user.id}'}</td>
					<td>${'${user.pwd}'}</td>
					<td>${'${user.name}'}</td>
					<td>${'${user.addr}'}</td>
					<td>${'${user.phone}'}</td>
					<td>
						<button type="button" class="modiBtn btn btn-outline-primary btn-sm">수정</button>
						<button type="button" class="delBtn btn btn-outline-danger btn-sm">삭제</button>
					</td>
				</tr>
				<tr id="mview_${'${user.id}'}" data-id="${'${user.id}'}" style="display: none;">
					<td>${'${user.id}'}</td>
					<td><input type="text" name="pwd" id="pwd${'${user.id}'}" value="${'${user.pwd}'}"></td>
					<td>${'${user.name}'}</td>
					<td><input type="text" name="addr" id="addr${'${user.id}'}" value="${'${user.addr}'}"></td>
					<td>${'${user.phone}'}</td>
					<td>
						<button type="button" class="modifyBtn btn btn-primary btn-sm">수정</button>
						<button type="button" class="cancelBtn btn btn-danger btn-sm">취소</button>
					</td>
				</tr>
			`;
			$("#userlist").append(str);
		});//each
	}
	</script>	  
	       
	<div class="container text-center mt-3">
        <div class="col-lg-12 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="pink">회원목록</mark></h2>
              <div class="m-3 row justify-content-end">
            	<form id="searchform" class="form-inline" method="get" onsubmit = "return false">
	            	<select id="skey" name="key" class="form-control">
	            		<option value="id"> 아이디
	            		<option value="name"> 이름
	            		<option value="addr">주소
	            	</select>
	            	<input type="text" class="ml-1 form-control" id="sword" name="word" value="${word}">
	            	<button type="button" id="searchBtn" class="ml-1 btn btn-outline-primary">검색</button>
            	</form>
            </div>
			<div class="mb-3 text-right"><button type="button" class="modiBtn btn btn-outline-info" data-toggle="modal" data-target="#userRegModal">등록</button></div>
		  	<table class="table table-hover">
		  		<colgroup>
		            <col width="120">
		            <col width="120">
		            <col width="120">
		            <col width="170">
		            <!-- <col width="*"> -->
		            <col width="120">
		            <col width="130">
		        </colgroup>
		    	<thead>
			      	<tr>
			        	<th class="text-center">아이디</th>
			        	<th class="text-center">비밀번호</th>
			        	<th class="text-center">이름</th>
			        	 <th class="text-center">주소</th>
			        	<th class="text-center">연락처</th>
			        	<th class="text-center">수정/삭제</th>
			      	</tr>
		    	</thead>
		    	<tbody id="userlist"></tbody>
			</table>
		</div>
		
		<!-- 회원 등록 모달 -->
		<div class="modal" id="userRegModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">회원등록</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
		        <form id="memberform" method="post" action="">
					<div class="form-group" align="left">
						<label for="name">이름</label>
						<input type="text" class="form-control" id="name" name="name" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label for="">아이디</label>
						<input type="text" class="form-control" id="id" name="id" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label for="">비밀번호</label>
						<input type="password" class="form-control" id="pwd" name="pwd" placeholder="">
					</div>
			
				 	<div class="form-group" align="left">
						<label for="">주소</label>
						<input type="text" class="form-control" id="addr" name="addr" placeholder="">
					</div> 
					<div class="form-group" align="left">
						<label for="">전화번호</label>
						<input type="text" class="form-control" id="phone" name="phone" placeholder="숫자만 기재하세요.">
					</div> 
					<div class="form-group" align="center">
						<button type="button" class="btn btn-primary" id="registerBtn">회원가입</button>
						<button type="reset" class="btn btn-warning">초기화</button>
					</div>
				</form>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- 회원 정보 모달 -->
		<div class="modal" id="userViewModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">회원정보</h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
		       	<table class="table table-bordered">
		            <colgroup>
		                <col width="120">
		                <col width="*">
		                <col width="120">
		                <col width="*">
		            </colgroup>
		            <tbody>
		            <tr>
		                <th class="text-center">ID</th>
		                <td class="text-left" id="vid"></td>
		                <th class="text-center">회원명</th>
		                <td class="text-left" id="vname"></td>
		            </tr>
		            <tr>
		            	<th class="text-center">주소</th>
		                <td class="text-left" id="vaddr"></td>
		                <th class="text-center">전화번호</th>
		                <td class="text-left" id="vphone"></td>
		            </tr>
		            <!-- <tr>
		                <th class="text-center">주소</th>
		                <td class="text-left" colspan="3" id="vaddress"></td>
		            </tr> -->
		            </tbody>
		        </table>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>