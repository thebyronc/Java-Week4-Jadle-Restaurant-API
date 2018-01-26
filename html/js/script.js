$(document).ready(function() {
  $('#testClick').click(function() {
    // let restaurantId = $('#restaurantId').val();
    let restaurantId = 1;
    let path = 'http://localhost:4567/restaurants/' + restaurantId;
    $('#location').val("");
    $.ajax({
      url: `http://localhost:4567/restaurants/{restaurantId}`,
      type: 'GET',
      data: {
        format: 'json'
      },
      success: function(response) {
        $('.name').text(`Name of Restaurant: ${response.name}`);
        $('.id').text(`Restaurant Id: ${response.id}, ${response.zipcode}`);
      },
      error: function() {
        $('#errors').text("There was an error processing your request. Please try again.")
      }
    });
  });

  $('#getAll').click(function() {
    $.ajax({
      url: `http://localhost:4567/restaurants`,
      type: 'GET',
      data: {
        format: 'json'
      },
      success: function(response) {
        for (i = 0 ; i < response.length; i++ ){
            $('#allRestaurants').append(`<p>Name of Restaurant:${response[i].id}, ${response[i].name}, ${response[i].id}, ${response[i].zipcode} </p>`);
        }
      },
      error: function() {
        $('#errors').text("There was an error processing your request. Please try again.")
      }
    });
  });


  $('#toDelete').click(function() {
    var restaurantId = 2;
    $.ajax({
      url: "http://localhost:4567/restaurants/" + restaurantId + "/delete",
      type: 'GET',
      data: {
        format: 'json'
      },
      success: function() {
        alert("Deleted");
      },
      error: function() {
        $('#errors').text("There was an error processing your request. Please try again.")
      }
    });
  });


  $('#testAdd').click(function() {
    var restaurant = {
      "name": "Pizza Place",
      "address": "Pizza PL NW STREET",
      "zipcode": "97220",
      "phone": "504-231-2131"
    };
    $.ajax({
      type: "POST",
      url: "http://localhost:4567/restaurants/new",
      data: JSON.stringify(restaurant),
      dataType: "json",
      success: function(data){alert(data);},
      failure: function(errMsg) {
          alert(errMsg);
      }
    });
  });

});
