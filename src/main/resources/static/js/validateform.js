function validateform() {
  let x = document.forms["myForm"]["grade"].value;
  var validgrades = ["A+", "A", "A-","B+", "B", "B-","C+", "C", "C-", "D+", "D","F"];
  var flag= new Boolean();
    for (let i = 0; i < validgrades.length; i++) {
	  if (x==validgrades[i])
	{ return flag;
	}
  }
  alert("Invalid grade");
  return !flag;
}