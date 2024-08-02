/**
 * comments.js
 * /post/details.html에 포함.
 * 댓글 생성, 목록, 수정, 삭제. 
 */
document.addEventListener('DOMContentLoaded', () => {
    
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
    btnMoreComments.addEventListener('click', () => {}); // TODO
    
    
    //------------------------------ 함수 정의(선언) ------------------------------
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
                
            })
            .catch((error) => console.log(error));
    }
    
});