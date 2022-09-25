function getUserLocation() {
	console.log("call getUserLocation")
	if (!navigator.userAgent.match(/bot|spider/i)) {
		// Let's check if we have the value in localstorage
		if (localStorage.getItem('country')) {
			// Already have the value in localStorage no need to make call to IPinfo
			window.location.href = '/welcome?country=' + localStorage.getItem('country')
		} else {
			// No cached data, let's get it from IPinfo
			fetch('https://ipinfo.io?token=08e0e8bdbb5432')
				.then(res => res.json())
				.then(data => {
					// We have the data, let's cache it in localstorage before redirecting
					localStorage.setItem('ipinfo', "IP: " + data.ip + ", loc: " + data.loc + ", Postal: " + data.postal + ", Region: " + data.region)
					console.log(data);
					$("#track-ip").text('IP: '+data.ip);
					$("#track-loc").text(', Location: '+data.loc);
					$("#track-region").text(', Address: '+data.city+', '+data.region+', '+data.country);
					return data;
					/*window.location.href = '/welcome?country=' + data.country*/
				})
		}
	}
}

function getUpdatSymbols(id, flag) {
	$(".display").show();
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: 'http://localhost:8080/updatSymbols',
		data: {id: id, flag: flag},
	}).done(function(data) {
		console.log(data);
		$("#symbol-list").html('');
		$("#symbol-list").html(data);
		getUpdatStocks();
	});
}

function getUpdatStocks() {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: 'http://localhost:8080/updatStocks',
	}).done(function(data) {
		$(".display").hide();
		$("#stock-list").html('');
		$("#stock-list").html(data);
	});
}