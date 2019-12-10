const testWrapper = document.querySelector(".test-wrapper");
const testArea = document.querySelector("#test-area");
const originText = document.querySelector("#origin-text p").innerHTML;
const resetButton = document.querySelector("#reset");
const theTimer = document.querySelector(".timer");
const countMistake = document.querySelector(".mistakeCount");

let mili = 0;
let second = 0;
let minute = 0;
let check = 0;

let  status = "stopped";

var listText = ['the dog is jumping and cat is swimming' , 'the dog is singing and cat is dancing', 'the dog is drinking and cat is eating'];
var saveText = 'the dog is jumping and cat is swimming';
var checkText = '';
var mistake = 0;

function setTime(num){
	if(num <= 9){
		return("0" + num);
	}
	else{
		return num;
	}
}

function stopWatch(){

	mili++;
	
	if(mili / 100 === 1){
		mili = 0;
		second++;
		if(second / 60 ===1){
			second = 0;
			minute ++;
		}
	}

	theTimer.innerHTML = setTime(minute) + ":" + setTime(second) + ":" + setTime(mili);
}

function start(){
	if(status === "stopped" ){		
		check = setInterval(stopWatch, 10);
		status ="started";
	}
}

function textCheck(){
    start();

    checkText = document.getElementById('test-area').value;
    if (checkText.charAt(checkText.length-1) !== saveText.charAt(checkText.length-1)) {
        //could not fix mistake error.....
        mistake++;
    }
   
    if (checkText == saveText) {
        status = "stopped";
        clearInterval(check);
        if(mistake == 0){
        	countMistake.innerHTML = 'great! you made ' + mistake + ' times of mistake while you were typing' ;
        }
        else{
       		countMistake.innerHTML = 'you made ' + mistake + ' times of mistake while you were typing' ;
    	}
    	mistake = 0;

    }

}

function reset(){
	clearInterval(check);
	status = "stopped";
	mili = 0;
	second = 0;
	minute = 0;
	mistake = 0;
	saveText = listText[Math.floor(Math.random() * listText.length)];
	document.querySelector("#origin-text p").innerHTML = saveText;
	document.querySelector("#test-area").value = '';
	document.querySelector(".mistakeCount").innerHTML = "";
	document.querySelector(".timer").innerHTML = "00:00:00";
}




