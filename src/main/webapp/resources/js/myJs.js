
function showsignup(){
$("#signup").removeClass('d-none');
}


function hidelogin(){
$("#login").addClass('d-none');
showsignup();

}

function hidesignup(){
$("#signup").addClass('d-none');
showslogin();
}

function showslogin(){
$("#login").removeClass('d-none');
}

$(document).ready(function(){
	$('#lo').onClick(function(){$("#login").addClass('d-none');
$("#signup").removeClass('d-none');
})
})
