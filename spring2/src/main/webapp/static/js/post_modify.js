/**
 * '/post/modify.jsp'에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {

    // modify.jsp에 있는 element 찾기.         ('태그이름#id')
    const modifyForm = document.querySelector('form#modifyForm'); // form
    const inputId = document.querySelector('input#id'); // 번호
    const inputTitle = document.querySelector('input#title'); // 제목
    const textContent = document.querySelector('textarea#content'); // 내용
    const btnDelete = document.querySelector('button#btnDelete'); // 삭제 버튼
    const btnUpdate = document.querySelector('button#btnUpdate'); // 업데이트 버튼

    // 삭제 버튼 클릭 이벤트 리스너:
    btnDelete.addEventListener('click', () => {
        const result = confirm('삭제하시겠습니까?');
        // 삭제 확인:
        if (result) {
            location.href = `delete?id=${inputId.value}`; // GET 방식은 deledte 요청을 서버로 보냄.
        }
    });

    // 업데이트 버튼 클릭 이벤트 리스너:
    btnUpdate.addEventListener('click', () => {
        // 제목과 내용이 비어있는지 체크:
        if (inputTitle.value === '' || textContent.value === '') {
            alert('제목과 내용은 반드시 입력하세요.');
            returnl; // 함수 종료
        }

        // 업데이트 내용 저장 확인: 
        const result = confirm('업데이트 하시겠습니까?');
        if (result) {
            modifyForm.method = 'post'; // 요청 방식 
            modifyForm.action = 'update'; // 요청 주소
            modifyForm.submit(); // 폼 양식 데이터 제출(서버로 요청 보냄).
        }

    });

});