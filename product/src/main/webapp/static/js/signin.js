async function handleLoginClick() {
	const inputs = document.querySelectorAll("input");
	
	const loginUser = {
		username : inputs[0].value,
		password : inputs[1].value
	}
	
	console.log(loginUser);
	
	try {
		const response = await fetch("/product/auth/signin", {
			method: "POST",
			headers: {
				"Content-Type" : "application/json"
			},
			body : JSON.stringify(loginUser)
		});
		
		if(!response.ok) {
			throw await response.json();
		}
		alert("로그인 성공");
		location.href = "/product/home.do"; // 페이지 전환(자동 Get 요청)
	} catch (error) {
		alert(error?.errorMessage);
	}
	
}