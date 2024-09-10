/**
 * comments.js
 * /post/details.html에 포함.
 * 댓글 생성, 목록, 수정, 삭제. 
 */

document.addEventListener('DOMContentLoaded', () => { 
    let currentPageNo = 0; // 현재 댓글 목록의 페이지 번호
    // --> getAllComments() 함수에서 Ajax 요청을 보내고, 정상 응답이 오면 현재 페이지 번호가 바뀜
    // --> currentPageNo의 값은 [더보기] 버튼에서 사용.
    
    
    // bootstrap 라이브러리의 Collapse 객체를 생성: 
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle: false});
    
    // [댓글 보기] 버튼을 찾아서 클릭 이벤트 리스너를 설정.
    const btnToggle = document.querySelector('button#btnToggle');
    btnToggle.addEventListener('click', () => {
        
        bsCollapse.toggle(); // Collapse 객체를 보기/감추기 토글.
        
        const toggle = btnToggle.getAttribute('data-toggle');
        if(toggle === 'collapse') { // '===' : 변수의 데이터 타입까지 비교      
            btnToggle.innerHTML = '댓글 감추기';
            btnToggle.setAttribute('data-toggle', 'unfold');
            
            // 댓글 목록 불러오기 
            getAllComments(0);
            
        } else {
            btnToggle.innerHTML = '댓글 보기';
            btnToggle.setAttribute('data-toggle', 'collapse');
        }
        
    });
    
    // 댓글 [등록] 버튼을 찾아서 클릭 이벤트 리스너를 설정.
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    btnRegisterComment.addEventListener('click', registerComment);
    
    // 댓글 [더보기] 버튼을 찾아서 클릭 이벤트 리스너를 설정.
    const btnMoreComments = document.querySelector('button#btnMoreComments');
    btnMoreComments.addEventListener('click', () => getAllComments(currentPageNo + 1));
    
    
    //---------------------------------------- 함수 정의(선언) ----------------------------------------
    function registerComment() {
        // 댓글이 달린 포스트의 아이디
        const postId = document.querySelector('input#id').value; // --> 문자열. 숫자로 변환하려면 함수 이용.
        // 댓글 내용 
        const ctext = document.querySelector('textarea#commentText').value;
        // 댓글 작성자
        const writer = document.querySelector('input#commentWriter').value;
        
        if (ctext.trim() === '') { 
        // trim() --> 문자열 앞/뒤 공백 자르고 내용 입력. 스페이스바로 만든 공백만 넣은 경우에는 등록되지 않도록. 
            alert('댓글 내용을 입력하세요.');
            return;
        }
        
        // Ajax 요청에서 보낼 데이터
        /* 
        const d = {
            postId: postId,
            ctext: ctext,
            writer: writer,  
        };
        */
       // 배열:[], 객체:{} 프로퍼티이름과 지역변수(값)의 이름이 같으면 간단하게 객체를 선언할 수 있음. 
        const data = { postId, ctext, writer }; 
        
        // Ajax POST 방식 요청을 보내고, 응답/에러 처리 콜백 등록.
        axios.post('/api/comment', data)
            .then((response) => {
                console.log(response.data);
                alert('댓글이 등록되었습니다.');
                
                // 댓글 내용 입력란을 비움.
                document.querySelector('textarea#commentText').value = '';
                
                // 댓글 목록 갱신
                getAllComments(0);
                
            })
            .catch((error) => console.log(error));
    }
    
    function getAllComments(pageNo) {
        // 댓글들이 달린 포스트 아이디:
        const postId = document.querySelector('input#id').value;
        
        // Ajax 요청을 보낼 주소:
        // path variable: 댓글이 달린 포스트 아이디. request param: 페이지 번호. 
        const uri = `/api/comment/all/${postId}?p=${pageNo}`;
        
        // Ajax 요청을 보내고, 성공/실패 콜백 설정:
        axios.get(uri)
            .then((response) => {
                console.log(response);
                currentPageNo = response.data.number;
                
                // 현재 페이지 번호보다 페이지 개수가 많으면 댓글 [더보기] 버튼을 보여줌.
                const divBtnMore = document.querySelector('div#divBtnMore');
//              if (currentPageNo + 1 < response.data.totalPages) {
                if (!response.data.last) {
                    divBtnMore.classList.remove('d-none');
                } else {
                    divBtnMore.classList.add('d-none');
                }
                
                makeCommentElements(response.data.content, response.data.number);
            })
            .catch((error) => console.log(error));
            
    }
    
    function makeCommentElements(data, pageNo) {
        // 로그인 사용자 정보 --> 댓글  삭제/업데이트 버튼 여부 결정.
        const authUser = document.querySelector('span#authenticatedUser').innerText;
//        console.log(`authUser = ${authUser}`);
        
        // 댓글 목록을 추가할 div 요소 
        const divComments = document.querySelector('div#divComments');
        
        let htmlStr=''; // div에 삽입할 html 코드(댓글 목록)
        for (let comment of data) { // for(x in y) --> 인덱스, for(x of y) --> 데이터
            // console.log(comment);
            htmlStr += `
            <div class="card card-body mt-2">
                <div class="mt-2">
                    <span class="fw-bold">${comment.writer}</span>
                    <span class="text-secondary">${comment.modifiedTime}</span>
                </div>
                <div class="mt-2">
                    <div class="row"> 
                        <div class="mt-2 col-9">
                            <textarea class="commentText form-control" data-id="${comment.id}">${comment.ctext} </textarea> 
                        </div>
            `;
            
            // 로그인 사용자와 댓글 작성자가 같은 경우에만 삭제/수정 버튼이 보임.
            if (authUser === comment.writer) {
                htmlStr += `                   
                            <div class="mt-2 col-3">
                                <button class="btnDelete btn btn-outline-danger btn-sm" data-id="${comment.id}">삭제</button>
                                <button class="mx-2 btnUpdate btn btn-outline-primary btn-sm" data-id="${comment.id}">수정</button>
                            </div>
                        </div>
                    </div>
                </div>
                `;
            } else {
                htmlStr += `
                        </div>
                    </div>
                </div>
                `;
            }
        }
        
        if (pageNo === 0) {
            // 댓글 목록 첫번째 페이지면, 기존 내용을 다 지우고 새로 작성.
            divComments.innerHTML = htmlStr;
        } else {
            // 댓글 목록에서 첫번째 페이지가 아니면, 기존 내용 밑에 추가.
            divComments.innerHTML += htmlStr;
        }
        
        
        // 댓글 [삭제], [수정] 버튼들의 이벤트 리스너는 버튼들이 생겨난 이후에 등록!!
        
        // 댓글 [삭제] ==> 모든 button.btnDelete 버튼들을 찾아서 클릭 이벤트 리스너를 등록. 
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        btnDeletes.forEach((btn) => {  // 배열의 원소들을 순서대로 아규먼트로 전달.
            btn.addEventListener('click', deleteComment);
        }); 
        
        // 댓글 [수정]
        const btnUpdates = document.querySelectorAll('button.btnUpdate');
        btnUpdates.forEach((btn) => {
           btn.addEventListener('click', updateComment); 
        });
        
        
    }
    
    function deleteComment(event) {
//      console.log(event);
//      console.log(event.target);
        if (!confirm('정말 삭제할까요?')) { // '취소' ==> true
            return;
        }
        
        const id = event.target.getAttribute('data-id');
        const uri = `/api/comment/${id}`; // 삭제 Ajax 요청을 보낼 주소
        axios.delete(uri)
            .then((response) => {
                console.log(response);
                alert(`댓글 #${id}이 삭제되었습니다.`);
                getAllComments(0); // 댓글 목록 갱신
            })
            .catch((error) => console.log(error));
    }
    
    function updateComment(event) {
//      console.log(event.target);
        const id = event.target.getAttribute('data-id'); // 업데이트할 댓글 아이디
        const textarea = document.querySelector(`textarea.commentText[data-id="${id}"]`); // 속성값:[]
//      console.log(textarea);
        
        const ctext = textarea.value; // 업데이트할 댓글 내용
        if (ctext.trim() === '') {
            alert('댓글 내용은 반드시 입력해야 합니다.');
            return;
        }
        
        if (!confirm('수정한 댓글을 저장하시겠습니까?')) {
            return;
        }
        
        const uri = `/api/comment/${id}`; // Ajax 요청을 보낼 주소
        const data = { id, ctext }; // {id: id, ctext: ctext} 업데이트 요청 데이터.
        axios.put(uri, data)
            .then((response) => {
                console.log(response);
                alert(`댓글 #${id}이 업데이트 되었습니다.`);
                getAllComments(0); // 댓글 목록 갱신
            })
            .catch((error) => console.log(error));
    }
    
});