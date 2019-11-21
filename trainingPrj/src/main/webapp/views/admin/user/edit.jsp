<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
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
                                        <c:if test="${empty model.authorityId}">
                                            <option value=""></option>
                                            <c:forEach var="item" items="${roles}">
                                                <option value="${item.authorityId}" >${item.authorityName}</option>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty model.authorityId}">
                                            <option value=""></option>
                                            <c:forEach var="item" items="${roles}">
                                                <option value="${item.authorityId}"  <c:if test="${item.authorityId == model.authorityId}">selected="selected"</c:if>>
                                                        ${item.authorityName}
                                                </option>
                                            </c:forEach>
                                        </c:if>
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
                                        <c:if test="${empty model.genderId}">
                                            <option value=""></option>
                                            <c:forEach var="item" items="${genders}">
                                                <option value="${item.genderId}" >${item.genderName}</option>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty model.genderId}">
                                            <option value=""></option>
                                            <c:forEach var="item" items="${genders}">
                                                <option value="${item.genderId}" <c:if test="${item.genderId == model.genderId}">selected="selected"</c:if>>
                                                        ${item.genderName}
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">ユーザID：</label>
                                <div class="col-sm-9">
                                <label style="color: red"><c:out value="${model.userId}"/></label>													
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">年齢：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="age" name="age" value="${model.age}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">名：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="firstName" name="firstName" value="${model.firstName}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">姓：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="familyName" name="familyName" value="${model.familyName}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">パスワード：</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="password" name="password" >${model.password}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
							<div class="custom-control custom-checkbox">
							    <label class="custom-control-label" for="defaultUnchecked">管理者：</label>
							    <c:if test = "${model.admin==1}">
							    	<input type="checkbox" name="admin" id="admin" checked="checked">
							    </c:if>
							    <c:if test = "${model.admin==0}">
							    	<input type="checkbox" name="admin" id="admin">
							    </c:if>						    	
							</div>
                            <br/>
                            <br/>
                            <input type="hidden" value="${model.userId}" id="userId" name="userId"/>
                            <input type="hidden" value="edit" name="action"/>
							<button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/admin-user?type=list'">戻る</button>
							<button type="submit" class="btn btn-primary" >更新</button>					                            
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
	
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        data["content"] = editor.getData();
        var id = $('#id').val();
        if (id == "") {
            addNew(data);
        } else {
            updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            }
        });
    }
</script>
</body>
</html>
