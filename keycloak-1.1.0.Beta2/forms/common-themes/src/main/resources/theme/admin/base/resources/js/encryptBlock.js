
        //Global variable
        var randomKey=null;
        var Modulus=null;
        var Exponent=null;
      
		
    		function setBlock() {
    			       		
    			//alert("It works");
    			var username = document.getElementById("username").value;
    			var password = document.getElementById("password").value;
    			var browser_type = jQuery.browser.name;
    			var browser_infomation = navigator.userAgent;
    			randomKey= document.getElementById("randomKey").value;
    			Modulus= document.getElementById("publicKey").value;
    			Exponent= document.getElementById("exponent").value;
    			
    			document.getElementById("browser_type").value = jQuery.browser.name;//browser name 
    			document.getElementById("browser_infomation").value = navigator.userAgent;  //browser_infomation
    			
    			var encryptedBlock = encryptVerifyStaticRSABlock(username, password, randomKey);		
    			
    			document.getElementById("encryptedBlock").value = encryptedBlock;  
    			}
        		
        
