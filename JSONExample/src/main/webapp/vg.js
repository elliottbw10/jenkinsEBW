window.onload = function(){
    console.log("in onload");
    document.getElementById("vgSubmitGet").addEventListener("click",getVG,false);
    document.getElementById("vgFormSubmit").addEventListener("click",postVG,false);
}

function getVG(){
    console.log("in getVG");
    let vgid = document.getElementById("vgIDInput").value;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        console.log( "in ORSC" + xhr.readyState);
        if(xhr.readyState == 4 && xhr.status == 200){
            var vg = JSON.parse(xhr.responseText);
            loadVG(vg);
        }
    }

    xhr.open("GET", "http://localhost:8080/JSONExample/vg?vgid="+vgid,true);
    xhr.send();
}

function loadVG(vg){
    document.getElementById("vgID").innerHTML = vg.vgID;
    document.getElementById("vgName").innerHTML = vg.vgName;
    document.getElementById("vgMetaScore").innerHTML = vg.vgMetaScore;
}

function jsonBuilder(){
    var elements = document.getElementById("vgForm").elements;
    var obj = {};
    for(var i = 0; i<elements.length-1;i++){
        var item = elements.item(i);
        obj[item.name] = item.value;
        console.log(obj);
    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
}

function postVG(){
    console.log("in postVG");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        console.log( "in iORSC" + xhr.readyState);
        if(xhr.readyState == 4 && xhr.status == 200){
            console.log(xhr.responseText);
        }
    }
    xhr.open("POST","http://localhost:8080/JSONExample/vg",true);
    var payload = jsonBuilder();
    xhr.send(payload);
}
