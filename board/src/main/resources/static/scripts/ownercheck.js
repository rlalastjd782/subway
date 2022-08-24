// 로그인 되어있지 않을 때 접근하지 못하게
$.ajax({
	type: "post",
	async: false,
	url: "/login-check",
	success:  function (data) {
		if(data == false){
			alert("잘못된 접근입니다.");
			location.replace('/post/main');
		}
	}
})