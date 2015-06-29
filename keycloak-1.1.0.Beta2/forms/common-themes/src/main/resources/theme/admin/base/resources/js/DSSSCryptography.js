/**
* @author Chong Rong Hwa
* @company DSSS, http://www.dsssasia.com
* @version 3
*
* This file not mean from redistribution
*/
function translateVerifyRSABlock( UserIdValue,PinValue,ServerRND) 
{
  var s = UserIdValue + PinValue;
  var cnt = Math.ceil (s.length/(ServerRND.length/2));
  //create static old password
  var longrnd = "";
  for (var i = 0 ; i < cnt; i ++) longrnd = longrnd + ServerRND;
  
  var biUIDPIN = new BigInteger(getByteArray(s));
  longrnd = longrnd.substring (0,s.length*2);//one byte 2 hex digits
  var tmp = new BigInteger(longrnd,16);
  biUIDPIN = biUIDPIN.xor (tmp);
  var len = s.length.toString(16);
  if (s.length <= 0xF) len = "0"+len;
  var block1 = "0B"+ len + biUIDPIN.toString(16);
  
  //create plain text
  var plaintext = new BigInteger (block1, 16);
  
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function translateChangePwdRSABlock( UserIdValue,Pin1Value,Pin2Value,ServerRND) 
{
  // the server random is expected 8 bytes
  //create static old password
  var s = UserIdValue + Pin1Value;
  var cnt = Math.ceil (s.length/(ServerRND.length/2));
  var longrnd = "";
  for (var i = 0 ; i < cnt; i ++) longrnd = longrnd + ServerRND;

  var biUIDPIN = new BigInteger(getByteArray(s));
  longrnd = longrnd.substring (0,s.length*2);//one byte 2 hex digits
  var tmp = new BigInteger(longrnd,16);
  biUIDPIN = biUIDPIN.xor (tmp);
  var len = s.length.toString(16);
  if (s.length <= 0xF) len = "0"+len;
  var block1 = "0B"+ len + biUIDPIN.toString(16);
  
  //create static new password
  s = UserIdValue + Pin2Value;
  cnt = Math.ceil (s.length/(ServerRND.length/2));
  longrnd = "";
  for (var i = 0 ; i < cnt; i ++) longrnd = longrnd + ServerRND;
  
  biUIDPIN = new BigInteger(getByteArray(s));
  longrnd = longrnd.substring (0,s.length*2);//one byte 2 hex digits
  tmp = new BigInteger(longrnd,16);
  biUIDPIN = biUIDPIN.xor (tmp);
  len = s.length.toString(16);
  if (s.length <= 0xF) len = "0"+len;
  var block2 = "0C"+ len + biUIDPIN.toString(16);
  
  //create plain text
  var plaintext = new BigInteger (block1+block2, 16);
  
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function encryptSetPwdNoVerifyRSABlock( UserIdValue,PinValue,ServerRND ) 
{
  //create static password hash without TVL
  var s = UserIdValue + PinValue;
  var biPwdHash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND,16);
  biPwdHash = biPwdHash.xor (tmp);
  var block1 = "0210"+ biPwdHash.toString(16);
  
  //create plain text
  var plaintext = new BigInteger (block1, 16);
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function encryptSetPwdRSABlock( UserIdValue,PinValue,OtipValue,ServerRND ) 
{
  //create static password hash without TVL
  var s = UserIdValue + PinValue;
  var biPwdHash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND,16);
  biPwdHash = biPwdHash.xor (tmp);
  var block1 = "0210"+ biPwdHash.toString(16);
  
  //create OTPW with TVL
  var OTIPLength = OtipValue.length.toString(16);
  if (OtipValue.length<16) OTIPLength = "0" + OTIPLength;
  var biOTIP = new BigInteger(getByteArray(OtipValue));
  var block2 = "03" + OTIPLength + biOTIP.toString(16);
  
  //create plain text
  var plaintext = new BigInteger (block1+block2, 16);
  var rsa = new RSAKey();
  //alert(Modulus);
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function encryptChangePwdRSABlock( UserIdValue,Pin1Value,Pin2Value,OtipValue,ServerRND1,ServerRND2) 
{
  //create static old password hash with TVL
  var s = UserIdValue + Pin1Value;
  var biPwdHash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND1,16);
  biPwdHash = biPwdHash.xor (tmp);
  var block1 = "0110"+ biPwdHash.toString(16);
  
  //create static new password hash with TVL
  var s = UserIdValue + Pin2Value;
  var biPwdHash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND2,16);
  biPwdHash = biPwdHash.xor (tmp);
  var block2 = "0210"+ biPwdHash.toString(16);
  
  //create OTPW with TVL
  var OTIPLength = OtipValue.length.toString(16);
  if (OtipValue.length<16) OTIPLength = "0" + OTIPLength;
  var biOTIP = new BigInteger(getByteArray(OtipValue));
  var block3 = "03" + OTIPLength + biOTIP.toString(16);
  
  //create plain text
  var plaintext = new BigInteger (block1+block2+block3, 16);
  
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function encryptChangePwdNoVerifyRSABlock( UserIdValue,Pin1Value,Pin2Value,ServerRND1,ServerRND2) 
{
  //create static old password hash with TVL
  var s = UserIdValue + Pin1Value;
  var biPwdHash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND1,16);
  biPwdHash = biPwdHash.xor (tmp);
  var block1 = "0110"+ biPwdHash.toString(16);
  
  //create static new password hash with TVL
  var s = UserIdValue + Pin2Value;
  var biPwdHash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND2,16);
  biPwdHash = biPwdHash.xor (tmp);
  var block2 = "0210"+ biPwdHash.toString(16);
 
  //create plain text
  var plaintext = new BigInteger (block1+block2, 16);
  
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function encryptVerifyRSABlock(UserIdValue, PinValue, OtipValue, ServerRND){
  //create static password hash without TVL
  var s = UserIdValue + PinValue;
  var biPwdHash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND,16);
  biPwdHash = biPwdHash.xor (tmp);
  
  //create OTPW with TVL
  var OTIPLength = OtipValue.length.toString(16);
  if (OtipValue.length<16) OTIPLength = "0" + OTIPLength;
  var biOTIP = new BigInteger(getByteArray(OtipValue));
  var TLVOTIP = "03" + OTIPLength + biOTIP.toString(16);
  biTLVOTIP = new BigInteger(TLVOTIP,16);
  
  //create plain text
  tmp = biPwdHash.toString(16) + biTLVOTIP.toString(16);
  var plaintext = new BigInteger 
  ("0110" +  tmp , 16);
  
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function getUserIDHexString(useridStr)
{
  var useridByte = getByteArray(useridStr);
  userIDHexString = "";
  for (var i = 0 ; i < useridByte.length; i++){
    var num = "";
    if ( useridByte[i] <= 0xF) num = "0"+useridByte[i].toString(16);
    else num = useridByte[i].toString(16);
    userIDHexString = userIDHexString + num;
  }
  return userIDHexString + "00";
}

function encryptVerify2RSABlock(UserIdValue, OtipValue, ServerRND){
  //creating first block
  //0x03 || (length of OTIP) || (ServerRND[0-2*length of OTIP] ^ OTIP byte array)
  //get sub random
  var subRND = ServerRND.substring(0,OtipValue.length*2);
  var biSubRND = new BigInteger(subRND,16);
  //create first block
  var OTIPLength = OtipValue.length.toString(16);
  if (OtipValue.length<16) OTIPLength = "0" + OTIPLength;
  var biOTIP = new BigInteger(getByteArray(OtipValue));
  var TLVOTIP = "03" + OTIPLength + (biOTIP.xor(biSubRND)).toString(16);
  biTLVOTIP = new BigInteger(TLVOTIP,16);
  
  //create static password hash without TVL
  var s = UserIdValue + OtipValue;
  var biUID_OTIP_Hash = new BigInteger (MD5(s),16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND,16);
  biUID_OTIP_Hash = biUID_OTIP_Hash.xor (tmp);
  
  //create plain text
  tmp = biTLVOTIP.toString(16) + "0210" + biUID_OTIP_Hash.toString(16);
  var plaintext = new BigInteger( tmp , 16);
  
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(plaintext);
  return rsablock.toString(16);
}

function encryptVerifyOtipRSABlock(OtipValue) {
  //0x03 || (length of OTIP) || (OTIP byte array)
  //create first block
  
  var OTIPLength = OtipValue.length.toString(16);
  if (OtipValue.length<16) OTIPLength = "0" + OTIPLength;
  var biOTIP = new BigInteger(getByteArray(OtipValue));
  var TLVOTIP = "03" + OTIPLength + biOTIP.toString(16);
  biTLVOTIP = new BigInteger(TLVOTIP,16);
  
  //encrypt
  
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent);
  var rsablock = rsa.encrypt(biTLVOTIP);
  return rsablock.toString(16);
}

function encryptVerifyStaticRSABlock( UserIdValue, PinValue, ServerRND){	
  //create static password hash without TVL
  var s = UserIdValue + PinValue;
  var hashvalue = MD5(s);
  
  var biPwdHash = new BigInteger (hashvalue,16);//Big Integer password hash
  var tmp = new BigInteger(ServerRND,16);
  biPwdHash = biPwdHash.xor (tmp);
  
  //create plain text
  var plaintext = new BigInteger 
  ("0110" +  biPwdHash.toString(16)  , 16);
  var rsa = new RSAKey();
  rsa.setPublic(Modulus, Exponent );
  var rsablock = rsa.encrypt(plaintext).toString(16);
  return rsablock;
} 