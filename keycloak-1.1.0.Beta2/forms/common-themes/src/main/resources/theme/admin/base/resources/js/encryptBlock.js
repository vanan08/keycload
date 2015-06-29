
        //Global variable
        var randomKey=null;
        var Modulus=null;
        var Exponent=null;
      
		
    		function setBlock() {
    			       		
    			//alert("It works");
    			var username = document.getElementById("username").value;
    			var password = document.getElementById("password").value;
    			randomKey= document.getElementById("randomKey").value;
    			Modulus= document.getElementById("publicKey").value;
    			Exponent= document.getElementById("exponent").value;
    			
    			
    			
    			var encryptedBlock = encryptVerifyStaticRSABlock(username, password, randomKey);		
    			
    			document.getElementById("encryptedBlock").value = encryptedBlock;        		
    			    		
    			}
        		
        
