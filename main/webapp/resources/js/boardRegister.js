console.log('boardRegister in@@');

document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('files').click();
})

const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$");
const regExpImg = new RegExp("\.(jpg|jpeg|PNG|png|gif)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    console.log(fileName);
    console.log(fileSize);
    if (!regExpImg.test(fileName)) {
        console.log("이미지 X");
        return 0;
    }else if (regExp.test(fileName)) {
        console.log("실행 파일");
        return 0;
    }else if (fileSize > maxSize) {
        console.log("크기 초과");
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change', (e)=>{
if (e.target.id == 'files') {
    document.getElementById('regBtn').disabled = false;

    const fileObj = document.getElementById('files').files;
    console.log('files>> ',fileObj);

    let div = document.getElementById('fileZone');

    div.innerHTML="";

    let isOk = 1;
    let ul= `<ul class="list-group list-group-flush">`;
        for(let file of fileObj){
            let vaildResult = fileValidation(file.name,file.size);
            isOk *= vaildResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul +=  `${vaildResult ? '<div class="mb-3">업로드 가능</div>':'<div class="mb-3 text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded text-bg-${vaildResult ? 'success' : 'danger'}">${file.size}Byte</span></li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;

        if (isOk == 0) {
            document.getElementById('regBtn').disabled = true;
        }
}
})