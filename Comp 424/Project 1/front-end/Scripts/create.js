$(document).ready(function() {
  var main_password = $('#main_password');
  var check_password = $('#check_password');
  var level_check = $('#password_level');

  passwordStrengthCehck(main_password, check_password, level_check);
});

function passwordStrengthCehck(main_password, check_password, level_check){
	
	var weak_password = /(?=.{5,}).*/;

	var mid_password = /^(?=\S*?[a-z])(?=\S*?[0-9])\S{5,}$/;

	var str_password = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])\S{5,}$/;

	var perfect_password = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])(?=\S*?[^\w\*])\S{5,}$/;

	$(main_password).on('keyup', function(e){
		if(perfect_password.test(main_password.val())){
			level_check.removeClass().addClass('perfect_password').html("perfect password!");
		}
		else if(str_password.test(main_password.val())){
			level_check.removeClass().addClass('str_password').html("strong level password!");
		}
		else if(mid_password.test(main_password.val())){
			level_check.removeClass().addClass('mid_password').html("mid level password!");
		}
		else{
			level_check.removeClass().addClass('weak_password').html("very week password!! please type more with capital, chars");
		}

	});

	$(check_password).on('keyup', function(e){
		if(main_password.val() !== check_password.val()){
			level_check.removeClass().addClass('weak_password').html("passwords do not match!");
			$('.submit_button_create').prop('disabled', true);
		}
		else{
			level_check.removeClass().addClass('perfect_password').html("passwords match!");
			$('.submit_button_create').prop('disabled', false);
		}
	});

}