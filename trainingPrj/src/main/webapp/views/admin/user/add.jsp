<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>add</title>
</head>
<body>
<div class="main-content">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
									${message}
							</div>
						</c:if>
                        <form id="formSubmit" name="formSubmit" form action="<c:url value='/admin-user'/>" method="post" onsubmit="return validateForm()">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">役職：</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="authorityId" name="authorityId">
                                     
                                            <option value=""></option>
                                            <c:forEach var="item" items="${roles}">
                                                <option value="${item.authorityId}" >${item.authorityName}</option>
                                            </c:forEach>
                                        
                                    </select>
                                </div>
                            </div>
                           	<br/>
                           	<br/>
                            <br/>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">性別：</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="genderId" name="genderId">
                                     
                                            <option value=""></option>
                                            <c:forEach var="item" items="${genders}">
                                                <option value="${item.genderId}" >${item.genderName}</option>
                                            </c:forEach>
                                       

                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">ユーザID：</label>
                                <div class="col-sm-9">
                                <input type="text" class="form-control" id="userId" name="userId" required/>													
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">年齢：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="age" name="age" digits/>
                                </div>                             
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">名：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="firstName" name="firstName" required/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">姓：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="familyName" name="familyName" required/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">パスワード：</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="password" name="password" required></textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
							<div class="custom-control custom-checkbox">
							    <label class="custom-control-label" for="defaultUnchecked">管理者：</label>

							    	<input type="checkbox" name="admin" id="admin">
							    					    	
							</div>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <input type="hidden" value="${model.userId}" id="userId" name="userId"/>
                            <input type="hidden" value="add" name="action"/>
							<button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=list'">戻る</button>
							<button type="submit" class="btn btn-primary" >登録</button>				                            
                        </form>

                </div>
            </div>
        </div>
    </div>
</div>
<script>	
		$(":checkbox").change(function(){
		     $(this).val($(this).is(":checked") ? 1 : 0);
		});
	
	   $(document).ready(function() {
		   
	        //Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
	        $("#formSubmit").validate({
	            rules: {
	            	userId: {
                        required: true,
                        maxlength: 8
                    },
	            	firstName: {
                        required: true,
                        maxlength: 10
                    },

	            	familyName: {
                        required: true,
                        maxlength: 10
                    },
	            	password: {
                        required: true,
                        maxlength: 8
                    },
                    age:{
                    	digits:true
                    }
	
	            },
	            messages: {
	            	userId: {
                        required: "\u30E6\u30FC\u30B6ID\u304C\u5165\u529B\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002",
                        maxlength: "max8"
                    },
	            	firstName: {
                        required: "\u540D\u304C\u5165\u529B\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002",
                        maxlength: "max10"
                    },

	            	familyName: {
                        required: "\u6027\u304C\u5165\u529B\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002",
                        maxlength: "max10"
                    },
	            	password: {
                        required: "\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u5165\u529B\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002",
                        maxlength: "max8"
                    },
                    age:{
                    	digits:"int"
                    }
	
	            }
	        });
	    });
	
</script>
</body>
</html>
