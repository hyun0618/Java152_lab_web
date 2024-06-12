<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Spring Legacy 2</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous" />
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="Post create" />
        <%@ include file="../fragments/header.jspf" %>
        
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>새 글 작성</h2>
                </div>
                <div class="card-body">
                    <c:url var="postCreatePage" value="/post/create" />
                    <form method="post" action="${ postCreatePage }"> 
                    <!-- form에서 action 속성 값을 설정하지 않으면 현재 요청 주소로 다시 요청을 보낸다. 
                    즉, 같은 주소로 요청을 보낼 때에는(저장할 때에는) 액션값을 주지 않아도 된다. -->
                        <div class="mt-2">
                            <input name="title" type="text" placeholder="제목 입력"
                            class="form-control" required autofocus />
                        </div>
                        <div class="mt-2">
                            <textarea name="content" rows="5" placeholder="내용 입력"
                            class="form-control" required></textarea>
                        </div>
                        <div class="mt-2" d-none>
                            <input name="author" type="text" placeholder="작성자"
                            class="form-control" required />
                        </div>
                        <div class="mt-2">
                            <input type="submit" value="저장" 
                            class="form-control btn btn-outline-success center" />
                        </div>
                    
                    </form> 
                </div>
                
            </div>    
        </main>    
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>