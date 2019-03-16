<!DOCTYPE html>
<html>

<head>

    <meta charset='utf-8'>
    <title>SVG3DTagCloud - Example 3</title>
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
    <script src='js/jquery.svg3dtagcloud.min.js'></script>

    <link href='https://fonts.googleapis.com/css?family=Oswald&subset=latin,latin-ext' rel='stylesheet' type='text/css'>

    <style type='text/css'>

        html, body, div, span, applet, object, iframe,
        h1, h2, h3, h4, h5, h6, p, blockquote, pre,
        a, abbr, acronym, address, big, cite, code,
        del, dfn, em, font, img, ins, kbd, q, s, samp,
        small, strike, strong, sub, sup, tt, var,
        b, u, i, center,
        dl, dt, dd, ol, ul, li,
        fieldset, form, label, legend,
        table, caption, tbody, tfoot, thead, tr, th, td {
            margin: 0;
            padding: 0;
            border: 0;
            outline: 0;
            font-size: 100%;
            vertical-align: baseline;
            background: transparent;
            box-sizing: border-box;
        }

        body {
            overflow: hidden;
            background-color: #000;
        }

    </style>

    <script>

    	$( document ).ready( function() {

            var entries = [

                { image: './picture/bootstrap.png', width: '50', height: '50', url: 'https://getbootstrap.com/', target: '_top', tooltip: 'Bootstrap' },
                { image: './picture/git.png', width: '50', height: '50', url: 'https://git-scm.com/', target: '_top', tooltip: 'Git' },
                { image: './picture/github.png', width: '50', height: '50', url: 'https://github.com/ncedu-tlt/2018-dimi1-expense-manager', target: '_top', tooltip: 'GitHub' },
                { image: './picture/google_oauth.png', width: '50', height: '50', url: 'https://developers.google.com/identity/protocols/OAuth2', target: '_top', tooltip: 'Google OAuth 2.0' },
                { image: './picture/h2.png', width: '50', height: '50', url: 'http://www.h2database.com/html/main.html', target: '_top', tooltip: 'H2 Database Engine' },
                { image: './picture/heroku.png', width: '50', height: '50', url: 'https://www.heroku.com/', target: '_top', tooltip: 'Heroku' },
                { image: './picture/idea.png', width: '50', height: '50', url: 'https://www.jetbrains.com/idea/', target: '_top', tooltip: 'IntelliJ IDEA' },
                { image: './picture/java.png', width: '50', height: '50', url: 'https://www.java.com/ru/', target: '_top', tooltip: 'Java' },
                { image: './picture/jsp.png', width: '50', height: '50', url: 'https://ru.wikipedia.org/wiki/JavaServer_Pages', target: '_top', tooltip: 'JSP' },
                { image: './picture/maven.png', width: '50', height: '50', url: 'https://maven.apache.org/', target: '_top', tooltip: 'Apache Maven' },
                { image: './picture/springboot.png', width: '50', height: '50', url: 'https://spring.io/projects/spring-boot', target: '_top', tooltip: 'Spring Boot' },
                { image: './picture/tomcat.png', width: '50', height: '50', url: 'http://tomcat.apache.org/', target: '_top', tooltip: 'Apache Tomcat' },

            ];

            var settings = {

                entries: entries,
                width: 480,
                height: 480,
                radius: '65%',
                radiusMin: 75,
                bgDraw: true,
                bgColor: '#111',
                opacityOver: 1.00,
                opacityOut: 0.05,
                opacitySpeed: 6,
                fov: 800,
                speed: 2,
                fontFamily: 'Oswald, Arial, sans-serif',
                fontSize: '15',
                fontColor: '#fff',
                fontWeight: 'normal',//bold
                fontStyle: 'normal',//italic
                fontStretch: 'normal',//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
                fontToUpperCase: true,
                tooltipFontFamily: 'Oswald, Arial, sans-serif',
                tooltipFontSize: '16',
                tooltipFontColor: '#fff',
                tooltipFontWeight: 'normal',//bold
                tooltipFontStyle: 'normal',//italic
                tooltipFontStretch: 'normal',//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
                tooltipFontToUpperCase: false,
                tooltipTextAnchor: 'left',
                tooltipDiffX: 0,
                tooltipDiffY: 10

            };

            //var svg3DTagCloud = new SVG3DTagCloud( document.getElementById( 'holder'  ), settings );
            $( '#holder' ).svg3DTagCloud( settings );

		} );

    </script>

</head>

<body>

    <div id='holder'></div>

</body>

</html>