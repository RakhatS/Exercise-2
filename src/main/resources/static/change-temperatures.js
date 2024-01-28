

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/temp-changed', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}




function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendTemp(temp_value) {
    stompClient.publish({
        destination: "/app/temperature",
        body: temp_value
    });
}


$(document).ready(function () {
    function loadTemperatureData() {
        $.ajax({
            type: 'GET',
            url: '/api/v1/temperature',
            success: function (data) {
                displayTemperatureData(data);
            },
            error: function (error) {
                console.error('Error fetching temperature data:', error);
            }
        });
    }

    function displayTemperatureData(data) {
        const tableBody = $('#temperatureTableBody');
        tableBody.empty();

        data.forEach(function (temperature) {
            tableBody.append(`
                <tr>
                    <td>${temperature.id}</td>
                    <td>${temperature.value}</td>
                    <td>
                        <button onclick="deleteTemperature(${temperature.id})">Delete</button>
                    </td>
                </tr>
            `);
        });
    }

    $('#temperatureForm').submit(function (event) {
        event.preventDefault();

        const temperatureValue = $('#temperature').val();
        const newTemperature = { value: temperatureValue };

        $.ajax({
            type: 'POST',
            url: '/api/v1/temperature/save_temperature',
            contentType: 'application/json',
            data: JSON.stringify(newTemperature),
            success: function () {
                loadTemperatureData();
                sendTemp(JSON.stringify(newTemperature))
                $('#temperatureForm')[0].reset();
            },
            error: function (error) {
                console.error('Error saving temperature:', error);
            }
        });
    });

    window.deleteTemperature = function (temperatureId) {
        $.ajax({
            type: 'DELETE',
            url: `/api/v1/temperature/delete_temperature/${temperatureId}`,
            success: function () {
                loadTemperatureData();
            },
            error: function (error) {
                console.error('Error deleting temperature:', error);
            }
        });
    };

    loadTemperatureData();
    connect();
});







