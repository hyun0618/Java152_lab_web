/**
 * event.html에 포함
 */
 
 // button#btnInput 요소 찾기:
const btnInput = document.querySelector('button#btnInput'); 

// btnInput에 클릭 이벤트 리스너 설정:
// input#itemInput에 입력된 내용을 ul 요소의 li로 추가.
btnInput.addEventListener('click', (e) => {
//    console.log(e); //--> PointerEvent
    
    const itemInput = document.querySelector('input#itemInput');
    const itemList = document.querySelector('ul#itemList');
    

    itemList.innerHTML += `<li> ${itemInput.value} </li>`;
    itemInput.value = '';
});