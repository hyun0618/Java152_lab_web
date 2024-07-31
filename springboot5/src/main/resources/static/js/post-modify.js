/**
 * /post/modify.html 파일에 포함
 * 포스트 업데이트, 삭제 버튼 이벤트 처리.
 */

 document.addEventListener('DOMContentLoaded', () => {
    const updateForm = document.querySelector('form#updateForm');
    const id = document.querySelector('input#id'); // 번호
    const title = document.querySelector('input#title'); // 제목
    const content = document.querySelector('textarea#content'); // 내용
    const btnDelete = document.querySelector('button#btnDelete'); // 삭제 버튼
    const btnUpdate = document.querySelector('button#btnUpdate'); // 업데이트 버튼
    
    // 삭제 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    btnDelete.addEventListener('click', () => {
        const check = confirm('삭제하시겠습니까?');
  
        if (check) {
            location.href = `delete?id=${id.value}`; 
        }
    });
    
    // 업데이트 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    btnUpdate.addEventListener('click', () => {
        // trim(): 문자열 시작과 끝에 있는 모든 공백을 제거.
        if (title.value.trim() === '' || content.value.trim() === '') { 
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return; // 함수 종료
        }

        const check = confirm('변경된 내용을 저장할까요?');
        if (check) {
            updateForm.submit();
        }

    });
    
 });