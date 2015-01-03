$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
}; 
function objToJson(obj){
	
	var type = "WEB";
	var data = {"type":type,"obj":obj};
	var json = JSON.stringify(data);
	return json;
}
function formToJson(formID){
	var JSONObj = $(formID).serializeObject();
    return objToJson(JSONObj);
 
}