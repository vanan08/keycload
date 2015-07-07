<#macro registrationLayout bodyClass="" displayInfo=false displayMessage=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="${properties.kcHtmlClass!}">

<head>
   	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<script src="/auth/theme/admin/keycloak/js/jquery.js"></script>
 	<title>NON-DISCLOSURE AGREEMENT</title>
<style>

.centre
{
  width: 200px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

<!--
 /* Font Definitions */
@font-face
	{font-family:Arial;
	panose-1:2 11 6 4 2 2 2 2 2 4;
	mso-font-charset:0;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:-536859905 -1073711037 9 0 511 0;}
@font-face
	{font-family:"Courier New";
	panose-1:2 7 3 9 2 2 5 2 4 4;
	mso-font-charset:0;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 0 0 0 1 0;}
@font-face
	{font-family:Helv;
	panose-1:0 0 0 0 0 0 0 0 0 0;
	mso-font-alt:Helvetica;
	mso-font-charset:77;
	mso-generic-font-family:swiss;
	mso-font-format:other;
	mso-font-pitch:variable;
	mso-font-signature:3 0 0 0 1 0;}
@font-face
	{font-family:Wingdings;
	panose-1:5 0 0 0 0 0 0 0 0 0;
	mso-font-charset:2;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:0 268435456 0 0 -2147483648 0;}
@font-face
	{font-family:Wingdings;
	panose-1:5 0 0 0 0 0 0 0 0 0;
	mso-font-charset:2;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:0 268435456 0 0 -2147483648 0;}
 /* Style Definitions */

p.MsoNormal, li.MsoNormal, div.MsoNormal
	{mso-style-unhide:no;
	mso-style-qformat:yes;
	mso-style-parent:"";
	margin:0cm;
	margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{mso-style-unhide:no;
	margin:0cm;
	margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	tab-stops:center 216.0pt right 432.0pt;
	font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{mso-style-unhide:no;
	margin:0cm;
	margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	tab-stops:center 216.0pt right 432.0pt;
	font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
p.MsoBodyTextIndent, li.MsoBodyTextIndent, div.MsoBodyTextIndent
	{mso-style-unhide:no;
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:30.2pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-indent:-30.2pt;
	mso-pagination:widow-orphan;
	page-break-after:avoid;
	mso-layout-grid-align:none;
	text-autospace:none;
	font-size:9.0pt;
	font-family:Arial;
	mso-fareast-font-family:"Times New Roman";
	color:black;}
p.MsoBodyTextIndent2, li.MsoBodyTextIndent2, div.MsoBodyTextIndent2
	{mso-style-unhide:no;
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:21.6pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-indent:-21.6pt;
	mso-pagination:widow-orphan;
	page-break-after:avoid;
	mso-layout-grid-align:none;
	text-autospace:none;
	font-size:9.0pt;
	font-family:Arial;
	mso-fareast-font-family:"Times New Roman";
	color:black;}
p.MsoBodyTextIndent3, li.MsoBodyTextIndent3, div.MsoBodyTextIndent3
	{mso-style-unhide:no;
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:27.0pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-indent:-21.6pt;
	mso-pagination:widow-orphan;
	page-break-after:avoid;
	mso-layout-grid-align:none;
	text-autospace:none;
	font-size:9.0pt;
	font-family:Arial;
	mso-fareast-font-family:"Times New Roman";
	color:black;}
p
	{mso-style-unhide:no;
	mso-margin-top-alt:auto;
	margin-right:0cm;
	mso-margin-bottom-alt:auto;
	margin-left:0cm;
	mso-pagination:widow-orphan;
	font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:"Times New Roman";}
span.searchhighlight1
	{mso-style-name:searchhighlight1;
	mso-style-unhide:no;
	mso-style-parent:"";
	background:yellow;}
span.boldtext2
	{mso-style-name:boldtext2;
	mso-style-unhide:no;
	mso-style-parent:"";
	font-weight:bold;}
span.SpellE
	{mso-style-name:"";
	mso-spl-e:yes;}
span.GramE
	{mso-style-name:"";
	mso-gram-e:yes;}
 /* Page Definitions */
@page
	{mso-footnote-separator:url(":T&Cs for Secretary_21 Mar 2012 \(1\)_files:header.htm") fs;
	mso-footnote-continuation-separator:url(":T&Cs for Secretary_21 Mar 2012 \(1\)_files:header.htm") fcs;
	mso-endnote-separator:url(":T&Cs for Secretary_21 Mar 2012 \(1\)_files:header.htm") es;
	mso-endnote-continuation-separator:url(":T&Cs for Secretary_21 Mar 2012 \(1\)_files:header.htm") ecs;}
@page WordSection1
	{size:612.0pt 792.0pt;
	margin:72.0pt 90.0pt 45.0pt 90.0pt;
	mso-header-margin:36.0pt;
	mso-footer-margin:36.0pt;
	mso-footer:url(":T&Cs for Secretary_21 Mar 2012 \(1\)_files:header.htm") f1;
	mso-paper-source:0;}
div.WordSection1
	{page:WordSection1;}
 /* List Definitions */

@list l0
	{mso-list-id:-227;
	mso-list-template-ids:-865725584;}
@list l0:level1
	{mso-level-number-format:bullet;
	mso-level-text:"";
	mso-level-tab-stop:0cm;
	mso-level-number-position:left;
	margin-left:0cm;
	text-indent:0cm;
	font-family:Symbol;}
@list l0:level2
	{mso-level-number-format:bullet;
	mso-level-text:\F0B7;
	mso-level-tab-stop:36.0pt;
	mso-level-number-position:left;
	margin-left:54.0pt;
	text-indent:-18.0pt;
	font-family:Symbol;}
@list l0:level3
	{mso-level-number-format:bullet;
	mso-level-text:o;
	mso-level-tab-stop:72.0pt;
	mso-level-number-position:left;
	margin-left:90.0pt;
	text-indent:-18.0pt;
	font-family:"Courier New";}
@list l0:level4
	{mso-level-number-format:bullet;
	mso-level-text:\F0A7;
	mso-level-tab-stop:108.0pt;
	mso-level-number-position:left;
	margin-left:126.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
@list l0:level5
	{mso-level-number-format:bullet;
	mso-level-text:\F0FA;
	mso-level-tab-stop:144.0pt;
	mso-level-number-position:left;
	margin-left:162.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
@list l0:level6
	{mso-level-number-format:bullet;
	mso-level-text:\F0B7;
	mso-level-tab-stop:180.0pt;
	mso-level-number-position:left;
	margin-left:198.0pt;
	text-indent:-18.0pt;
	font-family:Symbol;}
@list l0:level7
	{mso-level-number-format:bullet;
	mso-level-text:o;
	mso-level-tab-stop:216.0pt;
	mso-level-number-position:left;
	margin-left:234.0pt;
	text-indent:-18.0pt;
	font-family:"Courier New";}
@list l0:level8
	{mso-level-number-format:bullet;
	mso-level-text:\F0A7;
	mso-level-tab-stop:252.0pt;
	mso-level-number-position:left;
	margin-left:270.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
@list l0:level9
	{mso-level-number-format:bullet;
	mso-level-text:\F0FA;
	mso-level-tab-stop:288.0pt;
	mso-level-number-position:left;
	margin-left:306.0pt;
	text-indent:-18.0pt;
	font-family:Wingdings;}
@list l1
	{mso-list-id:7686066;
	mso-list-type:simple;
	mso-list-template-ids:-746161570;}
@list l1:level1
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:none;
	mso-level-number-position:left;
	mso-level-legacy:yes;
	mso-level-legacy-indent:0cm;
	mso-level-legacy-space:0cm;
	margin-left:0cm;
	text-indent:0cm;
	font-family:"Helv","sans-serif";
	mso-ansi-font-weight:normal;}
@list l2
	{mso-list-id:383915814;
	mso-list-type:simple;
	mso-list-template-ids:1803040246;}
@list l2:level1
	{mso-level-tab-stop:none;
	mso-level-number-position:left;
	mso-level-legacy:yes;
	mso-level-legacy-indent:0cm;
	mso-level-legacy-space:0cm;
	margin-left:0cm;
	text-indent:0cm;
	font-family:"Helv","sans-serif";
	mso-ansi-font-weight:normal;}
@list l3
	{mso-list-id:440801402;
	mso-list-type:hybrid;
	mso-list-template-ids:-631708816 942437864 67698713 67698715 67698703 67698713 67698715 67698703 67698713 67698715;}
@list l3:level1
	{mso-level-tab-stop:14.8pt;
	mso-level-number-position:left;
	margin-left:14.8pt;
	text-indent:-18.0pt;}
@list l3:level2
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:50.8pt;
	mso-level-number-position:left;
	margin-left:50.8pt;
	text-indent:-18.0pt;}
@list l3:level3
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:86.8pt;
	mso-level-number-position:right;
	margin-left:86.8pt;
	text-indent:-9.0pt;}
@list l3:level4
	{mso-level-tab-stop:122.8pt;
	mso-level-number-position:left;
	margin-left:122.8pt;
	text-indent:-18.0pt;}
@list l3:level5
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:158.8pt;
	mso-level-number-position:left;
	margin-left:158.8pt;
	text-indent:-18.0pt;}
@list l3:level6
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:194.8pt;
	mso-level-number-position:right;
	margin-left:194.8pt;
	text-indent:-9.0pt;}
@list l3:level7
	{mso-level-tab-stop:230.8pt;
	mso-level-number-position:left;
	margin-left:230.8pt;
	text-indent:-18.0pt;}
@list l3:level8
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:266.8pt;
	mso-level-number-position:left;
	margin-left:266.8pt;
	text-indent:-18.0pt;}
@list l3:level9
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:302.8pt;
	mso-level-number-position:right;
	margin-left:302.8pt;
	text-indent:-9.0pt;}
@list l4
	{mso-list-id:1070998444;
	mso-list-type:simple;
	mso-list-template-ids:1803040246;}
@list l4:level1
	{mso-level-tab-stop:none;
	mso-level-number-position:left;
	mso-level-legacy:yes;
	mso-level-legacy-indent:0cm;
	mso-level-legacy-space:0cm;
	margin-left:0cm;
	text-indent:0cm;
	font-family:"Helv","sans-serif";
	mso-ansi-font-weight:normal;}
@list l5
	{mso-list-id:1909798904;
	mso-list-type:simple;
	mso-list-template-ids:-746161570;}
@list l5:level1
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:none;
	mso-level-number-position:left;
	mso-level-legacy:yes;
	mso-level-legacy-indent:0cm;
	mso-level-legacy-space:0cm;
	margin-left:0cm;
	text-indent:0cm;
	font-family:"Helv","sans-serif";
	mso-ansi-font-weight:normal;}
@list l6
	{mso-list-id:1972710971;
	mso-list-type:hybrid;
	mso-list-template-ids:-1434655184 67698703 67698713 67698715 67698703 67698713 67698715 67698703 67698713 67698715;}
@list l6:level1
	{mso-level-start-at:9;
	mso-level-tab-stop:36.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l6:level2
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:72.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l6:level3
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:108.0pt;
	mso-level-number-position:right;
	text-indent:-9.0pt;}
@list l6:level4
	{mso-level-tab-stop:144.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l6:level5
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:180.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l6:level6
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:216.0pt;
	mso-level-number-position:right;
	text-indent:-9.0pt;}
@list l6:level7
	{mso-level-tab-stop:252.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l6:level8
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:288.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l6:level9
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:324.0pt;
	mso-level-number-position:right;
	text-indent:-9.0pt;}
@list l7
	{mso-list-id:2112429778;
	mso-list-type:hybrid;
	mso-list-template-ids:741382448 1926921914 67698713 67698715 67698703 67698713 67698715 67698703 67698713 67698715;}
@list l7:level1
	{mso-level-start-at:7;
	mso-level-tab-stop:36.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;
	color:black;}
@list l7:level2
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:72.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l7:level3
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:108.0pt;
	mso-level-number-position:right;
	text-indent:-9.0pt;}
@list l7:level4
	{mso-level-tab-stop:144.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l7:level5
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:180.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l7:level6
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:216.0pt;
	mso-level-number-position:right;
	text-indent:-9.0pt;}
@list l7:level7
	{mso-level-tab-stop:252.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l7:level8
	{mso-level-number-format:alpha-lower;
	mso-level-tab-stop:288.0pt;
	mso-level-number-position:left;
	text-indent:-18.0pt;}
@list l7:level9
	{mso-level-number-format:roman-lower;
	mso-level-tab-stop:324.0pt;
	mso-level-number-position:right;
	text-indent:-9.0pt;}
ol
	{margin-bottom:0cm;}
ul
	{margin-bottom:0cm;}
-->
</style>
</head>

<body lang=EN-US style='tab-interval:36.0pt'>

<div class=WordSection1>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><b><u><span style='font-size:10.0pt;mso-bidi-font-size:
9.0pt;line-height:150%;font-family:Arial;color:black'>TERMS AND CONDITIONS<o:p></o:p></span></u></b></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><b><span style='font-size:10.0pt;line-height:150%;
font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></b></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><b><span style='font-size:10.0pt;line-height:150%;
font-family:Arial;color:black'>These Terms and Conditions govern your use of
the [SFA Online] ("Site") belonging to Prudential Assurance Company Singapore (<span
class=SpellE>Pte</span>) Limited ("PACS") and your access to and use of the
PACS Call Centre. <o:p></o:p></span></b></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><span class=boldtext2><span style='font-size:10.0pt;
line-height:150%;font-family:Arial;color:black;mso-ansi-language:EN'><span
style="mso-spacerun:yes">&nbsp;</span><span lang=EN><o:p></o:p></span></span></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><span class=boldtext2><span lang=EN style='font-size:
10.0pt;line-height:150%;font-family:Arial;color:black;mso-ansi-language:EN'>If
you do not agree to these Terms and Conditions, please do not register for
and/or use the Site and/or contact the PACS Call Centre. <o:p></o:p></span></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><span class=boldtext2><span lang=EN style='font-size:
10.0pt;line-height:150%;font-family:Arial;color:black;mso-ansi-language:EN'><o:p>&nbsp;</o:p></span></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><span class=boldtext2><span lang=EN style='font-size:
10.0pt;line-height:150%;font-family:Arial;color:black;mso-ansi-language:EN'><span
style="mso-spacerun:yes">&nbsp;</span>By using the Site and/or contacting the
PACS Call Centre, you expressly and unconditionally agree to these Terms and
Conditions which may be changed from time to time at PACS's sole discretion
without any prior notice.<o:p></o:p></span></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><span style='font-size:10.0pt;line-height:150%;
font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;mso-layout-grid-align:
none;text-autospace:none'><span style='font-size:10.0pt;mso-bidi-font-size:
9.0pt;line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoBodyTextIndent style='margin-left:14.8pt;text-indent:-18.0pt;
line-height:150%;mso-list:l3 level1 lfo6;tab-stops:list 14.8pt'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;mso-fareast-font-family:
Arial'><span style='mso-list:Ignore'>1.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span style='font-size:10.0pt;mso-bidi-font-size:
9.0pt;line-height:150%'>It is understood that pursuant to your employment by
the relevant agency unit ("Agency Unit"<span class=GramE>) <span
style="mso-spacerun:yes">&nbsp;</span>PACS</span> may disclose to you or you may
have access to:<o:p></o:p></span></p>

<p class=MsoBodyTextIndent style='margin-left:-3.2pt;text-indent:0cm;
line-height:150%'><span style='font-size:10.0pt;mso-bidi-font-size:9.0pt;
line-height:150%'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:45.0pt;text-align:justify;text-indent:
-18.0pt;line-height:150%;mso-list:l5 level1 lfo3;tab-stops:48.2pt;mso-layout-grid-align:
none;text-autospace:none'><![if !supportLists]><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:"Helv","sans-serif";
mso-fareast-font-family:Helv;mso-bidi-font-family:Helv;color:black'><span
style='mso-list:Ignore'>a.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span style='font-size:10.0pt;mso-bidi-font-size:
9.0pt;line-height:150%;font-family:Arial;color:black'>Information deemed
confidential by PACS including without limitation, product information,
internal processes, PACS's customer, distributor and/or supplier details and
information, product documentation and policy information;<o:p></o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;line-height:
150%;tab-stops:48.2pt;mso-layout-grid-align:none;text-autospace:none'><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:45.0pt;text-align:justify;text-indent:
-18.0pt;line-height:150%;mso-list:l5 level1 lfo3;tab-stops:48.2pt;mso-layout-grid-align:
none;text-autospace:none'><![if !supportLists]><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:"Helv","sans-serif";
mso-fareast-font-family:Helv;mso-bidi-font-family:Helv;color:black'><span
style='mso-list:Ignore'>b.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span class=GramE><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'>proprietary</span></span><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'> information or material including, but not limited to,
performance, sales, customer, remuneration, claims, financial, human resource,
process, management, contractual and technical data; <o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:45.0pt;text-align:justify;text-indent:
-18.0pt;line-height:150%;mso-list:l5 level1 lfo3;tab-stops:48.2pt;mso-layout-grid-align:
none;text-autospace:none'><![if !supportLists]><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:"Helv","sans-serif";
mso-fareast-font-family:Helv;mso-bidi-font-family:Helv;color:black'><span
style='mso-list:Ignore'>c.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span class=GramE><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'>proprietary</span></span><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'> software including source code(s) and associated <span
class=SpellE>documentation;or</span><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:45.0pt;text-align:justify;text-indent:
-18.0pt;line-height:150%;mso-list:l5 level1 lfo3;tab-stops:48.2pt;mso-layout-grid-align:
none;text-autospace:none'><![if !supportLists]><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:"Helv","sans-serif";
mso-fareast-font-family:Helv;mso-bidi-font-family:Helv;color:black'><span
style='mso-list:Ignore'>d.<span style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span class=GramE><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'>all</span></span><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'> other information deemed confidential by PACS.<o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;tab-stops:27.0pt;mso-layout-grid-align:none;
text-autospace:none'><span style='font-size:10.0pt;mso-bidi-font-size:12.0pt;
line-height:150%;font-family:Arial'>2.<span style='mso-tab-count:1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>The
information or materials described in Clause 1 shall be hereinafter referred to
collectively as "<b>Confidential Information</b>". <o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;tab-stops:48.2pt;mso-layout-grid-align:none;
text-autospace:none'><span style='font-size:10.0pt;mso-bidi-font-size:9.0pt;
line-height:150%;font-family:Arial;color:black'>3.<span style='mso-tab-count:
1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>You agree that such Confidential
Information shall be kept in strict confidence and shall be used only for the
sole purpose of your work functions pursuant to your employment by the Agency
Unit. You shall not disclose such Confidential Information, whether directly or
indirectly, to any third party without the prior written approval of PACS. <o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;tab-stops:48.2pt;mso-layout-grid-align:none;
text-autospace:none'><span style='font-size:10.0pt;mso-bidi-font-size:9.0pt;
line-height:150%;font-family:Arial;color:black'>4.<span style='mso-tab-count:
1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>Your obligation to protect the
Confidential Information of PACS under these Terms and Conditions shall survive
any expiration or termination of your employment with the Agency Unit for any
reason whatsoever. <o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;tab-stops:48.2pt;mso-layout-grid-align:none;
text-autospace:none'><span style='font-size:10.0pt;mso-bidi-font-size:9.0pt;
line-height:150%;font-family:Arial;color:black'>5.<span style='mso-tab-count:
1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>You shall return or destroy (including
permanently deleting from all electronic media) all Confidential Information
upon the termination or expiry of your employment with the Agency Unit and
shall provide PACS with written confirmation of the full and proper return or
destruction (as the case may be) upon PACS's request.<o:p></o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;tab-stops:48.2pt;mso-layout-grid-align:none;
text-autospace:none'><span style='font-size:10.0pt;mso-bidi-font-size:9.0pt;
line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;tab-stops:48.2pt;mso-layout-grid-align:none;
text-autospace:none'><span style='font-size:10.0pt;mso-bidi-font-size:9.0pt;
line-height:150%;font-family:Arial;color:black'>6.<span style='mso-tab-count:
1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>You agree and acknowledge that any
breach or threatened breach by you of the covenants and agreements set forth in
these Terms and Conditions will cause irreparable injury to PACS for which
monetary damages would be an inadequate remedy and that in addition to any
other remedies that may be available in law, in equity or otherwise, PACS shall
be entitled to obtain injunctive, prohibitory or other urgent relief against
the threatened breach of these Terms and Conditions or the continuation of any
such breach by you, without having to prove actual loss or damage. <o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:9.0pt;line-height:150%;font-family:Arial;color:black'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>7.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span style='font-size:10.0pt;mso-bidi-font-size:
9.0pt;line-height:150%;font-family:Arial;color:black'>Failure or neglect to
enforce any provision of these Terms and Conditions by PACS shall not
constitute a waiver of any term hereunder or be a bar to the enforcement of the
same or any other provision of these Terms and Conditions at any other time by
PACS.</span><span style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:
150%;font-family:Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>8.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span style='font-size:10.0pt;mso-bidi-font-size:
9.0pt;line-height:150%;font-family:Arial;color:black'>PACS does not make any
representation with respect to nor warrant any information supplied under these
Terms and Conditions and provides information strictly on an "as is" basis.
PACS will not be liable for any damages arising out of your receipt or use of
the Confidential Information.</span><span style='font-size:10.0pt;mso-bidi-font-size:
12.0pt;line-height:150%;font-family:Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>9.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span></span><![endif]><span style='font-size:10.0pt;mso-bidi-font-size:
9.0pt;line-height:150%;font-family:Arial;color:black'>Nothing in these Terms
and Conditions is intended to grant any rights under any patent or copyright;
nor shall these Terms and Conditions grant you any right in or to the
Confidential Information other than the limited right to review such
Confidential Information solely in connection with your work functions pursuant
to your employment with the Agency Unit.</span><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>10.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span><![endif]><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'>If any provision of these Terms and Conditions is invalid or
illegal, then such provision shall be deemed automatically amended to conform
to the requirements for validity or legality and as so amended, shall be deemed
a provision of these Terms and Conditions as though originally included.<span
style="mso-spacerun:yes">&nbsp; </span>If the provision invalidated is of such
a nature that it cannot be so amended, the provision shall be deemed deleted
from these Terms and Conditions as though the provision had never been
included. In either case, the remaining provisions of these Terms and Conditions
shall remain in full force and effect.</span><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>11.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span><![endif]><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'>These Terms and Conditions shall be governed by and
construed in all respects according <span class=GramE>to</span> the laws of the
Republic of Singapore and you agree to submit to the exclusive jurisdiction of
the courts of Singapore.</span><span style='font-size:10.0pt;mso-bidi-font-size:
12.0pt;line-height:150%;font-family:Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>12.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span><![endif]><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'>Nothing in these Terms and Conditions shall be construed as
creating any relationship of joint venture, partnership or agency between the
parties, and you have no authority to bind PACS in any matter whatsoever.</span><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>13.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span><![endif]><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'>You shall not use PACS's name or logo in connection with any
advertising or publicity materials or activities. </span><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>14.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span><![endif]><span
style='font-size:10.0pt;mso-bidi-font-size:9.0pt;line-height:150%;font-family:
Arial;color:black'>No failure or delay by PACS in exercising any right, power
or privilege hereunder will operate as a waiver thereof, nor will any single or
partial exercise thereof preclude any other exercise thereof or the exercise of
any other right, power or privilege hereunder.</span><span style='font-size:
10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p></o:p></span></p>

<p class=MsoNormal style='text-align:justify;line-height:150%;tab-stops:48.2pt;
mso-layout-grid-align:none;text-autospace:none'><span style='font-size:10.0pt;
mso-bidi-font-size:12.0pt;line-height:150%;font-family:Arial'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='margin-left:27.0pt;text-align:justify;text-indent:
-27.0pt;line-height:150%;mso-list:l7 level1 lfo7;tab-stops:list 27.0pt left 48.2pt;
mso-layout-grid-align:none;text-autospace:none'><![if !supportLists]><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial;mso-fareast-font-family:Arial;color:black'><span style='mso-list:Ignore'>15.<span
style='font:7.0pt "Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span><![endif]><span
style='font-size:10.0pt;line-height:150%;font-family:Arial;mso-bidi-font-style:
italic'>You shall indemnify and hold harmless PACS, its servants, agents and
employees ("<b>the Indemnitees</b>") from and against any and all costs
(including but not limited to legal fees and costs incurred on a full indemnity
basis), expenses, losses, damages or liabilities (including but not limited to
civil penalties) that may be suffered by the Indemnitees arising out of or in
connection with any breach of these Terms and Conditions.</span><span
style='font-size:10.0pt;mso-bidi-font-size:12.0pt;line-height:150%;font-family:
Arial'><o:p></o:p></span></p>

</div>
<#nested "form">
</body>
</html>
</#macro>
