/**
 * /post/modify.jsp에 포함.
 */

// HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 때, 이벤트 리스너를 실행.
 document.addEventListener('DOMContentLoaded', () => { 
    
    // form 요소 찾기.
    const modifyForm = document.querySelector('form#modifyForm');
    
    // 글 번호가 입력된 input#id 요소 찾기. 
    const inputId = document.querySelector('input#id');
    
    // 글 제목이 입력된 input#title 요소 찾기. 
    const inputTitle = document.querySelector('input#title');
    
    // 글 내용이 작성된 textarea#content 요소 찾기.
    const textareaContent = document.querySelector('textarea#content');
    
    // 삭제 버튼 찾기
    const btnDelete = document.querySelector('button#btnDelete');
    
    // 업데이트 버튼 찾기.
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    // 삭제 버튼에 클릭 이벤트 리스너를 설정.
    btnDelete.addEventListener('click', () => {
        const result = confirm('삭제하시겠습니까?');
//      console.log(result); --> confirm()의 리턴값을 확인하기 위해 출력해 봄. ==> true/false
        if (result) { // --> result 값이 true 이면(--> '삭제하시겠습니까'의 질문에 '확인'클릭) 
            // 삭제(GET 방식) 요청을 서버로 보냄. 
            location.href = `delete?id=${inputId.value}`; // ${  } ==> 자바스크립트의 문자열 탬플릿    
        }
    });
    
    // 업데이트 버튼에 클릭 이벤트로 리스너 설정.
    btnUpdate.addEventListener('click', () => {
        // title, content가 비어있는지 체크:
        const title = inputTitle.value; // input 요소에 입력된 값.
        const content = textareaContent.value; // textarea 요소에 입력된 값.
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다!');
            return; // 함수 종료
        }
        
        const result = confirm('변경된 내용으로 업데이트 할까요?'); 
        if (result) {
            modifyForm.method = 'post'; // 폼 제출 방식 설정.
            modifyForm.action = 'update'; // 폼 제출 요청 주소 설정. 
            modifyForm.submit(); // 폼 제출. (--> 서버로 요청을 보냄)
            
        }
           
    });
    
 });