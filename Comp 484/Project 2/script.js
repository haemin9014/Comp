$(function() { // Makes sure that your function is called once all the DOM elements of the page are ready to be used.
    
    // Called function to update the name, happiness, and weight of our pet in our HTML
    checkAndUpdatePetInfoInHtml();
  
    // When each button is clicked, it will "call" function for that button (functions are below)
    $('.treat-button').click(clickedTreatButton);
    $('.play-button').click(clickedPlayButton);
    $('.exercise-button').click(clickedExerciseButton);
    $('.dance-button').click(clickedDanceButton);
   })
    var weight = 14;
    var dance = 50;
    var happiness = 70;
    // Add a variable "pet_info" equal to a object with the name (string), weight (number), and happiness (number) of your pet
    var pet_info = {
      name:"Riya", weight:"14", happiness:"70", text:" ", dance: "50",screenshot:"https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee.jpg?v=1572652395611"
    };


    var normalSound = document.getElementById("normalAudio");
    function normal(){
      normalSound.play();
    }

    var playSound = document.getElementById("playAudio");
    function playS(){
      playSound.play();
    }

    var danceSound = document.getElementById("danceAudio");
    function danceS(){
      danceSound.play();
    }

    function clickedTreatButton() {
      normal();
      // Increase pet happiness
      ++pet_info['happiness'];
      // Increase pet weight
      ++pet_info['weight'];
      // Increase charm
      --pet_info['dance'];
      document.getElementById('text').innerHTML = "let's eat!"; //first way
      //pet_info['text'] ="let's eat!"; //second way
      document.getElementById('screenshot').src = "https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_food.gif?v=1572652489238"; //first way
      //pet_info['screenshot'] ="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_food.gif?v=1572652489238"; //second way     
      checkAndUpdatePetInfoInHtml();
    }
    
    function clickedPlayButton() {
      playS();
      // Increase pet happiness
      ++pet_info['happiness'];
      // Decrease pet weight
      --pet_info['weight'];
      document.getElementById('text').innerHTML = "let's play!"; //first way
      //pet_info['text'] ="let's play!";//second way
      document.getElementById('screenshot').src = "https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_play.gif?v=1572652572998" //first way
      //pet_info['screenshot'] ="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_play.gif?v=1572652572998";//second way
      checkAndUpdatePetInfoInHtml();
    }
    
    function clickedExerciseButton() {
      normal();
      // Decrease pet happiness
      --pet_info['happiness'];
      // Decrease pet weight
      --pet_info['weight'];
      // Increase charm
      ++pet_info['dance'];
      document.getElementById('text').innerHTML = "let's exercise!"; //first way
      //pet_info['text'] ="let's exercise!";//second way
      document.getElementById('screenshot').src = "https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_exercise.gif?v=1572652579071" //first way
      //pet_info['screenshot'] ="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_exercise.gif?v=1572652579071"//second way
      checkAndUpdatePetInfoInHtml();
    }

    function clickedDanceButton() {
      danceS();
      // increase pet happiness
      ++pet_info['happiness'];
      // Decrease pet weight
      --pet_info['weight'];
      // Increase charm
      ++pet_info['dance'];
      document.getElementById('text').innerHTML = "let's dance!"; //first way
      //pet_info['text'] ="let's exercise!";//second way
      document.getElementById('screenshot').src = "https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_dance.gif?v=1572659551664" //first way
      //pet_info['screenshot'] ="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee_dance.gif?v=1572659551664"//second way
      checkAndUpdatePetInfoInHtml();
    }
  
    function checkAndUpdatePetInfoInHtml() {
      checkWeightAndHappinessBeforeUpdating();  
      updatePetInfoInHtml();
      //change();//we don't need this function when we decide to use first way
    }
    
    function checkWeightAndHappinessBeforeUpdating() {
      // Add conditional so if weight is lower than zero, set it back to zero
      if(pet_info['weight'] < 0 ){
        pet_info['weight'] = 0;
      }

      if(pet_info['dance'] < 0){
        pet_info['dance'] = 0;
      }
      if(pet_info['dance'] > 100){
        pet_info['dance'] = 100;
      }

      if(pet_info['happiness'] < 0){
        pet_info['happiness'] = 0;
      } 
    }
    
    /*
    //function for changing image
    function change() {//we don't need this function when we decide to use first way
      document.getElementById('screenshot').src = pet_info['screenshot'];
      document.getElementById('text').innerHTML = pet_info['text'];
    }
    */
    //function for basic image
    function basicImageText() {
      pet_info['screenshot'] ="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Feevee.jpg?v=1572652395611";
      document.getElementById('screenshot').src = pet_info['screenshot'];
      pet_info['text'] ="eevee..";
      document.getElementById('text').innerHTML = "eevee!"
    }

    // Updates your HTML with the current values in your pet_info dictionary
    function updatePetInfoInHtml() {
      $('.name').text(pet_info['name']);
      $('.weight').text(pet_info['weight']);
      $('.happiness').text(pet_info['happiness']);
      $('.text').text(pet_info['text']);
      $('.dance').text(pet_info['dance']);
      //get back to basic image and text!
      setTimeout(basicImageText, 2900);
    }
  