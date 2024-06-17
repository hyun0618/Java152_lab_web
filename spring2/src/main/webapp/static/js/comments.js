/**
 * /post/details.jsp에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {

    // btnToggleComment 버튼 element 찾기.
    const btnToggleComment = document.querySelector('button#btnToggleComment'); // Ajax ==> $('button#btn')

    // collapseComments div 요소를 부트스트랩의 Collapse 객체로 생성. 
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', { toggle: false });
    // ==> Collapse 메서드의 아규먼트: 문자열(''), 객체({}) 
    // {toggle: false} --> 필드 이름이 toggle, 값이 false인 객체 생성.(접혀진 상태로 보여줌.)

    // 댓글 토글 버튼에 클릭 이벤트 리스너 등록.
    btnToggleComment.addEventListener('click', () => {
        bsCollapse.toggle();

        if (btnToggleComment.innerHTML === '댓글 보기') {
            btnToggleComment.innerHTML = '댓글 닫기';

            // 포스트에 달려 있는 모든 댓글 목록 보여줌.
            getAllComments();

        } else {
            btnToggleComment.innerHTML = '댓글 보기';
        }
    });

    // 버튼 btnRegisterComment 요소에 이벤트 리스너 등록. 
    const btnRegisterComment = document.querySelector('button#btnRegisterComment'); // 버튼 찾기
    btnRegisterComment.addEventListener('click', registerComment); // 이벤트 리스너 설정.

    function registerComment() { // 댓글 등록 이벤트 리스너 콜백(함수)
        // 댓글이 달릴 포스트 번호 찾기.
        const postId = document.querySelector('input#id').value;

        // 댓글 내용 찾기.
        const ctext = document.querySelector('textarea#ctext').value;

        // 댓글 작성자 아이디 찾기.         
        const username = document.querySelector('input#username').value;

        // 댓글 내용, 댓글 작성자가 비어있는지 체크.
        if (ctext === '' || username === '') {
            alert('댓글 내용과 작성자는 반드시 입력하세요.');
            return; // 이벤트 리스너 종료.
        }

        // Ajax 요청에서 보낼 데이터 객체를 생성.
        /* const data = {
             postId: postId, // ==> "객체의 프로퍼티 이름: function registerComment()에서 선언된 변수"
             ctext: ctext,
             username: username  
        }; */
        const data = { postId, ctext, username };
        console.log(data);

        // POST 방식의 Ajax 요청을 보냄. 응답 성공/실패 콜백을 등록. 
        axios
            .post('../api/comment', data) // 요청주소: "spring2/api/comment"
            .then((response) => { // responseEntity.ok
                // console.log(response);     
                console.log(response.data); // RestController에서 보낸 응답 데이터.
                if (response.data === 1) { // 댓글 등록의 result가 1이므로
                    alert('댓글 등록 성공');
                    document.querySelector('textarea#ctext').value = '';
                    document.querySelector('input#username').value = '';
                    // 댓글 목록 갱신
                    getAllComments();
                }
            })
            .catch((error) => { // responseEntity.error
                console.log(error);
            });
    }

    // 포스트에 달려 있는 모든 댓글 목록 가져오기.
    function getAllComments() {
        // 댓글 목록을 요청하기 위한 포스트 번호
        const postId = document.querySelector('input#id').value;

        // 댓글 목록을 요청하기 위한 REST API URI
        const uri = `../api/comment/all/${postId}`;

        // Ajax 요청을 보냄. 
        axios
            .get(uri)
            .then((response) => { // 성공
                console.log(response.data);
                // 댓글 목록을 HTML로 작성 --> div#comments 영역에 출력.
                makeCommentElement(response.data);
            })
            .catch((error) => { // 실패
                console.log(error);
            });
    }
    
    // 댓글 목록(댓글 객체들의 배열)을 아규먼트로 전달받아서 HTML을 작성.
    function makeCommentElement(data) { // data: 배열
        // 댓글 목록 HTML이 삽입될 div
        const divComments = document.querySelector('div#comments');
        
        // 댓글 목록 HTML 코드
        let htmlStr = '';
        for (let comment of data) {
            // 댓글 최종 수정 시간
            const modifiedTime = new Date(comment.modifiedTime).toLocaleString();
            
            htmlStr += `
            <div class="card card-body my-1">
                <div style="font-size: 0.825rem;">
                    <span>${comment.id}</span>
                    <span class="fw-bold">${comment.username}</span>
                    <span class="text-secondary">${modifiedTime}</span>
                </div>
                <div>${comment.ctext}</div>
                <div class="d-flex justify-content-end mt-2">
                    <button class="btnDeleteComment btn btn-outline-danger btn-sm mx-1"
                        data-id="${comment.id}">삭제</button>
                    <button class="btnModifyComment btn btn-outline-primary btn-sm mx-1"
                        data-id="${comment.id}">수정</button>
                </div>
            </div>
            `;  
        }
        
        // 작성된 HTML 코드를 div 영역에 삽입한다.
        divComments.innerHTML = htmlStr;        
        
        // ==> divComments.innerHTML = htmlStr; 코드 밑에 리스너를 설정하는 코드가 있어야 한다. 
        // 모든 삭제 버튼을 찾아서 클릭 이벤트 리스너를 설정. 
        const btnDeletes = document.querySelectorAll('button.btnDeleteComment'); // 버튼 찾기.
        for (let btn of btnDeletes) {
            btn.addEventListener('click', (e) => {
                alert(e.target.getAttribute('data-id'));
            });
        }
        
        // 모든 수정 버튼을 찾아서 클릭 이벤트 리스너를 설정.
        
        
    }

});