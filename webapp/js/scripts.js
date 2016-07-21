
$(".answerWrite input[type=submit]").click(addAnswer);

$(".article article[type=]")

function quetionList(){
	var queryString = $("form[name=???]").serialize();
	$.ajax({
		type : 'get',
		url : '/api/qna/list',
		data: queryString,
		dataType : 'json',
		error : onError,
		success: onSuccessQL
	});
}

function onSuccessQL(json, status){
	console.log(json);
}

function addAnswer(e) {
	 e.preventDefault(); //submit 이 자동으로 동작하는 것을 막는다.
	 var queryString = $("form[name=answer]").serialize(); //form data들을 자동으로 묶어준다.
	 $.ajax({
		 type : 'post',
		 url : '/api/qna/addanswer',
		 data : queryString,
		 dataType : 'json',
		 error: onError,
		 success : onSuccess });
}

function onSuccess(json, status){
	 var answerTemplate = $("#answerTemplate").html();
	 var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId);
	 $(".qna-comment-slipp-articles").prepend(template);
}
function onError(xhr, status) {
	 alert("error");
} 

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

