/**
 * while.html에 포함 
 */

 // <ul id="list"></ul> 
 const list = document.querySelector('#list'); // document.getElementById('list')
 
 // <tbody id="tableBody"></tbody>
 const tableBody = document.querySelector('#tableBody');
 
 let html =''; // <ul></ul> 태그의 컨텐트로 작성할 HTML 코드.
 
 let n = 1;
 while (n <= 5) {
    html += `<li> 아이템 ${n} </li>`;
    n++;
 }
 list.innerHTML = html;
 
 html = '';
 n = 1;
 while (n <= 5) {
    html += `
        <tr>
            <td> ${n} </td> 
            <td> 제목${n} </td>
        </tr> 
    `;
    n++;
 }
 tableBody.innerHTML = html;
  