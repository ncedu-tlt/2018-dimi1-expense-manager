$( document ).ready( function() {
    var entries = [
        { image: './picture/bootstrap.png', width: '40', height: '40', url: 'https://getbootstrap.com/', target: '_blank', tooltip: 'Bootstrap' },
        { image: './picture/git.png', width: '40', height: '40', url: 'https://git-scm.com/', target: '_blank', tooltip: 'Git' },
        { image: './picture/github.png', width: '40', height: '40', url: 'https://github.com/ncedu-tlt/2018-dimi1-expense-manager', target: '_blank', tooltip: 'GitHub' },
        { image: './picture/google_oauth.png', width: '40', height: '40', url: 'https://developers.google.com/identity/protocols/OAuth2', target: '_blank', tooltip: 'Google OAuth 2.0' },
        { image: './picture/h2.png', width: '40', height: '40', url: 'http://www.h2database.com/html/main.html', target: '_blank', tooltip: 'H2 Database Engine' },
        { image: './picture/heroku.png', width: '40', height: '40', url: 'https://www.heroku.com/', target: '_blank', tooltip: 'Heroku' },
        { image: './picture/idea.png', width: '40', height: '40', url: 'https://www.jetbrains.com/idea/', target: '_blank', tooltip: 'IntelliJ IDEA' },
        { image: './picture/java.png', width: '40', height: '40', url: 'https://www.java.com/ru/', target: '_blank', tooltip: 'Java' },
        { image: './picture/jsp.png', width: '40', height: '40', url: 'https://ru.wikipedia.org/wiki/JavaServer_Pages', target: '_blank', tooltip: 'JSP' },
        { image: './picture/maven.png', width: '40', height: '40', url: 'https://maven.apache.org/', target: '_blank', tooltip: 'Apache Maven' },
        { image: './picture/springboot.png', width: '40', height: '40', url: 'https://spring.io/projects/spring-boot', target: '_blank', tooltip: 'Spring Boot' },
        { image: './picture/tomcat.png', width: '40', height: '40', url: 'http://tomcat.apache.org/', target: '_blank', tooltip: 'Apache Tomcat' },
        { image: './picture/jquery.png', width: '40', height: '40', url: 'https://jquery.com/', target: '_blank', tooltip: 'jQuery' },
        { image: './picture/canvas.png', width: '50', height: '50', url: 'https://canvasjs.com/', target: '_blank', tooltip: 'canvasJS' },
    ];
    var settings = {
        entries: entries,
        width: 400,
        height: 400,
        radius: '65%',
        radiusMin: 75,
        bgDraw: true,
        bgColor: '#ffffff',
        opacityOver: 1.00,
        opacityOut: 0.05,
        opacitySpeed: 6,
        fov: 800,
        speed: 0.7,
        fontFamily: 'Oswald, Arial, sans-serif',
        fontSize: '15',
        fontColor: '#fff',
        fontWeight: 'normal',//bold
        fontStyle: 'normal',//italic
        fontStretch: 'normal',//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
        fontToUpperCase: true,
        tooltipFontFamily: 'Oswald, Arial, sans-serif',
        tooltipFontSize: '16',
        tooltipFontColor: '#55a04d',
        tooltipFontWeight: 'normal',//bold
        tooltipFontStyle: 'normal',//italic
        tooltipFontStretch: 'normal',//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
        tooltipFontToUpperCase: false,
        tooltipTextAnchor: 'left',
        tooltipDiffX: 0,
        tooltipDiffY: 10
    };

    $( '#holder' ).svg3DTagCloud( settings );
});