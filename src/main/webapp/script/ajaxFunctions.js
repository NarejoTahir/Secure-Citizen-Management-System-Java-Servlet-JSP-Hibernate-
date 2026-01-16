

	function loadDistricts() {
    var provinceSelect = document.getElementById("provinceSelect");
    var districtSelect = document.getElementById("districtSelect");
    var provinceId = provinceSelect.value;


    districtSelect.innerHTML = '<option value="" disabled selected>Loading...</option>';

    var xhr = new XMLHttpRequest();

    xhr.open("GET", "/Nadra_System/mainController?action=getDistricts&provinceId=" + provinceId, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var districts = JSON.parse(xhr.responseText);
            var options = '<option value="" disabled selected>Select District</option>';
			districts.forEach(function(district) {
  				  options += '<option value="' + district.id + '">' + district.name + '</option>'
			});
            districtSelect.innerHTML = options;
        }
    };
    xhr.send();
	
}

function searchByCnic() {
    var cnic = document.getElementById("cnicInput").value.trim();
    var tableContainer = document.getElementById("tableContainer");
    var tbody = document.getElementById("data-body");

    if(cnic === "") {
        tableContainer.style.display = "none";
        tbody.innerHTML = "";
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/Nadra_System/mainController?action=getUserByCnic&cnic=" + cnic, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            try {
                var data = JSON.parse(xhr.responseText);

                tbody.innerHTML = ""; 
                tableContainer.style.display = "block";

                if(data.length === 0 || data[0].message === "No data found") {
                    tbody.innerHTML = '<tr><td colspan="8" class="text-center">No data found</td></tr>';
                } else {
                    data.forEach(function(u) {
                        var row = "<tr>" +
                            "<td>" + u.id + "</td>" +
                            "<td>" + u.name + "</td>" +
                            "<td>" + u.dob + "</td>" +
                            "<td>" + u.country + "</td>" +
                            "<td>" + u.province + "</td>" +
                            "<td>" + u.district + "</td>" +
                            "<td>" + u.tehsil + "</td>" +
                            "<td>" + u.cnic + "</td>" +
                            "</tr>";
                        tbody.innerHTML += row;
                    });
                }

            } catch(e) {
                console.error("Invalid JSON:", xhr.responseText);
                tbody.innerHTML = '<tr><td colspan="8" class="text-center text-danger">Error loading data</td></tr>';
                tableContainer.style.display = "block";
            }
        }
    };
    xhr.send();
}

let SESSION_TIMEOUT_MINUTES = 2;
let timeRemaining = SESSION_TIMEOUT_MINUTES * 60; // in seconds

// DOM element to show timer (make sure it's on your header)
let timerElement = document.getElementById("sessionTimer");


function updateTimer() {
    if (!timerElement) return; // skip if no timer element on this page

    let minutes = Math.floor(timeRemaining / 60);
    let seconds = timeRemaining % 60;
    timerElement.textContent = `${minutes.toString().padStart(2,'0')}:${seconds.toString().padStart(2,'0')}`;

    if (timeRemaining <= 0) {
        // Auto-logout
        
        window.location.href = '/Nadra_System/mainController?action=logout&errorMsg=Logout Due To Session Expire';
    }

    timeRemaining--;
}

// Reset timer on user activity
function resetTimer() {
    timeRemaining = SESSION_TIMEOUT_MINUTES * 60;
}

// Listen for user activity
//document.addEventListener('mousemove', resetTimer);
//document.addEventListener('keydown', resetTimer);
//document.addEventListener('scroll', resetTimer);


setInterval(updateTimer, 1000);


	





