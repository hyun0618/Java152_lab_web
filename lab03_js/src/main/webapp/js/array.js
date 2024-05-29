/**
 * array.html에 포함
 * 
 * 자바스크립트 배열: 여러 개 원소(아이템)들을 하나의 변수 이름으로 저장하기 위한 타입. 
 * 자바 배열: "한 개" 타입의 값들 여러 개를 저장하는 타입.
 * 
 * 자바스크립트 배열에서는 다른 타입의 값들이 저장될 수 있음.
 */


// div#output인 요소를 찾음:
const output = document.querySelector('div#output');


// 배열 선언 & 초기화:
const arr = [1, 2, 30, 40, -5]; // 자바: int[] arr = {1, 2, 3};


// 배열 arr의 내용을 output 영역에 입력. 
let html = '';
for (let i=0; i < arr.length; i++) {
    html += `${arr[i]}, `;
}
output.innerHTML += html + '<br/>';


// 자바 향상된 for 문장: for (변수 선언 : 배열) { ... }
// 자바스크립트 향상된 for-of 문장: 배열의 원소(아이템)들을 iteration 함.
 html = '';
 for (let val of arr) { // : 대신에 of 씀.
    html += `${val}, `;
 }
 output.innerHTML += html + '<br/>';

 
 // for-in 문장: 배열의 인덱스들을 iteration 함.
html = '';
for (let idx in arr) {
    html += `${arr[idx]}, `;
} 
output.innerHTML += html + '<br/>';


// 배열 arr의 원소들의 합계, 평균을 output에 출력. 

let sum = 0; 
for (let val of arr) {
    sum += val;
}

const avg = sum / arr.length; // "/" 연산자는 소수점 이하의 계산을 수행한다. 

output.innerHTML += `sum = ${sum}, average = ${avg} <br/>`;


// 배열의 원소들을 ul의 li 요소로 만들기. 
const drama = ['삼식이 삼촌', '동조자', '삼체'];
const list = document.querySelector('ul#list');
html = '';
for (let val of drama) {
    html += `<li> ${val} </li>`
}
list.innerHTML = html;

 
 