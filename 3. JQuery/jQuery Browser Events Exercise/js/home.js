$(document).ready(function () {
    $('#mainInfoDiv').show();
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
});

//nav bar
$('#mainButton').on('click', mainClicked);
$('#akronButton').on('click', akronClicked);
$('#minneapolisButton').on('click', minneapolisClicked);
$('#louisvilleButton').on('click', louisvilleClicked);

function mainClicked(){
    $('#mainInfoDiv').show();
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
}
function akronClicked(){
    $('#akronInfoDiv').show();
    $('#mainInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
    hideAkronWeather();
}
function minneapolisClicked(){
    $('#minneapolisInfoDiv').show();
    $('#mainInfoDiv').hide();
    $('#akronInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
    hideMinneapolisWeather();
}
function louisvilleClicked(){
    $('#louisvilleInfoDiv').show();
    $('#mainInfoDiv').hide();
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    hideLouisvilleWeather();
}

//show/hide weather
$('#akronWeatherButton').on('click', akronWeatherClicked);
$('#minneapolisWeatherButton').on('click', minneapolisWeatherClicked);
$('#louisvilleWeatherButton').on('click', louisvilleWeatherClicked);

function akronWeatherClicked() {
    if($('#akronWeatherButton').text() === 'Show Weather') {
        $('#akronWeatherButton').text('Hide Weather')
        $('#akronWeather').show();
    } else {
        hideAkronWeather()
    }
}
function hideAkronWeather() {
    $('#akronWeatherButton').text('Show Weather')
    $('#akronWeather').hide();
}
function minneapolisWeatherClicked() {
    if($('#minneapolisWeatherButton').text() === 'Show Weather') {
        $('#minneapolisWeatherButton').text('Hide Weather')
        $('#minneapolisWeather').show();
    } else {
        hideMinneapolisWeather();
    }
}
function hideMinneapolisWeather() {
    $('#minneapolisWeatherButton').text('Show Weather')
    $('#minneapolisWeather').hide();
}
function louisvilleWeatherClicked() {
    if($('#louisvilleWeatherButton').text() === 'Show Weather') {
        $('#louisvilleWeatherButton').text('Hide Weather')
        $('#louisvilleWeather').show();
    } else {
        hideLouisvilleWeather();
    }
}
function hideLouisvilleWeather() {
    $('#louisvilleWeatherButton').text('Show Weather')
    $('#louisvilleWeather').hide();
}

//Hover on table
    $('tr').hover(
        function() {
            $(this).css('background-color', 'WhiteSmoke')
        },
        function() {
            $(this).css('background-color', '')
        }
    );



