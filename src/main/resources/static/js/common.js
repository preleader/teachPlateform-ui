const BASEURL = "http://127.0.0.1:8080/api/";
const Box = {},func={};
func.run = function(node,time){
	console.log(node)
	$(document.body).append(node);
	setTimeout(()=>{$("#tipmsg").remove();},time);
};
Box.alert = function(msg,time,type){
	let content = {
		success:`<div id='tipmsg' class="alert alert-success">
			    	<strong>成功!</strong><br/>/**&nbsp;&nbsp;**/ ${msg}
			     </div>`,
	    info:`<div id='tipmsg' class="alert alert-info">
			      <strong>信息!</strong><br/> /**&nbsp;&nbsp;**/${msg}
			  </div>`,
	    warn: `<div id='tipmsg' class="alert alert-warning">
			     <strong>警告!</strong><br/>/**&nbsp;&nbsp;**/${msg}
			   </div>`,
		danger:`<div id='tipmsg' class="alert alert-danger">
			       <strong>错误!</strong><br/>/**&nbsp;&nbsp;**/${msg}
			    </div>`,
		primary:`<div id='tipmsg' class="alert alert-primary">
				    <strong>提示!</strong><br/>/**&nbsp;&nbsp;**/${msg}
				</div>`,
		second:`<div id='tipmsg' class="alert alert-secondary">
				    <strong>提示!</strong><br/>/**&nbsp;&nbsp;**/${msg}
			    </div>`,
	};
	if(!Object.keys(content).includes(type))
		console.log('类型缺失');
	else
	   func.run(content[type],time);
}

export {BASEURL,Box};