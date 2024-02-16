async function handleSignupSubmit() {
	const inputs = document.querySelectorAll("input");
	
	const user = {
		username : inputs[0].value,
		password : inputs[1].value,
		name : inputs[2].value,
		email : inputs[3].value,
	}
	
	try {
		const response = await fetch("/product/user", {
			method: "POST",
			headers : {
				"Content-Type" : "application/json"
			},
			body: JSON.stringify(user)
		});

		const responseData = await response.json();
		console.log(responseData)
	} catch(error) {
		console.log(error);
	}
}