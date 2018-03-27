$(document).ready(function() {
    $('#uploadget').click(function(event){
      getData();
    })
    $('#upload_file_button').click(function(event){
      postData();
    })
});

function getData() {

  $.ajax({
    type : "GET",
    contentType : "application/json",
    url : "http://localhost:8080/api/tracks",
    timeout : 600000,
    success : function(data) {
      console.log("SUCCESS: ", data);
    },
    error : function(e) {
      console.log("ERROR: ", e);
    },
    done : function(e) {
      console.log("DONE");
    }
  });
}

function postData() {

  var msg = $('#msg').val();

  var data = {
    "title" : msg,
    "artist" : "kallek",
    "album" : "hejsan",
    "length" : 4,
    "year" : 1981
  };

  console.log(data);

  $.ajax({
    type : "POST",
    enctype: 'multipart/form-data',
    headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
    url : "http://localhost:8080/api/tracks",
    data : JSON.stringify(data),
    contenType : "application/json",
    success : function(data) {
      console.log("SUCCESS: ", data);
    },
    error : function(e) {
      console.log("ERROR: ", e);
    },
    done : function(e) {
      console.log("DONE");
    }
  });
}
