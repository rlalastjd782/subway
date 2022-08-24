// 로그인 되어있는지 체크

let logincheck = "";

$.ajax({
	type: "post",
	async: false,
	url: "/login-check",
	success:  function (data) {
		if(data == true){
			logincheck = true;
		} else{
			logincheck = false;
		}
	}
});

const loginbtn = document.getElementById('loginbtn');
const signupbtn = document.getElementById('signupbtn');

const mypagebtn = document.getElementById('mypagebtn');
const logoutbtn = document.getElementById('logoutbtn');

if(logincheck){
	$("#loginbtn").text("마이페이지");
	$("#signupbtn").text("Logout");
}

$("#loginbtn").click(function(){
	
	console.log()
	
	if($("#loginbtn").text() == "Login"){
		location.replace('/user/login');
	} else{
		location.replace('/user/mypage');
	}

});

$("#signupbtn").click(function(){
	
	if($("#loginbtn").text() == "Sign-up"){
		location.replace('/user/signup');
	} else{
		$.ajax({
				type: "post",
				async: false,
				url: "/user/logout",
				success:  function () {
					location.replace('/post/main');
				}
			})
	}

});