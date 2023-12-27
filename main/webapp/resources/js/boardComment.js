console.log(bnoVal);

async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method:'post',
           headers:{ 
            'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    if (email == "" || email == null) {
		alert('로그인해주세요');
	}else{
        const cmtText = document.getElementById('text').value;
        const cmtWriter = document.getElementById('exampleFormControlInput1').value;
        if (cmtText == "" || cmtText == null) {
            alert("댓글을 입력해주세요");
            document.getElementById('text').focus();
            return false;
        }else{
            let cmtData = {
                bno : bnoVal,
                writer : cmtWriter,
                content : cmtText
            };
            console.log(cmtData);
            postCommentToServer(cmtData).then(result =>{
                console.log(result);
                if (result == 1) {
                    alert('댓글등록 성공');
                    document.getElementById('text').value='';
                }
                getCommentList(bnoVal);
            })
        }
    }
})

async function spreadCommentListFromSever(bno, page){
    try {
        const resp = await fetch('/comment/list/'+bno+'/'+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bno, page=1){
    spreadCommentListFromSever(bno,page).then(result=>{
        console.log(result);

        let ul = document.getElementById('cmtListArea');

        if (result.cmtList.length > 0) {
            if (page == 1) {
                ul.innerText="";
            }
            for(let cvo of result.cmtList){
                let li = `<li data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                li += `<div><div class="fw-bole">${cvo.writer}</div>`;
                li += `<input type="text" readonly="readonly" value="${cvo.content}"><br>`;
                li +=`<span class="badge rounded-pill text-bg-dark">${cvo.regAt}</span>`;
                if (email != "") {
                    li += `<button type="button" class="modBtn btn btn-dark"  data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                    li += `<button type="button" class="delBtn btn btn-dark">삭제</button></div></li>`;
                }
                ul.innerHTML += li;
            }
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);

            if (result.pgvo.pageNo < result.endPage) {
                moreBtn.style.visibility = 'visible';
                moreBtn.dataset.page= page +1;
            }else{
                moreBtn.style.visibility = 'hidden';
            }
        }else{
            let li = `<li>Comment List Empty</li>`;
            ul.innerHTML = li;
        }
    })
}

async function removeCommentToServer(cno,writer){
    try {
        const url = '/comment/del/'+cno+'/'+writer;
        const config = {
            method : 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function editCommentToServer(cmtDataMod){
    try {
        const url = '/comment/mod/'+cmtDataMod.cno;
        const config = {
            method:'put',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtDataMod)
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
document.addEventListener('click', (e)=>{
    console.log(e.target);
    if (e.target.classList.contains('delBtn')) {
        console.log('삭제버튼 클릭');
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        let writerId= li.dataset.writer;
        removeCommentToServer(cnoVal, writerId).then(result =>{
            if (result == 1) {
                alert('댓글 삭제 성공')
            }else{
                alert('작성자가 아닙니다')
            }
            getCommentList(bnoVal);
        })
    }else if(e.target.classList.contains('modBtn')){
        let li = e.target.closest('li');
        let cmtText = li.querySelector('.fw-bole').nextSibling;
        console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText.value;

        document.getElementById('cmtModBtn').setAttribute('data-cno',li.dataset.cno);
        document.getElementById('cmtModBtn').setAttribute('data-writer',li.dataset.writer);
    }else if (e.target.id == 'cmtModBtn') {
        console.log(e.target);
        let cmtDataMod = {
            cno: e.target.dataset.cno,
            content : document.getElementById('cmtTextMod').value,
            writer : e.target.dataset.writer
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result =>{
            if (parseInt(result)) {
                document.querySelector('.btn-close').click();
                console.log("result>> ",result);
                if (result == "1") {
                    alert('댓글 수정 완료');
                }else if (result == "2") {
                    alert('작성자가 아닙니다');
                }
                getCommentList(bnoVal);
            }
        })
    }else if (e.target.id == 'moreBtn') {
        getCommentList(bnoVal, parseInt(e.target.dataset.page));
    }
})