<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Lab 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="Post Modify" /> <%-- scope의 기본값은 page --%>
        <%@ include file="../fragments/header.jspf" %>
        
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>포스트 수정 페이지</h2>
                </div>
                <div class="card-body">
                    <form id="modifyForm">
                        <div class="mt-2">
                            <label for="id" class="form-label">번호</label>
                            <input id="id" class="form-control" type="text" name="id" value="${post.id}" readonly />
                            <%-- ${post.id}는 req.setAttribute("post", post); 에서 가져옴. --%>
                        </div>
                        <div class="mt-2">
                            <label for="title" class="form-label">제목</label>
                            <input id="title" class="form-control" type="text" name="title" value="${post.title}" />
                        </div>
                         <div class="mt-2">
                            <label for="content" class="form-label">내용</label>
                            <textarea id="content" class="form-control" name="content" rows="5">${post.content}</textarea>
                        </div>
                        <div class="mt-2 d-none">
                            <label for="author" class="form-label">작성자</label>
                            <input id="author" class="form-control" type="text" value="${post.author}" readonly />
                        </div>
                        
                    </form>
                </div>
                
                <%-- 'url'로 직접 접근해도 삭제 및 업데이트가 뜨지 않는다. --%>
                <c:if test="${post.author eq signedInUser}">
                    <div class="card-footer">
                        <button id="btnDelete" class="btn btn-outline-danger">삭제</button>
                        <button id="btnUpdate" class="btn btn-outline-success">업데이트</button>
                    </div>
                </c:if>
            </div>
        </main>
    </div>
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
    
    <%-- body 끝에서 작성(!순서 중요! 이벤트리스너들은 요소들이) --%>
    <c:url var="post_modify_js" value="/js/post_modify.js" />
    <script src="${post_modify_js}"></script>
    
</body>
</html>