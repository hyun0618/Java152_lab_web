/**
 * for.html에 포함.
 */

// 
 const result = document.getElementById('result');
 
 // result 요소에 추가할 HTML 코드를 저장하기 위한 문자열 변수:
 let html = '';
 
 for (let x = 1; x < 10; x++) {
    html += `2 x ${x} = ${2 * x} <br/>`; // ``: 문자열 템플릿 
 }
 result.innerHTML += html;
 result.innerHTML += '<hr/>';
 
 // result에 구구단 3단 ~ 9단 추가. 

html = ''; 

 for (let y = 3; y < 10; y++) {
    
     for (let x = 1; x < 10; x++) {
        html += `${y} x ${x} = ${y * x} <br/>`; 
     }
     html += '<br/><hr/>';
}
 result.innerHTML += html;
 
 // break를 사용해서 2단은 2x2까지, ..., 9단은 9x9까지 출력. 
 html = ''; 

 for (let y = 2; y < 10; y++) {  
     for (let x = 1; x < 10; x++) {
        if (x > y) {
            break;
        } else {
            html += `${y} x ${x} = ${y * x} <br/>`; 
        }
     }
     html += '<br/><hr/>';
}
 result.innerHTML += html;

