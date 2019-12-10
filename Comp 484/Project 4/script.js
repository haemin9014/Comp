var answerOne = 0;
var answerTwo;
var answerThree;
var answerFour;
var answerFive;
var wrong = 0;
var correct = 0;
var green = '#00FF00';
var red = '#FF0000';
var map;

function initMap() {
    // setting the map api and location
    var location = { lat: 34.242, lng: -118.528 };

    var mapOption = {
        zoom: 15.7,
        center: location,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDoubleClickZoom: true,
        zoomControl: false,
        disableDefaultUI: true,
        scrollwheel: false,
        draggable: false,
        gestureHandling: 'none',
        clickableIcons: false,
        styles: [
            {
                "featureType": "road",
                "elementType": "labels",
                "stylers": [
                  {
                "visibility": "off"
                  }
                ]
            },
            {
                "featureType": "poi",
                "elementType": "labels",
                "stylers": [
                  {
                "visibility": "off"
                  }
                ]
            }
        ]
    }
  map = new google.maps.Map(document.getElementById('map'), mapOption);
  google.maps.event.addListener(map, 'dblclick', clicked);

}
//starting from firstplace to fifth place, it will load the colored box
function firstplace(c){
    if(c==1){
        var library = new google.maps.Rectangle({
            strokeColor: green,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: green,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.240818342844086,
                south:34.23920274778836,
                east:-118.52866683842586,
                west:-118.52996090111367        
            }
        });
    }
    else if(c==0){
        var library = new google.maps.Rectangle({
            strokeColor: red,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: red,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.240818342844086,
                south:34.23920274778836,
                east:-118.52866683842586,
                west:-118.52996090111367        
            }
        });        
    }
    else{
        console.log("error!");
    }
}

function secondplace(c){
    if(c==1){
        var bthree = new google.maps.Rectangle({
            strokeColor: green,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: green,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.23889709118184,
                south:34.23747795668556,
                east:-118.53191519986666,
                west:-118.53365822307882        
            }
        });
    }
    else if(c==0){
        var bthree = new google.maps.Rectangle({
            strokeColor: red,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: red,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.23889709118184,
                south:34.23747795668556,
                east:-118.53191519986666,
                west:-118.53365822307882        
            }
        });
    }
    else{
        console.log("error!");
    }
}

function thirdplace(c){
    if(c==1){
        var fFour = new google.maps.Rectangle({
            strokeColor: green,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: green,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.2407344254522,
                south:34.24036327761002,
                east:-118.52528642977205,
                west:-118.52597307527986        
            }
        });
    }
    else if(c==0){
        var fFour = new google.maps.Rectangle({
            strokeColor: red,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: red,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.2407344254522,
                south:34.24036327761002,
                east:-118.52528642977205,
                west:-118.52597307527986        
            }
        });        
    }
    else{
        console.log("error!");
    }
}

function fourthplace(c){
    if(c==1){
        var sierra = new google.maps.Rectangle({
            strokeColor: green,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: green,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.23848983636923,
                south:34.238118678630975,
                east:-118.53008818396529,
                west:-118.53151429386617        
            }
        });
    }
    else if(c==0){
        var sierra = new google.maps.Rectangle({
            strokeColor: red,
            strokeOpacity: 1,
            strokeWeight: 3,
            fillColor: red,
            fillOpacity: 5,
            map: map,
            bounds: {
                north:34.23848983636923,
                south:34.238118678630975,
                east:-118.53008818396529,
                west:-118.53151429386617        
            }
        });        
    }
    else{
        console.log("error!");
    }
}

function fifthplace(c){
    if(c==1){
        var art = new google.maps.Rectangle({
                strokeColor: green,
                strokeOpacity: 1,
                strokeWeight: 3,
                fillColor: green,
                fillOpacity: 5,
                map: map,
                bounds: {
                    north:34.24386569302971,
                    south:34.243210749509295,
                    east:-118.52918402361229,
                    west:-118.53034603908702        
                }
            });
    }
    else if(c==0){
        var art = new google.maps.Rectangle({
                strokeColor: red,
                strokeOpacity: 1,
                strokeWeight: 3,
                fillColor: red,
                fillOpacity: 5,
                map: map,
                bounds: {
                    north:34.24386569302971,
                    south:34.243210749509295,
                    east:-118.52918402361229,
                    west:-118.53034603908702        
                }
            });        
    }
    else{
        console.log("error!");
    }
}

//called function when it double clicked the place
function clicked(e){
    if(answerOne == 0){
        //check lat
       if(34.23920274778836 <= e.latLng.lat() && e.latLng.lat() <= 34.240818342844086){
           //check lng
            if(-118.52996090111367 <= e.latLng.lng()&& e.latLng.lng() <=-118.52866683842586){
                //change if it is correct
                document.getElementById('one').innerHTML = "Correct!!"
                //open next question
                document.getElementById('two').innerHTML = "where is b3 parking lot?"
                //text color will changed to green
                document.getElementById("one").style.background = "green";
                //open green box
                firstplace(1);
                //close first question         
                answerOne = 1
                //open second question
                answerTwo = 0  
                //increase number of correct
                correct++;
            }
            else{
                //open red box
                firstplace(0);
                //change if it is wrong
                document.getElementById('one').innerHTML = "Wrong!!"
                //text color will changed to red
                document.getElementById("one").style.background = "red";
                //open next question
                document.getElementById('two').innerHTML = "where is b3 parking lot?"
                //close first qeustion
                answerOne = 1
                //open next question
                answerTwo = 0
                //increase number of wrong
                wrong++;                
            }
        }
        else{
            //open red box
            firstplace(0);
            //change if it is wrong
            document.getElementById('one').innerHTML = "Wrong!!"
            //text color will changed to red
            document.getElementById("one").style.background = "red";
            //open next question
            document.getElementById('two').innerHTML = "where is b3 parking lot?"
            // close first question
            answerOne = 1
            //open next question
            answerTwo = 0
            //increase number of wrong
            wrong++;
        }
        //it will be repeated
    }
    else if(answerTwo == 0){
        if(34.23747795668556 <= e.latLng.lat() && e.latLng.lat() <= 34.23889709118184){
            if( -118.53365822307882 <= e.latLng.lng() && e.latLng.lng() <= -118.53191519986666){
                document.getElementById('two').innerHTML = "Correct!!";
                document.getElementById("two").style.background = "green";
                document.getElementById('three').innerHTML = "where is F4 parking lot?";
                secondplace(1);
                answerTwo = 1;
                answerThree = 0;
                correct++;
            } 
            else{
                answerTwo = 1;
                answerThree = 0;
                secondplace(0);
                wrong++;
                document.getElementById('two').innerHTML = "Wrong!!"
                document.getElementById("two").style.background = "red";
                document.getElementById('three').innerHTML = "where is F4 parking lot?"                 
            }
        }
        else{
            answerTwo = 1;
            answerThree = 0;
            secondplace(0);
            wrong++;
            document.getElementById('two').innerHTML = "Wrong!!"
            document.getElementById("two").style.background = "red";
            document.getElementById('three').innerHTML = "where is F4 parking lot?" 
        }
    }
    else if(answerThree == 0){
        if(34.24036327761002 <= e.latLng.lat() && e.latLng.lat() <=34.2407344254522){
            if(-118.52597307527986 <= e.latLng.lng() && e.latLng.lng() <=-118.52528642977205){
                document.getElementById('three').innerHTML = "Correct!!"
                document.getElementById("three").style.background = "green";
                document.getElementById('four').innerHTML = "where is sierra hall?"
                thirdplace(1);
                answerThree = 1;
                answerFour = 0;
                correct++;
            }
            else{
                answerThree= 1;
                answerFour = 0;
                thirdplace(0);
                document.getElementById('three').innerHTML = "Wrong!!"
                document.getElementById("three").style.background = "red";
                document.getElementById('four').innerHTML = "where is sierra hall?" 
                wrong++;                
            }
        }
        else{
            answerThree= 1;
            answerFour = 0;
            thirdplace(0);
            document.getElementById('three').innerHTML = "Wrong!!"
            document.getElementById("three").style.background = "red";
            document.getElementById('four').innerHTML = "where is sierra hall?" 
            wrong++;
        }
    }
    else if(answerFour == 0){
        if(34.238118678630975 <= e.latLng.lat() && e.latLng.lat() <=34.23848983636923){
            if(-118.53151429386617 <= e.latLng.lng() && e.latLng.lng() <=-118.53008818396529){
                document.getElementById('four').innerHTML = "Correct!!"
                document.getElementById("four").style.background = "green";
                document.getElementById('five').innerHTML = "where is CSUN art Galleries?" 
                fourthplace(1);
                answerFour = 1;
                answerFive = 0;
                correct++;
            }
            else{
                answerFour = 1;
                answerFive = 0;
                fourthplace(0);
                wrong++;
                document.getElementById('four').innerHTML = "Wrong!!"
                document.getElementById("four").style.background = "red";
                document.getElementById('five').innerHTML = "Where is CSUN art Galleries?"                 
            }
        }
        else{
            answerFour = 1;
            answerFive = 0;
            fourthplace(0);
            wrong++;
            document.getElementById('four').innerHTML = "Wrong!!"
            document.getElementById("four").style.background = "red";
            document.getElementById('five').innerHTML = "Where is CSUN art Galleries?" 
        }
    }
    else if(answerFive == 0){
        if(34.243210749509295 <= e.latLng.lat() && e.latLng.lat() <= 34.24386569302971){
            if(-118.53034603908702 <= e.latLng.lng() && e.latLng.lng() <=-118.52918402361229){
                document.getElementById('five').innerHTML = "Correct!!"
                document.getElementById("five").style.background = "green"; 
                correct++;
                fifthplace(1);
                answerFive = 1;
                document.getElementById('result').innerHTML = "you got " + correct + " correct and you got " + wrong + " wrong in this quiz!"
            }
            else{
                answerFive = 1;
                fifthplace(0);
                document.getElementById('five').innerHTML = "Wrong!!"
                document.getElementById("five").style.background = "red";
                wrong++;
                document.getElementById('result').innerHTML = "you got " + correct + " correct and you got " + wrong + " wrong in this quiz!"
            }
        }
        else{
            answerFive = 1;
            fifthplace(0);
            document.getElementById('five').innerHTML = "Wrong!!"
            document.getElementById("five").style.background = "red";
            wrong++;
            document.getElementById('result').innerHTML = "you got " + correct + " correct and you got " + wrong + " wrong in this quiz!"
        }
    }
    else{
        console.log("finished all problems!");
    }
}