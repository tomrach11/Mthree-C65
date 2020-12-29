$(document).ready(function () {
    $('H1').addClass('text-center');
    $('H2').addClass('text-center');
    $('H1').addClass('page-header');
    $('#yellowHeading').text('Yellow Team');
    $('.orange').css('background-color', 'orange');
    $('.blue').css('background-color', 'lightblue');
    $('.red').css('background-color', 'red');
    $('.yellow').css('background-color', 'yellow');
    $('#yellowTeamList').html('<li>Joseph Banks</li><li>Simon Jones</li>')
    $('#oops').hide();
    $('#footerPlaceholder').remove();
    $('#footer').css({ 'text-align' : 'center', 'height' : '24px', 'font-family' : 'Courier' });
    $('#footer').html('<p> Tom Rachtawarn : tomrach11@gmail.com</p>');
});